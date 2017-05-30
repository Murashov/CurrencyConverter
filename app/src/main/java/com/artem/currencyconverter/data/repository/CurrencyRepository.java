package com.artem.currencyconverter.data.repository;

import android.support.annotation.Nullable;

import com.artem.currencyconverter.data.datastore.CurrencyDataStore;
import com.artem.currencyconverter.data.datastore.factrory.CurrencyDataStoreFactory;
import com.artem.currencyconverter.data.model.CurrencyEntity;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class CurrencyRepository {
    private final CurrencyDataStore mRemoteDataStore;
    private final CurrencyDataStore mLocalDataStore;

    public CurrencyRepository(CurrencyDataStoreFactory localDataStoreFactory, CurrencyDataStoreFactory remoteDataStoreFactory) {
        mLocalDataStore = localDataStoreFactory.create();
        mRemoteDataStore = remoteDataStoreFactory.create();
    }

    @Nullable
    public List<CurrencyEntity> getCurrencies() {
        List<CurrencyEntity> result;

        result = mRemoteDataStore.getCurrencies();

        if (result != null) {
            mLocalDataStore.refreshCurrencies(result);
        } else {
            result = mLocalDataStore.getCurrencies();
        }

        return result;
    }
}
