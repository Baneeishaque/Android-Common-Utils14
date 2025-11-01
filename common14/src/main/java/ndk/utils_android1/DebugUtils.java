package ndk.utils_android1;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import androidx.annotation.NonNull;

public class DebugUtils {

    public static boolean isDebugBuild(@NonNull Context currentApplicationContext) {

        return (0 != (currentApplicationContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
