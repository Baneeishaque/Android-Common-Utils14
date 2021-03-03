package ndk.utils_android14;

import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import androidx.core.util.Pair;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONException;

import static android.graphics.Color.RED;

import ndk.utils_android1.LogUtils;
import ndk.utils_android1.TextClearUtils;
import ndk.utils_android1.NetworkUtils;
import ndk.utils_android1.ToastUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.ClientProtocolException;

import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class NetworkUtils14 {
    
    private static FurtherActions furtherActions;

    public static void displayOfflineLongNoFabBottomSnackBar(View view, View.OnClickListener networkFunction) {
        Snackbar snackbar = Snackbar.make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE).setAction("Retry",
                networkFunction);
        snackbar.getView().setBackgroundColor(RED);
        snackbar.show();
    }

    public interface FurtherActions {

        void onSuccess();
    }

    public static void handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(String[] networkActionResponseArray, AppCompatActivity currentActivity, Class toSwitchActivity, EditText[] editTextsToClear, View viewToFocusOnError, String tag, int actionFlag, Pair[] nextClassExtras, FurtherActions furtherActions) {

        NetworkUtils14.furtherActions = furtherActions;

        LogUtils.debug(tag, "Network Action Response Index 0 : " + networkActionResponseArray[0]);
        LogUtils.debug(tag, "Network Action Response Index 1 : " + networkActionResponseArray[1]);

        if (networkActionResponseArray[0].equals("1")) {

            Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
            LogUtils.debug(tag, "Error, Network Action Response Index 1 : " + networkActionResponseArray[1]);

        } else {

            try {
                JSONObject json = new JSONObject(networkActionResponseArray[1]);

                switch (json.getString("status")) {

                    case "0":

                        Toast.makeText(currentActivity, "OK", Toast.LENGTH_LONG).show();

                        switch (actionFlag) {

                            case 1: // finish and switch
                                ActivityUtils14.startActivityForClassWithFinish(currentActivity, toSwitchActivity);
                                break;

                            case 2: // clear fields
                                TextClearUtils.resetFields(editTextsToClear);
                                break;

                            case 3: // self finish
                                currentActivity.finish();
                                break;

                            case 4: // finish and switch with extras
                                ActivityUtils14.startActivityWithStringExtrasAndFinish(currentActivity, toSwitchActivity, nextClassExtras);
                                break;

                            case 5: // No Action
                                LogUtils.debug(tag, "Further Action...");
                                furtherActions.onSuccess();
                                break;

                            case 6: // clear fields & further actions
                                LogUtils.debug(tag, "Further Action...");
                                TextClearUtils.resetFields(editTextsToClear);
                                furtherActions.onSuccess();
                                break;
                        }
                        break;

                    case "1":
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils.debug(tag, "Error : " + json.getString("error"));
                        viewToFocusOnError.requestFocus();
                        break;

                    default:
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils.debug(tag, "Error : Application_Utils json");
                }
            } catch (JSONException e) {
                Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                LogUtils.debug(tag, "Error : " + e.getLocalizedMessage());
            }
        }
    }

    void handleJsonInsertionResponseAndSwitchWithFinishAndToggleView(String[] networkActionResponseArray, Class toSwitchActivity, View viewToFocusOnError, View viewToToggle, String tag, AppCompatActivity currentActivity) {

        handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, toSwitchActivity, new EditText[]{}, viewToFocusOnError, tag, 1, new Pair[]{}, furtherActions);
        viewToToggle.setEnabled(true);
    }

        public static String[] performHttpClientPostTask(String url, Pair[] namePairValues) {

        try {
            DefaultHttpClient defaultHttpClient;
            HttpPost httpPost;
            ArrayList<NameValuePair> nameValuePairs;
            String networkActionResponse;

            defaultHttpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);

            if (namePairValues.length != 0) {

                nameValuePairs = new ArrayList<>(namePairValues.length);

                for (Pair namePairValue : namePairValues) {

                    nameValuePairs.add(new BasicNameValuePair(namePairValue.first != null ? namePairValue.first.toString() : null, namePairValue.second != null ? namePairValue.second.toString() : null));
                }

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {

            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};

        } catch (ClientProtocolException e) {

            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};

        } catch (IOException e) {

            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }

    public static void checkNetworkThenStartActivityWithStringExtras(Context context, Class activity, Pair[] extras, boolean forResultFlag, int requestCode) {
        if (NetworkUtils.isOnline(context)) {
            ActivityUtils14.startActivityForClassWithStringExtras(context, activity, extras);
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void startActivityWithNetworkAndStringExtras(Context activityContext, Class activity, Pair[] stringExtras) {

        if (NetworkUtils.isOnline(activityContext)) {

            ActivityUtils14.startActivityForClassWithStringExtras(activityContext, activity, stringExtras);

        } else {

            ToastUtils.longToast(activityContext, "Internet is unavailable...");
        }
    }

    public static void startActivityWithNetworkStringExtrasAndRequestCode(Context activityContext, Class activity, Pair[] stringExtras, int requestCode) {

        if (NetworkUtils.isOnline(activityContext)) {
            ActivityUtils14.startActivityForResultWithStringExtras(activityContext, activity, stringExtras, requestCode);
        } else {
            ToastUtils.longToast(activityContext, "Internet is unavailable...");
        }
    }
}