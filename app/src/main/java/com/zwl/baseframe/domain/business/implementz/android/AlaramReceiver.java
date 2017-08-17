package com.zwl.baseframe.domain.business.implementz.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zwl.baseframe.R;
import com.zwl.baseframe.domain.ui.notification.WordNotification;

/**
 * Created by hasee on 2017/8/9.
 */

public class AlaramReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        WordNotification.notify(context, context.getString(R.string.word_notification_placeholder_text_template), 0);
    }
}
