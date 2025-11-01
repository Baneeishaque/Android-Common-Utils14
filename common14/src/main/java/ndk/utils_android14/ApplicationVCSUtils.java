package ndk.utils_android14;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.ToastUtils1;

public class ApplicationVCSUtils {


    public static void downloadAndInstallApk(String applicationName, float versionName, String updateUrl, final Context currentActivityContext) {

        //TODO : Use Permission Utils
        String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        String fileName = applicationName + "_" + versionName + ".apk";
        destination += fileName;
        final Uri uri = Uri.parse("file://" + destination);

        File file = new File(destination);
        if (file.exists()) {

            if (!file.delete()) {

                String DELETION_FAILURE_PLEASE_CLEAR_YOUR_DOWNLOADS = "Deletion failure, please clear your downloads...";
                ToastUtils1.longToast(currentActivityContext, DELETION_FAILURE_PLEASE_CLEAR_YOUR_DOWNLOADS);
                LogUtils1.debug(applicationName, DELETION_FAILURE_PLEASE_CLEAR_YOUR_DOWNLOADS, currentActivityContext);
            }
        }

        LogUtils1.debugOnGui(applicationName, "Update URL : " + updateUrl, currentActivityContext);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updateUrl));
        request.setDescription("Downloading Update...");
        request.setTitle(applicationName + " " + versionName);

        request.setDestinationUri(uri);

        final DownloadManager downloadManager = (DownloadManager) currentActivityContext.getSystemService(Context.DOWNLOAD_SERVICE);
        final long downloadId = downloadManager.enqueue(request);

        BroadcastReceiver downloadCompleteBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {

                InstallApk.installApk(uri, downloadManager, downloadId, context, this);
            }
        };

        currentActivityContext.registerReceiver(downloadCompleteBroadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
