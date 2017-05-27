package com.artem.currencyconverter.presentation.presenter.factory;

import com.artem.currencyconverter.presentation.presenter.ConverterPresenter;
import com.artem.currencyconverter.presentation.presenter.MvpPresenter;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterPresenterFactory implements PresenterFactory<ConverterPresenter> {
    @Override
    public ConverterPresenter create() {
        return new ConverterPresenter();
    }
}
