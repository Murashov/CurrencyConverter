package com.artem.currencyconverter.data.datastore;

import android.support.annotation.Nullable;

import com.artem.currencyconverter.data.model.CurrencyEntity;

import java.util.List;

/**
 * Created by Artem on 5/28/2017.
 */

public interface CurrencyDataStore {
    @Nullable List<CurrencyEntity> getCurrencies();
    void refreshCurrencies(List<CurrencyEntity> currencies);
}
