package ndk.utils_android14;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.github.kimkevin.cachepot.CachePot;

import ndk.utils_android1.ActivityUtils1;

public class ActivityUtils14 {

    public static void startActivityForClassWithFinish(Context currentActivityContext, Class nextActivityClass) {

        ActivityUtils1.startActivityForClass(currentActivityContext, nextActivityClass);
        ((AppCompatActivity) currentActivityContext).finish();
    }

    public static void startActivityForIntentWithFinish(Context currentActivityContext, Intent nextActivityIntent) {

        ActivityUtils1.startActivityForIntent(currentActivityContext, nextActivityIntent);
        ((AppCompatActivity) currentActivityContext).finish();
    }

    public static void startActivityWithObjectPushAndFinish(Context activityContext, Class activity, Object objectToPush) {

        CachePot.getInstance().push(objectToPush);
        startActivityForClassWithFinish(activityContext, activity);
    }

    public static void startActivityWithObjectPush(Context activityContext, Class activity, Object objectToPush) {

        CachePot.getInstance().push(objectToPush);
        ActivityUtils1.startActivityForClass(activityContext, activity);
    }
}
