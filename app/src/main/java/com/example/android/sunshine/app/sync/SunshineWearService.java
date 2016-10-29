package com.example.android.sunshine.app.sync;

import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.WearableListenerService;

public class SunshineWearService extends WearableListenerService {

    public static final String TAG = "SunshineWearService";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() != DataEvent.TYPE_CHANGED) {
                continue;
            }

            DataItem dataItem = dataEvent.getDataItem();
            if (!dataItem.getUri().getPath().equals("/sunshine-sync")) {
                continue;
            }

            SunshineSyncAdapter.syncImmediately(getApplicationContext());

            Log.d(TAG, "Sync forced by wear");

        }
    }
}
