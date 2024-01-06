package ndk.utils_android14;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android1.SharedPreferencesUtils1;

public abstract class SharedPreferencesActivityWithContexts extends AppCompatActivity {

    public Context currentActivityContext = this, currentApplicationContext;
    public AppCompatActivity currentAppCompatActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        currentApplicationContext = getApplicationContext();
    }

    public SharedPreferences getSharedPreferences() {
        return SharedPreferencesUtils1.getSharedPreferences(getApplicationContext(), configureApplicationName());
    }

    protected abstract String configureApplicationName();
}
