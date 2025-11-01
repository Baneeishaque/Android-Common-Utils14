package ndk.utils_android1;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

public class LogUtils1 {

    public static void debug(String tag, String message, Context currentApplicationContext) {

        if (DebugUtils.isDebugBuild(currentApplicationContext)) {

            Log.d(tag, message + " on " + currentApplicationContext.getClass().getName());
        }
    }

    public static void debugOnGui(String applicationTag, String message, Context currentApplicationContext) {

        if (DebugUtils.isDebugBuild(currentApplicationContext)) {

            ToastUtils1.longToast(currentApplicationContext, message);
        }
        debug(applicationTag, message, currentApplicationContext);
    }

    public static void extendedDebug(String tag, String message, Context currentApplicationContext, boolean isGuiAvailable) {

        if (isGuiAvailable) {

            debugOnGui(tag, message, currentApplicationContext);

        } else {

            debug(tag, message, currentApplicationContext);
        }
    }

    public static <T> void debugDataSnapshotOnGui(DataSnapshot dataSnapshot, Context currentApplicationContext, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, boolean isValuePrintActive, Class<T> dataSnapshotValueClass, boolean isGenericType, GenericTypeIndicator<T> genericTypeIndicator) {

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

    public static <T> void debugDataSnapshot(DataSnapshot dataSnapshot, String applicationTag, boolean isDataSnapshotPrintActive, boolean isKeyPrintActive, boolean isValuePrintActive, Class<Object> dataSnapshotValueClass, boolean isGenericType, GenericTypeIndicator<T> genericTypeIndicator, Context currentApplicationContext) {

        if (dataSnapshot == null) {

            debug(applicationTag, "DataSnapshot is empty", currentApplicationContext);

        } else {

            if (isDataSnapshotPrintActive) {

                debug(applicationTag, "DataSnapshot : " + dataSnapshot, currentApplicationContext);
            }

            debug(applicationTag, "DataSnapshot Exists : " + dataSnapshot.exists(), currentApplicationContext);

            if (isKeyPrintActive) {

                debug(applicationTag, "DataSnapshot Key : " + dataSnapshot.getKey(), currentApplicationContext);
            }
            if (isValuePrintActive && (!isGenericType) && dataSnapshot.exists()) {

                debug(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(dataSnapshotValueClass), currentApplicationContext);
            }
            if (isGenericType && dataSnapshot.exists()) {

                debug(applicationTag, "DataSnapshot Value : " + dataSnapshot.getValue(genericTypeIndicator), currentApplicationContext);
            }
        }
    }
}
