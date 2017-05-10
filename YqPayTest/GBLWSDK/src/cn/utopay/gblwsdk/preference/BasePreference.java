package cn.utopay.gblwsdk.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Versace on 17/3/21.
 */
public class BasePreference {

    private static SharedPreferences preference = null;

    public BasePreference (Context context){
        if (preference == null) {
            preference = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }
    }

    protected int readInt(String name, int defaultInt) {
        return preference.getInt(name, defaultInt);
    }

    protected float readFloat(String name, float defaultFloat) {
        return preference.getFloat(name, defaultFloat);
    }

    protected String readString(String name, String defaultString) {
        return preference.getString(name, defaultString);
    }

    protected Boolean readBoolean(String name, Boolean defaultBoolean) {
        return preference.getBoolean(name, defaultBoolean);
    }

    protected boolean readBoolean(String name, boolean defaultBoolean) {
        return preference.getBoolean(name, defaultBoolean);
    }

    protected void write(String name, int value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    protected void write(String name, float value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    protected long readLong(String name, long defaultLong) {
        return preference.getLong(name, defaultLong);
    }

    protected void write(String name, long value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong(name, value);
        editor.apply();
    }

    protected void write(String name, String value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(name, value);
        editor.apply();
    }

    protected void write(String name, boolean value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    protected void cleanAll() {
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }
}
