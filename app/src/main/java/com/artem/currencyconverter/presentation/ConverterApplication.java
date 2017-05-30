package com.artem.currencyconverter.presentation;

import android.app.Application;

import com.artem.currencyconverter.dependencies.GlobalDependencies;

/**
 * Created by Artem on 5/28/2017.
 */

public class ConverterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalDependencies.initialize(this);
    }
}
