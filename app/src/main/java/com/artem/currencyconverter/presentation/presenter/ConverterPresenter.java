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
    private int mSourcePostion;
    private int mTargetPosition;
    private boolean mIsInitalized = false;

    public ConverterPresenter(GetCurrenciesInteractor getCurrenciesInteractor) {
        mGetCurrenciesInteractor = getCurrenciesInteractor;
    }

    @Override
    public void setView(ConverterView view) {
        mView = view;

        if (mIsInitalized) {
            if (mCurrencies != null && ! mCurrencies.isEmpty()) {
                mView.addCurrencyList(mCurrencies);
                mView.setSourcePosition(mSourcePostion);
                mView.setTargetPosition(mTargetPosition);
            } else {
                mView.showError();
            }
        }
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onResult(List<Currency> result) {
        mCurrencies = result;

        if (mView != null) {
            mView.addCurrencyList(mCurrencies);
            mView.stopLoading();
        }
    }

    @Override
    public void onError(Throwable error) {
        mView.showError();
    }

    public void convert(double sourceValue, int sourceIndex, int targetIndex) {
        mView.setTargetValue(sourceValue * mCurrencies.get(sourceIndex).convertTo(mCurrencies.get(targetIndex)));
    }

    public void setSourcePosition(int position) {
        mSourcePostion = position;
    }

    public void setTargetPosition(int position) {
        mTargetPosition = position;
    }

    public void initialize() {
        mSourcePostion = 0;
        mTargetPosition = 0;
        reloadData();

        mIsInitalized = true;
    }

    public void reloadData() {
        mCurrencies = null;
        mView.startLoading();
        mGetCurrenciesInteractor.execute(this);
    }
}
