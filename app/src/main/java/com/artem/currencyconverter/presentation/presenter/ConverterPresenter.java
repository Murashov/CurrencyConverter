package com.artem.currencyconverter.presentation.presenter;

import com.artem.currencyconverter.presentation.view.ConverterView;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterPresenter implements MvpPresenter<ConverterView> {
    private ConverterView mView;

    @Override
    public void setView(ConverterView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }

    public void initialize() {
        mView.startLoading();
    }
}
