package com.mifos.mifosxdroid.online.attachmeeting;

import com.mifos.mifosxdroid.base.MvpView;
import com.mifos.objects.accounts.GroupAccounts;
import com.mifos.objects.client.Client;
import com.mifos.objects.group.Group;
import com.mifos.objects.noncore.DataTable;

import java.util.List;


/**
 * Created by Rajan Maurya on 07/06/16.
 */
public interface AttachMeetingMvpView extends MvpView {

    void loadFrequencyOptions(int index);

    void showFetchingError(int errorMessage);
}
