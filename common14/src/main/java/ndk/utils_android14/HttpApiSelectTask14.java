package ndk.utils_android14;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.NetworkUtils1;
import ndk.utils_android1.ProgressBarUtils1;
import ndk.utils_android1.ToastUtils1;

// TODO : Integrate with ndk.utils_android3.DbSelect

public class HttpApiSelectTask14 extends AsyncTask<Void, Void, String[]> {

    //Default : Foreground Task
    boolean isBackgroundTask = false;

    //true - enables status check on asyncResponseJsonArray
    //Status Check, checks jsonArray[0].jsonObject.status
    //Status Check, status = 1 error with jsonArray[0].jsonObject.error & jsonArray[0].jsonObject.error_number
    //Status Check, status = 2 no entries
    //Status Check, status = 0 no error
    //Status Check, status = other error
    //false - disables status check on asyncResponseJsonArray
    //Default : true
    boolean isStatusCheckOnAsyncResponseJsonArrayFirstElementEnabled = true;

    AsyncResponseJSONArray asyncResponseJSONArray;
    AsyncResponseJSONObject asyncResponseJSONObject = null;
    AsyncResponse asyncResponse = null;

    private String url;

    private String applicationName;

    private Context currentActivityContext;

    private View progressBar;
    private View form;

    //TODO : Migrate to Java Tuples
    private Pair[] nameValuePairs = new Pair[]{};

    //progressFlag = 0 means has progressBar
    //progressFlag = nonZero means no progressBar
    //Default 0
    //TODO : Make boolean variable
    private int progressFlag = 0;

    //responseFlag = 1 means asyncResponse
    //responseFlag = 2 means asyncResponseJsonObject
    //responseFlag = 0 means asyncResponseJsonArray
    //Default 0
    private int responseFlag = 0;

    //splashFlag = 1 means has splash screen
    //splashFlag = 0 means no splash screen
    //Default 0
    //TODO : Make boolean variable
    private int splashFlag = 0;

    boolean isJavaTuplesUsedForNameValuePairs = false;
    ArrayList<org.javatuples.Pair<String, String>> nameValuePairsInJavaTuples = new ArrayList<>();

    private boolean isCustomizedNoEntriesMessagePresent = false;
    private String customizedNoEntriesMessage = "";

    NoEntriesActions noEntriesActions;

    public HttpApiSelectTask14(String url, Pair[] nameValuePairs, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONArray asyncResponseJSONArray, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.noEntriesActions = noEntriesActions;
    }

    public HttpApiSelectTask14(String url, Pair[] nameValuePairs, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONArray asyncResponseJSONArray, String customizedNoEntriesMessage, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.isCustomizedNoEntriesMessagePresent = true;
        this.customizedNoEntriesMessage = customizedNoEntriesMessage;
        this.noEntriesActions = noEntriesActions;
    }

    public HttpApiSelectTask14(String url, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONArray asyncResponseJSONArray, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.noEntriesActions = noEntriesActions;
    }

    public HttpApiSelectTask14(String url, Pair[] nameValuePairs, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONArray asyncResponseJSONArray, boolean isStatusCheckOnAsyncResponseJsonArrayFirstElementEnabled, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.isStatusCheckOnAsyncResponseJsonArrayFirstElementEnabled = isStatusCheckOnAsyncResponseJsonArrayFirstElementEnabled;
        this.noEntriesActions = noEntriesActions;
    }

    public HttpApiSelectTask14(String url, Pair[] nameValuePairs, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONObject asyncResponseJSONObject, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONObject = asyncResponseJSONObject;
        this.responseFlag = 2;
        this.noEntriesActions = noEntriesActions;
    }

    public HttpApiSelectTask14(String url, ArrayList<org.javatuples.Pair<String, String>> nameValuePairsInJavaTuples, Context currentActivityContext, View progressBar, View form, String applicationName, AsyncResponseJSONObject asyncResponseJSONObject, NoEntriesActions noEntriesActions) {

        this.url = url;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.form = form;
        this.applicationName = applicationName;
        this.nameValuePairsInJavaTuples = nameValuePairsInJavaTuples;
        this.asyncResponseJSONObject = asyncResponseJSONObject;
        this.responseFlag = 2;
        this.isJavaTuplesUsedForNameValuePairs = true;
        this.noEntriesActions = noEntriesActions;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        if (isJavaTuplesUsedForNameValuePairs) {

            LogUtils1.debug(applicationName, "Url : " + url + "\n" + HttpPostUtils14.toStringOnNameValuePairsInJavaTuples(nameValuePairsInJavaTuples), currentActivityContext);
            return NetworkUtils14.performHttpClientPostTask(url, nameValuePairsInJavaTuples);

        } else {

            LogUtils1.debug(applicationName, "Url : " + url + "\n" + HttpPostUtils14.toStringOnNameValuePairs(nameValuePairs), currentActivityContext);
            return NetworkUtils14.performHttpClientPostTask(url, nameValuePairs);
        }
    }

    protected void onPostExecute(String[] networkActionResponseArray) {

        if (progressFlag == 0) {

            ProgressBarUtils1.showProgress(false, currentActivityContext, progressBar, form);
        }

        if (responseFlag == 1) {

            Log.d(applicationName, "Network Action status is " + networkActionResponseArray[0]);
            Log.d(applicationName, "Network Action response is " + networkActionResponseArray[1]);

            if (networkActionResponseArray[0].equals("1")) {

                NetworkUtils1.displayFriendlyExceptionMessage(currentActivityContext, networkActionResponseArray[1]);
                Log.d(applicationName, "Network Action response is " + networkActionResponseArray[1]);
                asyncResponse.processFinish("exception");

            } else {

                asyncResponse.processFinish(networkActionResponseArray[1]);
            }

        } else if (responseFlag == 2) {

            Log.d(applicationName, "Network Action status is " + networkActionResponseArray[0]);
            Log.d(applicationName, "Network Action response is " + networkActionResponseArray[1]);

            if (networkActionResponseArray[0].equals("1")) {

                NetworkUtils1.displayFriendlyExceptionMessage(currentActivityContext, networkActionResponseArray[1]);
                Log.d(applicationName, "Network Action response is " + networkActionResponseArray[1]);

            } else {

                try {

                    JSONObject json_object = new JSONObject(networkActionResponseArray[1]);
                    asyncResponseJSONObject.processFinish(json_object);

                } catch (JSONException var3) {

                    ToastUtils1.longToast(currentActivityContext, "Error...");
                    Log.d(applicationName, "Error : " + var3.getLocalizedMessage());
                }
            }
        } else {

            Log.d(applicationName, "Network Action Response Array 0 : " + networkActionResponseArray[0]);
            Log.d(applicationName, "Network Action Response Array 1 : " + networkActionResponseArray[1]);

            if (networkActionResponseArray[0].equals("1")) {

                if (isBackgroundTask) {

                    Log.d(applicationName, "Error...");

                } else {

                    ToastUtils1.longToast(currentActivityContext, "Error...");
                }

                Log.d(applicationName, "Network Action Response Array 1 : " + networkActionResponseArray[1]);

                if (splashFlag == 1) {

                    ((AppCompatActivity) currentActivityContext).finish();
                }

            } else {

                try {

                    JSONArray json_array = new JSONArray(networkActionResponseArray[1]);

                    if (splashFlag != 1 && isStatusCheckOnAsyncResponseJsonArrayFirstElementEnabled) {

                        switch (json_array.getJSONObject(0).getString("status")) {

                            case "1":

                                ToastUtils1.longToast(currentActivityContext, "Error...");
                                LogUtils1.debug(applicationName, "Error : " + json_array.getJSONObject(0).getInt("error_number") + ", " + json_array.getJSONObject(0).getInt("error"), currentActivityContext);
                                break;

                            case "2":

                                if (isBackgroundTask) {

                                    if (isCustomizedNoEntriesMessagePresent) {

                                        LogUtils1.debug(applicationName, customizedNoEntriesMessage, currentActivityContext);

                                    } else {

                                        LogUtils1.debug(applicationName, "No Entries...", currentActivityContext);
                                    }
                                } else {

                                    if (isCustomizedNoEntriesMessagePresent) {

                                        ToastUtils1.longToast(currentActivityContext, customizedNoEntriesMessage);

                                    } else {

                                        ToastUtils1.longToast(currentActivityContext, "No Entries...");
                                    }

                                }
                                noEntriesActions.processNoEntriesSituation();
                                break;

                            case "0":

                                asyncResponseJSONArray.processFinish(json_array);
                                break;

                            default:

                                ToastUtils1.longToast(currentActivityContext, "Error...");
                                LogUtils1.debug(applicationName, "Response : " + json_array, currentActivityContext);
                                break;
                        }
                    } else {

                        asyncResponseJSONArray.processFinish(json_array);
                    }

                } catch (JSONException e) {

                    ToastUtils1.longToast(currentActivityContext, "Error...");
                    LogUtils1.debug(applicationName, "Error : " + e.getLocalizedMessage(), currentActivityContext);
                }
            }
        }
    }

    protected void onCancelled() {

        if (progressFlag == 0) {

            ProgressBarUtils1.showProgress(false, currentActivityContext, progressBar, form);
        }
    }

    public interface AsyncResponseJSONArray {

        void processFinish(JSONArray jsonArray);
    }

    public interface AsyncResponseJSONObject {

        void processFinish(JSONObject var1);
    }

    public interface AsyncResponse {

        void processFinish(String var1);
    }

    public interface NoEntriesActions {

        void processNoEntriesSituation();
    }
}
