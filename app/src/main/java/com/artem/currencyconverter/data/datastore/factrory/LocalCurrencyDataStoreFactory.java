package com.artem.currencyconverter.data.datastore.factrory;

import android.content.Context;

import com.artem.currencyconverter.data.datastore.CurrencyDataStore;
import com.artem.currencyconverter.data.datastore.CurrencySqliteDataStore;

/**
 * Created by artemmurashov on 5/30/17.
 */

public class LocalCurrencyDataStoreFactory implements CurrencyDataStoreFactory {
    public final Context mContext;

    public LocalCurrencyDataStoreFactory(Context context) {
        mContext = context;
    }

    @Override
    public CurrencyDataStore create() {
        return new CurrencySqliteDataStore(mContext);
    }
}
