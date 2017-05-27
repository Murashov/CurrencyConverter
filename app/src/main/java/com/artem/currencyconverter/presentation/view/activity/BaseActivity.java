package com.artem.currencyconverter.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;

import com.artem.currencyconverter.presentation.presenter.MvpPresenter;

/**
 * Created by Artem on 5/27/2017.
 */

public abstract class BaseActivity<T extends MvpPresenter> extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<T> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getSupportLoaderManager().initLoader(getLoaderId(), null, this);
    }

    protected abstract int getLayoutId();
    protected abstract int getLoaderId();
}
