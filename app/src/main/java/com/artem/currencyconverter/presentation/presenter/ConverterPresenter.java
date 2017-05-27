package com.artem.currencyconverter.presentation.presenter;

import com.artem.currencyconverter.domain.interactor.GetCurrenciesInteractor;
import com.artem.currencyconverter.domain.observer.InteractorResultObserver;
import com.artem.currencyconverter.presentation.model.Currency;
import com.artem.currencyconverter.presentation.view.ConverterView;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterPresenter implements MvpPresenter<ConverterView>, InteractorResultObserver<List<Currency>> {
    private ConverterView mView;
    private GetCurrenciesInteractor mGetCurrenciesInteractor;

    public ConverterPresenter(GetCurrenciesInteractor getCurrenciesInteractor) {
        mGetCurrenciesInteractor = getCurrenciesInteractor;
    }

    @Override
    public void setView(ConverterView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onResult(List<Currency> result) {
        mView.addCurrencyList(result);
        mView.stopLoading();
    }

    @Override
    public void onError(Throwable error) {

    }

    public void initialize() {
        mView.startLoading();
        mGetCurrenciesInteractor.execute(this);
    }
}
