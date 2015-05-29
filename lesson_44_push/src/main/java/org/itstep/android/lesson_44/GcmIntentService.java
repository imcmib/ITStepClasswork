package org.itstep.android.lesson_44;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class GcmIntentService extends IntentService {
    private static final String TAG = GcmIntentService.class.getSimpleName();

    public GcmIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            GcmBroadcastReceiver.completeWakefulIntent(intent);
            return;
        }

        // TODO [aivanchenko | 29.05.2015 16:37]
        for (String key : extras.keySet()) {
            Log.i(TAG, key + ": " + extras.get(key));
        }
        Log.v(TAG, "===================================================");

        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}
