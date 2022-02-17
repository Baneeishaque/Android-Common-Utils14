package ndk.utils_android14;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ndk.utils_android1.LogUtils1;

public class VolleyUtils {

    public static void performVolleyStringRequestOfNoRetriesAndResolvedIn5Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, 5000, applicationName);
    }

    public static void performVolleyJsonObjectRequestOfNoRetriesAndResolvedIn5Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyJsonObjectRequestSuccessResponseActions volleyJsonObjectRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        performVolleyJsonObjectRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyJsonObjectRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, 5000, applicationName);
    }

    public static void performVolleyStringGetRequestOfNoRetriesAndResolvedIn5Seconds(Context currentActivityContext, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedIn5Seconds(currentActivityContext, Request.Method.GET, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, applicationName);
    }

    public static void performVolleyJsonObjectGetRequestOfNoRetriesAndResolvedIn5Seconds(Context currentActivityContext, String volleyRequestUrl, @NonNull VolleyJsonObjectRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        performVolleyJsonObjectRequestOfNoRetriesAndResolvedIn5Seconds(currentActivityContext, Request.Method.GET, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, applicationName);
    }

    public static void performVolleyStringGetRequestOfNoRetriesResolvedIn5SecondsAndWithoutParameters(Context currentActivityContext, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, String applicationName) {

        performVolleyStringGetRequestOfNoRetriesAndResolvedIn5Seconds(currentActivityContext, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, null, applicationName);
    }

    public static void performVolleyStringPostRequestOfNoRetriesAndResolvedIn5Seconds(Context currentActivityContext, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, HashMap<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedIn5Seconds(currentActivityContext, Request.Method.POST, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, applicationName);
    }

    public static void performVolleyStringRequestOfNoRetriesAndResolvedIn10Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, HashMap<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, 10000, applicationName);
    }

    public static void performVolleyStringRequestOfNoRetriesAndResolvedIn15Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, HashMap<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, 15000, applicationName);
    }

    public static void performVolleyStringRequestOfNoRetriesAndResolvedIn20Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, HashMap<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, 20000, applicationName);
    }

    public static void performVolleyStringRequestOfNoRetriesAndResolvedIn25Seconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, HashMap<String, String> volleyRequestParameters, String applicationName) {

        performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, applicationName);
    }

    public static void performVolleyStringRequestOfNoRetriesAndResolvedInCustomSeconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, int volleyRequestTimeoutInMilliSeconds, String applicationName) {

        StringRequest stringRequest;
        if (volleyRequestMethod == Request.Method.GET) {

            if (volleyRequestParameters != null && volleyRequestParameters.size() > 0) {

                stringRequest = constructStringRequest(currentActivityContext, volleyRequestMethod, generateGetUrlWithParameters(volleyRequestUrl, volleyRequestParameters).toString(), volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, null, applicationName);

            } else {
                stringRequest = constructStringRequest(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, null, applicationName);
            }
        } else {
            stringRequest = constructStringRequest(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, applicationName);
        }
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(volleyRequestTimeoutInMilliSeconds, 0, 0));
        VolleySingleton.getInstance(currentActivityContext).addToRequestQueue(stringRequest);
    }

    public static void performVolleyJsonObjectRequestOfNoRetriesAndResolvedInCustomSeconds(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyJsonObjectRequestSuccessResponseActions volleyJsonObjectRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, int volleyRequestTimeoutInMilliSeconds, String applicationName) {

        JsonObjectRequest jsonObjectRequest;
        if (volleyRequestMethod == Request.Method.GET) {

            if (volleyRequestParameters.size() > 0) {

                jsonObjectRequest = constructJsonObjectRequest(currentActivityContext, volleyRequestMethod, generateGetUrlWithParameters(volleyRequestUrl, volleyRequestParameters).toString(), volleyJsonObjectRequestSuccessResponseActions, volleyRequestErrorResponseActions, null, applicationName);

            } else {
                jsonObjectRequest = constructJsonObjectRequest(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyJsonObjectRequestSuccessResponseActions, volleyRequestErrorResponseActions, null, applicationName);
            }
        } else {
            jsonObjectRequest = constructJsonObjectRequest(currentActivityContext, volleyRequestMethod, volleyRequestUrl, volleyJsonObjectRequestSuccessResponseActions, volleyRequestErrorResponseActions, volleyRequestParameters, applicationName);
        }
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(volleyRequestTimeoutInMilliSeconds, 0, 0));
        VolleySingleton.getInstance(currentActivityContext).addToRequestQueue(jsonObjectRequest);
    }

    @NonNull
    private static StringBuilder generateGetUrlWithParameters(String volleyRequestUrl, Map<String, String> volleyRequestParameters) {

        boolean isFirstParameter = true;
        StringBuilder volleyRequestUrlBuilder = new StringBuilder(volleyRequestUrl);
        for (Map.Entry<String, String> entry : volleyRequestParameters.entrySet()) {
            String parameterName = entry.getKey();
            String parameterValue = entry.getValue();
            if (isFirstParameter) {
                volleyRequestUrlBuilder.append("?").append(parameterName).append("=").append(parameterValue);
                isFirstParameter = false;
            } else {
                volleyRequestUrlBuilder.append("&").append(parameterName).append("=").append(parameterValue);
            }
        }
        return volleyRequestUrlBuilder;
    }

    @NonNull
    private static StringRequest constructStringRequest(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyStringRequestSuccessResponseActions volleyStringRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        return new StringRequest(volleyRequestMethod, volleyRequestUrl, volleyStringRequestSuccessResponseActions::actions, volleyRequestErrorResponseActions::actions) {
            @Override
            protected Map<String, String> getParams() {
                LogUtils1.debugOnGui(applicationName, "getParams : " + volleyRequestParameters, currentActivityContext);
                return volleyRequestParameters;
            }
        };
    }

    @NonNull
    private static JsonObjectRequest constructJsonObjectRequest(Context currentActivityContext, int volleyRequestMethod, String volleyRequestUrl, @NonNull VolleyJsonObjectRequestSuccessResponseActions volleyJsonObjectRequestSuccessResponseActions, @NonNull VolleyRequestErrorResponseActions volleyRequestErrorResponseActions, Map<String, String> volleyRequestParameters, String applicationName) {

        return new JsonObjectRequest(volleyRequestMethod, volleyRequestUrl, null, volleyJsonObjectRequestSuccessResponseActions::actions, volleyRequestErrorResponseActions::actions) {
            @Override
            protected Map<String, String> getParams() {
                LogUtils1.debugOnGui(applicationName, "getParams : " + volleyRequestParameters, currentActivityContext);
                return volleyRequestParameters;
            }
        };
    }
}
