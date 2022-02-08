package ndk.utils_android1;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.util.Objects;

import ndk.utils_android14.BuildConfig;

public class LogUtils1 {

    public static void debug(String tag, String message) {

        if (BuildConfig.DEBUG) {

            Log.d(tag, message);
        }
    }

    public static void debugOnGui(String message, Context currentApplicationContext, String applicationTag) {

        if (BuildConfig.DEBUG) {

            ToastUtils1.longToast(currentApplicationContext, message);
        }
        debug(applicationTag, message + " on " + currentApplicationContext.getClass().getName());
    }

    public static void debugDataSnapshotOnGui(DataSnapshot dataSnapshot, Context currentApplicationContext, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, Class dataSnapshotValueClass) {

        if (dataSnapshot == null) {
            debugOnGui("DataSnapshot is empty", currentApplicationContext, applicationTag);
        } else {
            if (isDataSnapshotPrintActive) {
                debugOnGui("DataSnapshot : " + dataSnapshot, currentApplicationContext, applicationTag);
            }
            debugOnGui("DataSnapshot Exists : " + dataSnapshot.exists(), currentApplicationContext, applicationTag);
            if (isKeyPrintActive) {
                debugOnGui("DataSnapshot Key : " + dataSnapshot.getKey(), currentApplicationContext, applicationTag);
            }
            debugOnGui("DataSnapshot Value : " + Objects.requireNonNull(dataSnapshot.getValue(dataSnapshotValueClass)), currentApplicationContext, applicationTag);
        }
    }

    public static void debugDataSnapshot(DataSnapshot dataSnapshot, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, Class<Object> dataSnapshotValueClass) {

        if (dataSnapshot == null) {
            debug(applicationTag, "DataSnapshot is empty");
        } else {
            if (isDataSnapshotPrintActive) {
                debug(applicationTag, "DataSnapshot : " + dataSnapshot);
            }
            debug(applicationTag, "DataSnapshot Exists : " + dataSnapshot.exists());
            if (isKeyPrintActive) {
                debug(applicationTag, "DataSnapshot Key : " + dataSnapshot.getKey());
            }
            debug(applicationTag, "DataSnapshot Value : " + Objects.requireNonNull(dataSnapshot.getValue(dataSnapshotValueClass)));
        }
    }
}
