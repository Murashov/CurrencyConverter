package com.artem.currencyconverter.presentation.presenter.loader;

import android.content.Context;
import android.support.v4.content.Loader;

import com.artem.currencyconverter.presentation.presenter.MvpPresenter;
import com.artem.currencyconverter.presentation.presenter.factory.PresenterFactory;

/**
 * Created by Artem on 5/27/2017.
 */

public class PresenterLoader<T extends MvpPresenter> extends Loader<T> {
    private final PresenterFactory<T> mFactory;

    private T mPresenter;

    public PresenterLoader(Context context, PresenterFactory<T> factory) {
        super(context);
        mFactory = factory;
    }

    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        mPresenter = mFactory.create();
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        mPresenter = null;
    }
}
