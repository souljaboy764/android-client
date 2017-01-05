package com.mifos.mifosxdroid.online.attachmeeting;

import android.util.Log;

import com.mifos.api.datamanager.DataManagerCenter;
import com.mifos.api.datamanager.DataManagerDataTable;
import com.mifos.api.datamanager.DataManagerGroups;
import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.base.BasePresenter;
import com.mifos.objects.accounts.GroupAccounts;
import com.mifos.objects.group.Group;
import com.mifos.objects.group.GroupWithAssociations;
import com.mifos.objects.noncore.DataTable;
import com.mifos.objects.templates.loans.MeetingCalendarTemplate;
import com.mifos.objects.zipmodels.GroupAndGroupAccounts;
import com.mifos.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rajan Maurya on 07/06/16.
 */
public class AttachMeetingPresenter extends BasePresenter<AttachMeetingMvpView> {

    private final DataManagerGroups mDataManagerGroups;
    private final DataManagerCenter mDataManagerCenter;
    private CompositeSubscription mSubscriptions;

    private final String LOG_TAG = getClass().getName();

    @Inject
    public AttachMeetingPresenter(DataManagerGroups dataManagerGroups,
                                  DataManagerCenter dataManagerCenter) {
        mDataManagerGroups = dataManagerGroups;
        mDataManagerCenter = dataManagerCenter;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void attachView(AttachMeetingMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscriptions.clear();
    }

    public void loadGroupMeetingCalenderTemplate(int groupId) {
        mSubscriptions.add(mDataManagerGroups.getMeetingCalenderTemplate(groupId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MeetingCalendarTemplate>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MeetingCalendarTemplate meetingCalendarTemplate) {
                        Log.d(LOG_TAG, meetingCalendarTemplate.toString());
                    }
                })
        );
    }

}
