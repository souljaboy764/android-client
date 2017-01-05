package com.mifos.mifosxdroid.online.attachmeeting;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.fonts.MaterialIcons;
import com.joanzapata.iconify.widget.IconTextView;
import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.adapters.LoanAccountsListAdapter;
import com.mifos.mifosxdroid.adapters.SavingsAccountsListAdapter;
import com.mifos.mifosxdroid.core.MifosBaseActivity;
import com.mifos.mifosxdroid.core.MifosBaseFragment;
import com.mifos.mifosxdroid.core.ProgressableFragment;
import com.mifos.mifosxdroid.online.documentlist.DocumentListFragment;
import com.mifos.mifosxdroid.online.grouploanaccount.GroupLoanAccountFragment;
import com.mifos.objects.accounts.GroupAccounts;
import com.mifos.objects.accounts.savings.DepositType;
import com.mifos.objects.client.Client;
import com.mifos.objects.group.Group;
import com.mifos.objects.noncore.DataTable;
import com.mifos.objects.templates.loans.CalendarType;
import com.mifos.utils.Constants;
import com.mifos.utils.FragmentConstants;
import com.mifos.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
                        ((EditText) view).setText(  Integer.toString(year) + "-" +
                                                    Integer.toString(monthOfYear) + "-" +
                                                    Integer.toString(dayOfMonth));
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

        spMeetingRepeatMode.setSelection(0);

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

        tvMeetingRepeatSuffix.setText(getResources().getStringArray(R.array.meeting_repeat_type_suffix)[pos]);


        spMeetingRepeatDaysOfWeek.setVisibility((pos==Constants.REPEATED_WEEKLY)?VISIBLE:GONE);
    }

    @Override
    public void showFetchingError(int errorMessage) {
        Toast.makeText(getActivity(), getStringMessage(errorMessage), Toast.LENGTH_SHORT).show();
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
