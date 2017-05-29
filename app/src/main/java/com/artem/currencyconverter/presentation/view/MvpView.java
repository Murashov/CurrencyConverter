package com.artem.currencyconverter.presentation.view;

/**
 * Created by Artem on 5/27/2017.
 */

public interface MvpView {
    void startLoading();
    void stopLoading();
    void showError();
}
