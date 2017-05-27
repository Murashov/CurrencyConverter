package com.artem.currencyconverter.presentation.presenter.factory;

import android.os.Handler;
import android.os.Looper;

import com.artem.currencyconverter.domain.interactor.GetCurrenciesInteractor;
import com.artem.currencyconverter.presentation.presenter.ConverterPresenter;
import com.artem.currencyconverter.presentation.presenter.MvpPresenter;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterPresenterFactory implements PresenterFactory<ConverterPresenter> {
    @Override
    public ConverterPresenter create() {
        return new ConverterPresenter(new GetCurrenciesInteractor(new Handler(Looper.getMainLooper())));
    }
}
