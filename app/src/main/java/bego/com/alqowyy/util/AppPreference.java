package bego.com.alqowyy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import bego.com.alqowyy.R;

public class AppPreference {
    SharedPreferences prefs;
    Context c;

    public AppPreference(Context c) {
        this.c = c;
        prefs= PreferenceManager.getDefaultSharedPreferences(c);
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = c.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun(){
        String key = c.getResources().getString(R.string.app_first_run);
        return prefs.getBoolean(key, true);
    }
}
