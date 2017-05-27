package com.artem.currencyconverter.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.artem.currencyconverter.R;
import com.artem.currencyconverter.presentation.presenter.ConverterPresenter;
import com.artem.currencyconverter.presentation.presenter.factory.ConverterPresenterFactory;
import com.artem.currencyconverter.presentation.presenter.loader.PresenterLoader;
import com.artem.currencyconverter.presentation.view.ConverterView;
import com.artem.currencyconverter.utils.Constants;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterActivity extends BaseActivity<ConverterPresenter> implements ConverterView {
    private ConverterPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.a_converter;
    }

    @Override
    protected int getLoaderId() {
        return Constants.CONVERTER_ACTIVITY_LOADER_ID;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onViewAttached(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
    }

    @Override
    public Loader<ConverterPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new ConverterPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<ConverterPresenter> loader, ConverterPresenter data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<ConverterPresenter> loader) {
        mPresenter = null;
    }
}
