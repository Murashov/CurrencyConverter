package com.artem.currencyconverter.presentation.view;

import com.artem.currencyconverter.presentation.model.Currency;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public interface ConverterView extends MvpView {
    void addCurrencyList(List<Currency> currencies);
    void setTargetValue(double value);
    void startLoading();
    void stopLoading();
    void setSourcePosition(int position);
    void setTargetPosition(int position);
}
