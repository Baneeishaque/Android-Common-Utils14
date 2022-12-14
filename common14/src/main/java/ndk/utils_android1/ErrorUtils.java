package ndk.utils_android1;

import android.content.Context;

import org.json.JSONObject;

public class ErrorUtils {

    public static void displayException(Context currentActivityContext, Exception exception, String applicationName) {

        ToastUtils1.errorToast(currentActivityContext, applicationName);
        LogUtils1.debug(applicationName, ExceptionUtils1.getExceptionDetails(exception), currentActivityContext);
    }

    public static void displayJSONFieldMiss(Context currentActivityContext, JSONObject jsonObject, String applicationName) {

        ToastUtils1.errorToast(currentActivityContext, applicationName);
        LogUtils1.debug(applicationName, "Error, Check JSON : " + jsonObject, currentActivityContext);
    }
}
