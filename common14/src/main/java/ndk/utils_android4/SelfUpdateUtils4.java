package ndk.utils_android4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ndk.utils_android1.ToastUtils1;

public class SelfUpdateUtils4 {

    public static void checkForPermissionAndDownloadInstallApk(Context context, int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response! After the user sees the explanation, try again to request the permission.
                ToastUtils1.longToast(context, "Please give storage permission...");
                checkForPermissionAndDownloadInstallApk(context, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST);

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((AppCompatActivity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST);
            }
        } else {

            //TODO : Temporarily store wanted variables
//            storagePermissionGrantedAction(context, mProgressView, mLoginFormView, applicationName, flavourName, versionName, updatedApkUrl, applicationTag);
        }
    }

    public static void onStorageRequestPermissionResult(int requestCode, int[] grantResults) {

//        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST) {
//
//            // If request is cancelled, the result arrays are empty.
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                // permission was granted, yay! Do the related task you need to do.
////                storagePermissionGrantedAction(context, mProgressView, mLoginFormView, applicationName, flavourName, versionName, updatedApkUrl, applicationTag);
//
//            } else {
//
//                // permission denied, boo! Disable the functionality that depends on this permission.
////                Toast_Utils.longToast(context, "Please give storage permission...");
////                checkForPermissionAndDownloadInstallApk(context, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST);
//            }
//        }
    }
}
