package ndk.utils_android1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class UpdateUtils {

    public static int getVersionCode(Context context, String applicationTag, boolean isGuiAvailable) {

        PackageManager pm = context.getPackageManager();
        try {

            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);

            int versionCode = pi.versionCode;
            LogUtils1.extendedDebug(applicationTag, "Version Code : " + versionCode, context, isGuiAvailable);
            return versionCode;

        } catch (PackageManager.NameNotFoundException ex) {

            return 0;
        }
    }

    public static float getVersionName(Context context, String applicationTag, boolean isGuiAvailable) {

        PackageManager pm = context.getPackageManager();
        try {

            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);

            float versionName = Float.parseFloat(pi.versionName);
            LogUtils1.extendedDebug(applicationTag, "Version Name : " + versionName, context, isGuiAvailable);
            return versionName;

        } catch (PackageManager.NameNotFoundException ex) {

            return 0;
        }
    }

    public static String[] getFlavouredServerVersion(String flavour, String fullVersionCheckUrl, String applicationName, Context currentApplicationContext, boolean isGuiAvailable) {

        // TODO: Use Utils
        String networkActionResponse;
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(fullVersionCheckUrl);
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("flavour", flavour));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            LogUtils1.extendedDebug(applicationName, "Network Action Response : " + networkActionResponse, currentApplicationContext, isGuiAvailable);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {
            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};
        } catch (ClientProtocolException e) {
            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};
        } catch (IOException e) {
            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }

    public static String[] getServerVersion(String fullVersionCheckUrl, String applicationName, Context currentApplicationContext, boolean isGuiAvailable) {

        LogUtils1.extendedDebug(applicationName, "URL : " + fullVersionCheckUrl, currentApplicationContext, isGuiAvailable);

        // TODO: Use Utils
        String networkActionResponse;
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(fullVersionCheckUrl);

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            LogUtils1.extendedDebug(applicationName, "Network Action Response : " + networkActionResponse, currentApplicationContext, isGuiAvailable);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {

            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};

        } catch (ClientProtocolException e) {

            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};

        } catch (IOException e) {

            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }
}
