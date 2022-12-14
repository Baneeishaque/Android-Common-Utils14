package ndk.utils_android14;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.snackbar.Snackbar;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.NetworkUtils1;
import ndk.utils_android1.TextClearUtils;
import ndk.utils_android1.ToastUtils1;

import static android.graphics.Color.RED;

public class NetworkUtils14 {

    private static FurtherActions furtherActions;

    public static void displayOfflineLongNoFabBottomSnackBar(View view, View.OnClickListener networkFunction) {
        Snackbar snackbar = Snackbar.make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE).setAction("Retry",
                networkFunction);
        snackbar.getView().setBackgroundColor(RED);
        snackbar.show();
    }

    public static void handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(String[] networkActionResponseArray, AppCompatActivity currentActivity, Class toSwitchActivity, EditText[] editTextsToClear, View viewToFocusOnError, String tag, int actionFlag, Pair[] nextClassExtras, FurtherActions furtherActions) {

        NetworkUtils14.furtherActions = furtherActions;

        LogUtils1.debug(tag, "Network Action Response Index 0 : " + networkActionResponseArray[0], currentActivity);
        LogUtils1.debug(tag, "Network Action Response Index 1 : " + networkActionResponseArray[1], currentActivity);

        if (networkActionResponseArray[0].equals("1")) {

            Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
            LogUtils1.debug(tag, "Error, Network Action Response Index 1 : " + networkActionResponseArray[1], currentActivity);

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
                                LogUtils1.debug(tag, "Further Action...", currentActivity);
                                furtherActions.onSuccess();
                                break;

                            case 6: // clear fields & further actions
                                LogUtils1.debug(tag, "Further Action...", currentActivity);
                                TextClearUtils.resetFields(editTextsToClear);
                                furtherActions.onSuccess();
                                break;
                        }
                        break;

                    case "1":
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils1.debug(tag, "Error : " + json.getString("error"), currentActivity);
                        viewToFocusOnError.requestFocus();
                        break;

                    default:
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils1.debug(tag, "Error : Application_Utils json", currentActivity);
                }
            } catch (JSONException e) {
                Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                LogUtils1.debug(tag, "Error : " + e.getLocalizedMessage(), currentActivity);
            }
        }
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

                //TODO : Use HttpPostUtils
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

    public static String[] performHttpClientPostTask(String url, ArrayList<org.javatuples.Pair<String, String>> namePairValuesInJavaTuples) {

        try {
            DefaultHttpClient defaultHttpClient;
            HttpPost httpPost;
            ArrayList<NameValuePair> nameValuePairs;
            String networkActionResponse;

            defaultHttpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);

            if (namePairValuesInJavaTuples.size() != 0) {

                //TODO : Use HttpPostUtils
                nameValuePairs = new ArrayList<>(namePairValuesInJavaTuples.size());

                for (org.javatuples.Pair<String, String> namePairValueInJavaTuples : namePairValuesInJavaTuples) {

                    nameValuePairs.add(new BasicNameValuePair(namePairValueInJavaTuples.getValue0(), namePairValueInJavaTuples.getValue1()));
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
        if (NetworkUtils1.isOnline(context)) {
            ActivityUtils14.startActivityForClassWithStringExtras(context, activity, extras);
        } else {
            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void startActivityWithNetworkAndStringExtras(Context activityContext, Class activity, Pair[] stringExtras) {

        if (NetworkUtils1.isOnline(activityContext)) {

            ActivityUtils14.startActivityForClassWithStringExtras(activityContext, activity, stringExtras);

        } else {

            ToastUtils1.longToast(activityContext, "Internet is unavailable...");
        }
    }

    public static void startActivityWithNetworkStringExtrasAndRequestCode(Context activityContext, Class activity, Pair[] stringExtras, int requestCode) {

        if (NetworkUtils1.isOnline(activityContext)) {
            ActivityUtils14.startActivityForResultWithStringExtras(activityContext, activity, stringExtras, requestCode);
        } else {
            ToastUtils1.longToast(activityContext, "Internet is unavailable...");
        }
    }

    void handleJsonInsertionResponseAndSwitchWithFinishAndToggleView(String[] networkActionResponseArray, Class toSwitchActivity, View viewToFocusOnError, View viewToToggle, String tag, AppCompatActivity currentActivity) {

        handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, toSwitchActivity, new EditText[]{}, viewToFocusOnError, tag, 1, new Pair[]{}, furtherActions);
        viewToToggle.setEnabled(true);
    }

    public interface FurtherActions {

        void onSuccess();
    }
}
