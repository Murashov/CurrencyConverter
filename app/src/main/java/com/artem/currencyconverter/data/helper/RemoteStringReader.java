package com.artem.currencyconverter.data.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by artemmurashov on 5/31/17.
 */

public class RemoteStringReader {
    private String mUrl;

    public RemoteStringReader(final String url) {
        mUrl = url;
    }

    public String read() throws IOException {
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
