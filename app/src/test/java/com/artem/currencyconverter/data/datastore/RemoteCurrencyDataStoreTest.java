package com.artem.currencyconverter.data.datastore;

import com.artem.currencyconverter.data.helper.RemoteStringReader;
import com.artem.currencyconverter.data.model.CurrencyEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.io.IOException;
import java.util.List;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by artemmurashov on 5/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RemoteCurrencyDataStoreTest {
    private static final String FAKE_VALID_XML =
            "<ValCurs Date=\"01.06.2017\" name=\"Foreign Currency Market\">\n" +
            "   <Valute ID=\"R01010\">\n" +
            "       <NumCode>036</NumCode>\n" +
            "       <CharCode>AUD</CharCode>\n" +
            "       <Nominal>1</Nominal>\n" +
            "       <Name>Австралийский доллар</Name>\n" +
            "       <Value>42,2436</Value>\n" +
            "   </Valute>\n" +
            "   <Valute ID=\"R01020A\">\n" +
            "       <NumCode>944</NumCode>\n" +
            "       <CharCode>AZN</CharCode>\n" +
            "       <Nominal>1</Nominal>\n" +
            "       <Name>Азербайджанский манат</Name>\n" +
            "       <Value>33,3065</Value>\n" +
            "   </Valute>" +
            "</ValCurs>";

    private static final String FAKE_INVALID_XML =
            "<ValCurs Date=\"01.06.2017\" name=\"Foreign Currency Market\">\n" +
            "   <Valute ID=\"R01010\">\n" +
            "       <NumCode>ABC</NumCode>\n" +
            "       <CharCode>123</CharCode>\n" +
            "       <Nominal>1.0</Nominal>\n" +
            "       <Name>1</Name>\n" +
            "       <Value>String</Value>\n" +
            "   </Valute>\n" +
            "</ValCurs>";

    private CurrencyDataStore mRemoteCurrencyDataStore;

    @Mock RemoteStringReader mStringReader;

    @Before
    public void setUp() {
        mRemoteCurrencyDataStore = new RemoteXMLCurrencyDataStore(mStringReader);
    }

    @Test
    public void testGetCurrenciesValidXML() throws IOException {
        given(mStringReader.read()).willReturn(FAKE_VALID_XML);

        List<CurrencyEntity> currencies = mRemoteCurrencyDataStore.getCurrencies();
        verify(mStringReader).read();

        assertThat(currencies, is(notNullValue()));
        assertThat(currencies.size(), is(2));
    }

    @Test
    public void testGetCurrenciesInvalidXML() throws IOException {
        given(mStringReader.read()).willReturn(FAKE_INVALID_XML);

        List<CurrencyEntity> currencies = mRemoteCurrencyDataStore.getCurrencies();
        verify(mStringReader).read();

        assertThat(currencies, is(nullValue()));
    }

    @Test
    public void testGetCurrenciesReaderThrows() throws IOException {
        given(mStringReader.read()).willThrow(new IOException());

        List<CurrencyEntity> currencies = mRemoteCurrencyDataStore.getCurrencies();
        verify(mStringReader).read();

        assertThat(currencies, is(nullValue()));
    }
}
