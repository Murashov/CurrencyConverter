package com.artem.currencyconverter.data.db;

import com.artem.currencyconverter.data.model.CurrencyEntity;

import java.util.List;

/**
 * Created by Artem on 5/28/2017.
 */

public interface CurrenciesDbHelper {
    List<CurrencyEntity> getCurrencies();
    void refreshCurrencies(List<CurrencyEntity> currencies);
}
