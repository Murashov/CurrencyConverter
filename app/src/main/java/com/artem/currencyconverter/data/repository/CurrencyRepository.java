package com.artem.currencyconverter.data.repository;

import com.artem.currencyconverter.data.db.CurrenciesDbHelper;
import com.artem.currencyconverter.data.db.CurrenciesSqliteDbHelper;
import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.data.remote.RemoteCurrencyLoader;
import com.artem.currencyconverter.presentation.ConverterApplication;
import com.artem.currencyconverter.utils.Constants;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class CurrencyRepository {
    private static CurrencyRepository sCurrencyRepository;

    private final RemoteCurrencyLoader mRemoteCurrencyLoader = new RemoteCurrencyLoader(Constants.CURRENCIES_URL);
    private final CurrenciesDbHelper mDbHelper = new CurrenciesSqliteDbHelper(ConverterApplication.getContextObject());

    public List<CurrencyEntity> getCurrencies() throws Exception {
        List<CurrencyEntity> result = mRemoteCurrencyLoader.load();
        mDbHelper.refreshCurrencies(result);
        return result;
    }

    public static CurrencyRepository getInstance() {
        if (sCurrencyRepository == null) {
            sCurrencyRepository = new CurrencyRepository();
        }

        return sCurrencyRepository;
    }
}
