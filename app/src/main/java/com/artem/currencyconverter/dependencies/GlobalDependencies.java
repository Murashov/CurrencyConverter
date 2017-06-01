package com.artem.currencyconverter.dependencies;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.artem.currencyconverter.data.datastore.factrory.CurrencyDataStoreFactory;
import com.artem.currencyconverter.data.datastore.factrory.LocalCurrencyDataStoreFactory;
import com.artem.currencyconverter.data.datastore.factrory.RemoteCurrencyDataStoreFactory;
import com.artem.currencyconverter.data.repository.CurrencyRepository;
import com.artem.currencyconverter.domain.interactor.GetCurrenciesInteractor;
import com.artem.currencyconverter.domain.mapper.CurrencyMapper;

/**
 * Created by artemmurashov on 5/30/17.
 */

public class GlobalDependencies {
    private static CurrencyRepository sCurrencyRepository;
    private static GetCurrenciesInteractor sGetCurrenciesInteractor;

    public static void initialize(Context context) {
        CurrencyDataStoreFactory localStoreFactory = new LocalCurrencyDataStoreFactory(context);
        CurrencyDataStoreFactory remoteStoreFactory = new RemoteCurrencyDataStoreFactory();

        sCurrencyRepository = new CurrencyRepository(localStoreFactory, remoteStoreFactory);

        Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        CurrencyMapper mapper = new CurrencyMapper();
        sGetCurrenciesInteractor = new GetCurrenciesInteractor(mainThreadHandler, sCurrencyRepository, mapper);
    }

    public static CurrencyRepository getCurrencyRepository() {
        return sCurrencyRepository;
    }

    public static GetCurrenciesInteractor getGetCurrenciesInteractor() {
        return sGetCurrenciesInteractor;
    }
}
