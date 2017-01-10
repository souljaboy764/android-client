package com.mifos.mifosxdroid.online.attachmeeting;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.core.MifosBaseActivity;
import com.mifos.mifosxdroid.core.MifosBaseFragment;
import com.mifos.objects.collectionsheet.CollectionMeetingCalendarPayload;
import com.mifos.objects.collectionsheet.EntityType;
import com.mifos.objects.templates.loans.MeetingCalendarTemplate;
import com.mifos.utils.Constants;
import com.mifos.utils.DateHelper;
import com.mifos.utils.PrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by nellyk on 2/27/2016.
 */
public class AttachMeetingFragment extends MifosBaseFragment implements AttachMeetingMvpView {

    public static final String LOG_TAG = AttachMeetingFragment.class.getSimpleName();

    @BindView(R.id.et_meeting_select_date)
    EditText etMeetingSelectDate;

    @BindView(R.id.cb_meeting_repeat)
    CheckBox cbMeetingRepeat;

    @BindView(R.id.tl_meeting_repeat_details)
    TableLayout tlMeetingRepeatDetails;

    @BindView(R.id.sp_meeting_repeat_mode)
    Spinner spMeetingRepeatMode;

    @BindView(R.id.sp_meeting_repeat_frequency)
    Spinner spMeetingRepeatFrequency;

    @BindView(R.id.tv_meeting_repeat_suffix)
    TextView tvMeetingRepeatSuffix;

    @BindView(R.id.sp_meeting_repeat_days_of_week)
    Spinner spMeetingRepeatDaysOfWeek;

    @BindView(R.id.bt_meeting_submit)
    Button btMeetingSubmit;

    @Inject
    AttachMeetingPresenter mAttachMeetingPresenter;

    MeetingCalendarTemplate mMeetingCalenderTemplate;

    private View rootView;
    private int groupOrCenterId, groupOrCenterType;

    public static AttachMeetingFragment newInstance(int groupOrCenterId, int groupOrCenterType) {
        AttachMeetingFragment fragment = new AttachMeetingFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ENTITY_ID, groupOrCenterId);
        args.putInt(Constants.ENTITY_TYPE, groupOrCenterType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MifosBaseActivity) getActivity()).getActivityComponent().inject(this);
        if (getArguments() != null) {
            groupOrCenterId = getArguments().getInt(Constants.ENTITY_ID);
            groupOrCenterType = getArguments().getInt(Constants.ENTITY_TYPE);
            mAttachMeetingPresenter.loadGroupMeetingCalenderTemplate(groupOrCenterId);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_attach_meeting, container, false);

        ButterKnife.bind(this, rootView);
        mAttachMeetingPresenter.attachView(this);
        etMeetingSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        ((EditText) view).setText(  String.valueOf(year) + "-" +
                                                    String.valueOf(monthOfYear+1) + "-" +
                                                    String.valueOf(dayOfMonth));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        cbMeetingRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                tlMeetingRepeatDetails.setVisibility((checked)?VISIBLE:GONE);
            }
        });

        spMeetingRepeatMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                loadFrequencyOptions(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spMeetingRepeatMode.setSelection(Constants.REPEATED_YEARLY);

        btMeetingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionMeetingCalendarPayload collectionMeetingCalendarPayload = new CollectionMeetingCalendarPayload();
                collectionMeetingCalendarPayload.setEntityId(groupOrCenterId);
                collectionMeetingCalendarPayload.setEntityType(mMeetingCalenderTemplate.getEntityTypeOptions().get(groupOrCenterType));

                String type = collectionMeetingCalendarPayload.getEntityType().getValue();
                String title;
                if(type.contentEquals(Constants.ENTITY_TYPE_GROUPS.toUpperCase()) ||
                   type.contentEquals(Constants.ENTITY_TYPE_CENTERS.toUpperCase())) {
                    title = type.toLowerCase() + "_" + Integer.toString(groupOrCenterId) + Constants.MEETING_TITLE_SUFFIX;
                    collectionMeetingCalendarPayload.setTitle(title);
                }

                collectionMeetingCalendarPayload.setDuration(mMeetingCalenderTemplate.getDuration());

                if(cbMeetingRepeat.isChecked()) {

                    collectionMeetingCalendarPayload.setFrequency(mMeetingCalenderTemplate.getFrequencyOptions().get(spMeetingRepeatMode.getSelectedItemPosition()));
                    collectionMeetingCalendarPayload.setInterval(Integer.parseInt(((TextView)spMeetingRepeatFrequency.getSelectedView()).getText().toString()));
                    if(spMeetingRepeatMode.getSelectedItemPosition()==Constants.REPEATED_WEEKLY)
                        collectionMeetingCalendarPayload.setRepeatsOnDay(mMeetingCalenderTemplate.getRepeatsOnDayOptions().get(spMeetingRepeatDaysOfWeek.getSelectedItemPosition()));
                }

                collectionMeetingCalendarPayload.setFirstReminder(0);
                collectionMeetingCalendarPayload.setSecondReminder(0);
                collectionMeetingCalendarPayload.setCalendarInstanceId(mMeetingCalenderTemplate.getCalendarTypeOptions().get(0).getId());
                collectionMeetingCalendarPayload.setTypeId(mMeetingCalenderTemplate.getEntityTypeOptions().get(groupOrCenterType).getId());
                if(!TextUtils.isEmpty(etMeetingSelectDate.getText().toString())) {
                   collectionMeetingCalendarPayload.setStartDate(etMeetingSelectDate.getText().toString());
                    mAttachMeetingPresenter.attachMeeting(collectionMeetingCalendarPayload);
                } else {
                    showFetchingError("Date Cannot be empty");
                }
            }
        });

        //btMeetingSubmit.performClick();
        return rootView;
    }



    @Override
    public void loadFrequencyOptions(int pos) {
        int frequencyLimit = getResources().getIntArray(R.array.meeting_repeat_frequency_limits)[pos];
        Integer[] frequencyOptions = new Integer[frequencyLimit];
        for(int i=1;i<=frequencyLimit; i++)
            frequencyOptions[i-1] = i;
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item,
                frequencyOptions);
        spMeetingRepeatFrequency.setAdapter(spinnerArrayAdapter);
        spMeetingRepeatFrequency.setSelection(0);

        tvMeetingRepeatSuffix.setText(getResources().getStringArray(R.array.meeting_repeat_type_suffix)[pos]);


        spMeetingRepeatDaysOfWeek.setVisibility((pos==Constants.REPEATED_WEEKLY)?VISIBLE:GONE);
    }

    @Override
    public void meetingAttachedSuccessfully() {

    }

    @Override
    public void showFetchingError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGroupMeetingCalendarTemplate(MeetingCalendarTemplate meetingCalenderTemplate) {
        this.mMeetingCalenderTemplate = meetingCalenderTemplate;
    }


    public void onDestroyView() {
        super.onDestroyView();
        mAttachMeetingPresenter.detachView();
    }

    @Override
    public void showProgressbar(boolean b) {
        if (b) {
            showMifosProgressDialog();
        } else {
            hideMifosProgressDialog();
        }
    }
}
