package com.artem.currencyconverter.data.repository;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.data.remote.RemoteCurrencyLoader;
import com.artem.currencyconverter.utils.Constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class CurrencyRepository {
    private static CurrencyRepository sCurrencyRepository;

    private final RemoteCurrencyLoader mRemoteCurrencyLoader = new RemoteCurrencyLoader(Constants.CURRENCIES_URL);

    public List<CurrencyEntity> getCurrencies() throws IOException {
        return mRemoteCurrencyLoader.load();
    }

    public static CurrencyRepository getInstance() {
        if (sCurrencyRepository == null) {
            sCurrencyRepository = new CurrencyRepository();
        }

        return sCurrencyRepository;
    }
}
