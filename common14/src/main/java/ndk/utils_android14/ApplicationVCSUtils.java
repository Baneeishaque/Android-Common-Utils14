package ndk.utils_android14;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

import ndk.utils_android1.DebugUtils;
import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.ToastUtils1;

public class ApplicationVCSUtils {

    public static void downloadAndInstallApk(String applicationName, float versionName, String updateUrl, final Context currentActivityContext) {

        //TODO : Use Permission Utils
        //get destination to update file and set Uri.
        //Download directory in external storage.
        String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        String fileName = applicationName + "_" + versionName + ".apk";
        destination += fileName;
        final Uri uri = Uri.parse("file://" + destination);

        //Delete update file if exists
        File file = new File(destination);
        if (file.exists()) {
            if (!file.delete()) {

                LogUtils1.debug(applicationName, "Deletion failure, please clear your downloads...", currentActivityContext);
                if (!DebugUtils.isDebugBuild(currentActivityContext)) {
                    ToastUtils1.longToast(currentActivityContext, "Deletion failure, please clear your downloads...");
                }
            }
        }

        //get url of app on server
        //set download manager
        LogUtils1.debug(applicationName, "Update URL : " + updateUrl, currentActivityContext);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updateUrl));
        request.setDescription("Downloading Update...");
        request.setTitle(applicationName + " " + versionName);

        //set destination
        request.setDestinationUri(uri);

        // get download service and enqueue file
        final DownloadManager downloadManager = (DownloadManager) currentActivityContext.getSystemService(Context.DOWNLOAD_SERVICE);
        final long downloadId = downloadManager.enqueue(request);

        //set BroadcastReceiver to install app when apk file is downloaded
        BroadcastReceiver downloadCompleteBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {

                InstallApk.installApk(uri, downloadManager, downloadId, context, this);
            }
        };

        //register receiver for when apk file download is compete
        currentActivityContext.registerReceiver(downloadCompleteBroadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
