package com.artem.currencyconverter.data.remote;

import android.util.Log;

import com.artem.currencyconverter.data.model.CurrencyEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class RemoteCurrencyLoader {
    private String mUrl;

    public RemoteCurrencyLoader(String url) {
        mUrl = url;
    }

    public List<CurrencyEntity> load() throws IOException {
        Log.d("RemoteLoader", readXML());
        return new ArrayList<>();
    }

    private String readXML() throws IOException {
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        try {
            // Create a URL for the desired page
            URL url = new URL(mUrl);

            // Read all the text returned by the server
            in = new BufferedReader(new InputStreamReader(url.openStream()));
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
