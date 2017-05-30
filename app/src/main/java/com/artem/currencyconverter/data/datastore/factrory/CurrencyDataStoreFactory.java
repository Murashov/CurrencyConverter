package com.artem.currencyconverter.data.datastore.factrory;

import com.artem.currencyconverter.data.datastore.CurrencyDataStore;

/**
 * Created by artemmurashov on 5/30/17.
 */

public interface CurrencyDataStoreFactory {
    CurrencyDataStore create();
}
