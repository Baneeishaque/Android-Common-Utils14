package ndk.utils_android1;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import ndk.utils_android14.BuildConfig;

public class LogUtils1 {

    public static void debug(String tag, String message) {

        if (BuildConfig.DEBUG) {

            Log.d(tag, message);
        }
    }

    public static void debugOnGui(String applicationTag, String message, Context currentApplicationContext) {

        if (BuildConfig.DEBUG) {

            ToastUtils1.longToast(currentApplicationContext, message);
        }
        debug(applicationTag, message + " on " + currentApplicationContext.getClass().getName());
    }

    public static void debugDataSnapshotOnGui(DataSnapshot dataSnapshot, Context currentApplicationContext, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, boolean isValuePrintActive, Class dataSnapshotValueClass, boolean isGenericType, GenericTypeIndicator genericTypeIndicator) {

        if (dataSnapshot == null) {

            debugOnGui(applicationTag, "DataSnapshot is empty", currentApplicationContext);

        } else {

            if (isDataSnapshotPrintActive) {

                debugOnGui(applicationTag, "DataSnapshot : " + dataSnapshot, currentApplicationContext);
            }

            debugOnGui(applicationTag, "DataSnapshot Exists : " + dataSnapshot.exists(), currentApplicationContext);

            if (isKeyPrintActive) {

                debugOnGui(applicationTag, "DataSnapshot Key : " + dataSnapshot.getKey(), currentApplicationContext);
            }
            if (isValuePrintActive && (!isGenericType) && dataSnapshot.exists()) {

                debugOnGui(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(dataSnapshotValueClass), currentApplicationContext);
            }
            if (isGenericType && dataSnapshot.exists()) {

                debugOnGui(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(genericTypeIndicator), currentApplicationContext);
            }
        }
    }

    public static void debugDataSnapshot(DataSnapshot dataSnapshot, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, boolean isValuePrintActive, Class<Object> dataSnapshotValueClass, boolean isGenericType, GenericTypeIndicator genericTypeIndicator) {

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
            if (isValuePrintActive && (!isGenericType) && dataSnapshot.exists()) {

                debug(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(dataSnapshotValueClass));
            }
            if (isGenericType && dataSnapshot.exists()) {

                debug(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(genericTypeIndicator));
            }
        }
    }
}
