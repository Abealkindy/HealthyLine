package com.rosinante24.healthyline.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rosinante24 on 18/10/17.
 */

public class Pref {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "health-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Pref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstLaunched(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {

        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}

