package com.artem.currencyconverter.data.remote;

import android.util.Log;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.data.model.ExchangeRates;

import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class RemoteCurrencyLoader {
    private String mUrl;
    private Persister mPersister = new Persister();

    public RemoteCurrencyLoader(String url) {
        mUrl = url;
    }

    public List<CurrencyEntity> load() throws Exception {
        Reader reader = new StringReader(readXML());
        ExchangeRates rates = mPersister.read(ExchangeRates.class, reader, false);
        return rates.getCurrencyEntities();
    }

    private String readXML() throws IOException {
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(mUrl);

            in = new BufferedReader(new InputStreamReader(url.openStream(), "Windows-1251"));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }

        } finally {
            if (in != null) {
                in.close();
            }
        }

        return sb.toString();
    }
}
