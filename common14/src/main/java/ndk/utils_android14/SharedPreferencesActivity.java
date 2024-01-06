package ndk.utils_android14;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public abstract class SharedPreferencesActivity extends AppCompatActivity {

    public SharedPreferences getSharedPreferences() {
        return getApplicationContext().getSharedPreferences(configureApplicationName(), Context.MODE_PRIVATE);
    }

    protected abstract String configureApplicationName();
}
