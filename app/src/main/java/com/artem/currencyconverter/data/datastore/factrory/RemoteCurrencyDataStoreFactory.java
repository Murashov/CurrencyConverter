package com.artem.currencyconverter.data.datastore.factrory;

import com.artem.currencyconverter.data.datastore.CurrencyDataStore;
import com.artem.currencyconverter.data.datastore.RemoteXMLCurrencyDataStore;
import com.artem.currencyconverter.data.helper.RemoteStringReader;
import com.artem.currencyconverter.utils.Constants;

/**
 * Created by artemmurashov on 5/30/17.
 */

public class RemoteCurrencyDataStoreFactory implements CurrencyDataStoreFactory {
    @Override
    public CurrencyDataStore create() {
        RemoteStringReader reader = new RemoteStringReader(Constants.CURRENCIES_URL);
        return new RemoteXMLCurrencyDataStore(reader);
    }
}
