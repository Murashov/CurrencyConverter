package com.artem.currencyconverter.presentation;

import android.app.Application;
import android.content.Context;

/**
 * Created by Artem on 5/28/2017.
 */

public class ConverterApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContextObject() {
        return sContext;
    }
}
