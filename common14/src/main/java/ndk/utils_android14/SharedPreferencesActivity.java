package ndk.utils_android14;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android1.SharedPreferencesUtils1;

public abstract class SharedPreferencesActivity extends AppCompatActivity {

    public SharedPreferences getSharedPreferences() {
        return SharedPreferencesUtils1.getSharedPreferences(getApplicationContext(), configureApplicationName());
    }

    protected abstract String configureApplicationName();
}
