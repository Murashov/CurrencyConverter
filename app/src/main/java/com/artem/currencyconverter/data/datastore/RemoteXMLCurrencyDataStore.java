package com.artem.currencyconverter.data.datastore;

import android.support.annotation.Nullable;

import com.artem.currencyconverter.data.helper.RemoteStringReader;
import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.data.model.ExchangeRates;

import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class RemoteXMLCurrencyDataStore implements CurrencyDataStore {
    private RemoteStringReader mStringReader;
    private Persister mSerializer = new Persister();

    public RemoteXMLCurrencyDataStore(final RemoteStringReader stringReader) {
        mStringReader = stringReader;
    }

    @Nullable
    @Override
    public List<CurrencyEntity> getCurrencies() {
        try {
            Reader reader = new StringReader(mStringReader.read());
            ExchangeRates rates = mSerializer.read(ExchangeRates.class, reader, false);
            return rates.getCurrencyEntities();
        } catch (Exception ignore) {
            return null;
        }
    }

    @Override
    public void refreshCurrencies(final List<CurrencyEntity> currencies) {

    }
}