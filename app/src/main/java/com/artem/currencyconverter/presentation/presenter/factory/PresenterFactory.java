package com.artem.currencyconverter.presentation.presenter.factory;

import com.artem.currencyconverter.presentation.presenter.MvpPresenter;

/**
 * Created by Artem on 5/27/2017.
 */

public interface PresenterFactory<T extends MvpPresenter> {
    T create();
}
