package com.artem.currencyconverter.presentation.presenter;

import com.artem.currencyconverter.presentation.view.MvpView;

/**
 * Created by Artem on 5/27/2017.
 */

public interface MvpPresenter<T extends MvpView> {
    void onViewAttached(T view);
    void onViewDetached();
}
