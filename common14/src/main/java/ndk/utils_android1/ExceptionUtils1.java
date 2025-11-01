package ndk.utils_android1;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class ExceptionUtils1 {

    @NonNull
    public static String getExceptionDetails(@NonNull Exception e) {

        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception : " + e;
    }

    public static void handleException(boolean isGuiPresent, Context applicationContext, final String tag, String exceptionDetails) {

        if (isGuiPresent) {

            ToastUtils1.errorToast(applicationContext, tag);
            LogUtils1.debugOnGui(tag, exceptionDetails, applicationContext);

        } else {

            LogUtils1.debug(tag, exceptionDetails, applicationContext);
        }
    }

    public static void handleException(boolean isGuiPresent, Context applicationContext, final String tag, Exception exception) {

        handleException(isGuiPresent, applicationContext, tag, getExceptionDetails(exception));
    }

    public static void handleExceptionOnGui(Context applicationContext, final String tag, String exceptionDetails) {

        handleException(true, applicationContext, tag, exceptionDetails);
    }

    public static void handleExceptionOnGui(Context applicationContext, final String tag, Exception exception) {

        handleExceptionOnGui(applicationContext, tag, getExceptionDetails(exception));
    }
}
