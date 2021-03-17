package ndk.utils_android14;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.github.kimkevin.cachepot.CachePot;

import java.util.Objects;

import ndk.utils_android1.ActivityUtils1;

public class ActivityUtils14 {

    public static Intent constructIntentWithStringExtras(Context currentActivityContext, Class nextActivityClass, Pair[] nextActivityExtras) {

        Intent intent = new Intent(currentActivityContext, nextActivityClass);
        if (nextActivityExtras.length != 0) {

            for (Pair extra : nextActivityExtras) {

                intent.putExtra(extra.first != null ? extra.first.toString() : null, extra.second != null ? extra.second.toString() : null);
            }
        }
        return intent;
    }

    public static void startActivityForClassWithStringExtras(Context currentActivityContext, Class nextActivityClass, Pair[] nextActivityExtras) {

        ActivityUtils1.startActivityForIntent(currentActivityContext, constructIntentWithStringExtras(currentActivityContext, nextActivityClass, nextActivityExtras));
    }

    public static void startActivityForResultWithStringExtras(Context activityContext, Class activity, Pair[] extras, int request_code) {

        ((AppCompatActivity) activityContext).startActivityForResult(constructIntentWithStringExtras(activityContext, activity, extras), request_code);
    }

    public static Intent getIntentWithIntegerExtras(Context context, Class activity, Pair[] extras) {
        Intent intent = new Intent(context, activity);
        if (extras.length != 0) {
            for (Pair extra : extras) {
                intent.putExtra(extra.first != null ? extra.first.toString() : null, Integer.parseInt(Objects.requireNonNull(extra.second != null ? extra.second.toString() : null)));
            }
        }
        return intent;
    }

    public static void startActivityWithIntegerExtras(Context activityContext, Class activity, Pair[] extras) {

        ActivityUtils1.startActivityForIntent(activityContext, getIntentWithIntegerExtras(activityContext, activity, extras));
    }

    public static void startActivityWithIntegerExtrasAndFinish(Context activityContext, Class activity, Pair[] extras) {

        startActivityForIntentWithFinish(activityContext, getIntentWithIntegerExtras(activityContext, activity, extras));
    }

    public static void startActivityWithFinishAndTabIndex(Context activityContext, Class activity, int tabIndex) {

        startActivityForIntentWithFinish(activityContext, getIntentWithIntegerExtras(activityContext, activity, new Pair[]{new Pair<>("tab_index", tabIndex)}));
    }

    public static void startActivityWithObjectPushAndIntegerExtras(Context activityContext, Class activity, Pair[] extras, Object objectToPush) {

        CachePot.getInstance().push(objectToPush);
        ActivityUtils1.startActivityForIntent(activityContext, getIntentWithIntegerExtras(activityContext, activity, extras));
    }

    public static void startActivityWithObjectPushAndIntegerExtrasAndFinish(Context activityContext, Class activity, Pair[] extras, Object objectToPush) {

        CachePot.getInstance().push(objectToPush);
        startActivityForIntentWithFinish(activityContext, getIntentWithIntegerExtras(activityContext, activity, extras));
    }

    public static void startActivityWithObjectPushAndOrigin(Context activityContext, Class activity, Object objectToPush, String origin) {

        CachePot.getInstance().push(objectToPush);
        ActivityUtils1.startActivityForIntent(activityContext, constructIntentWithStringExtras(activityContext, activity, new Pair[]{new Pair<>("origin", origin)}));
    }

    public static void startActivityWithStringExtrasAndFinish(Context currentActivityContext, Class nextActivity, Pair[] nextActivityExtras) {

        startActivityForIntentWithFinish(currentActivityContext, constructIntentWithStringExtras(currentActivityContext, nextActivity, nextActivityExtras));
    }

    public static void startActivityForClassWithFinish(Context currentActivityContext, Class nextActivityClass) {

        ActivityUtils1.startActivityForClass(currentActivityContext, nextActivityClass);
        ((AppCompatActivity) currentActivityContext).finish();
    }

    public static void startActivityForIntentWithFinish(Context currentActivityContext, Intent nextActivityIntent) {

        ActivityUtils1.startActivityForIntent(currentActivityContext, nextActivityIntent);
        ((AppCompatActivity) currentActivityContext).finish();
    }

    public static void startActivityWithObjectPushAndFinishAndOrigin(Context activityContext, Class activity, Object objectToPush, String origin) {

        startActivityWithObjectPushAndOrigin(activityContext, activity, objectToPush, origin);
        ((AppCompatActivity) activityContext).finish();
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