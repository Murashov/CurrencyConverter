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
    private List<Currency> mCurrencies;

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
        mCurrencies = result;
        mView.addCurrencyList(mCurrencies);
        mView.stopLoading();
    }

    @Override
    public void onError(Throwable error) {

    }

    public void convert(double sourceValue, int sourceIndex, int targetIndex) {
        mView.setTargetValue(sourceValue * mCurrencies.get(sourceIndex).convertTo(mCurrencies.get(targetIndex)));
    }

    public void initialize() {
        mView.startLoading();
        mGetCurrenciesInteractor.execute(this);
    }
}
