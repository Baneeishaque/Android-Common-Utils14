package ndk.utils_android1;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class DebugUtils {

    public static boolean isDebugBuild(Context currentApplicationContext) {

        return (0 != (currentApplicationContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
