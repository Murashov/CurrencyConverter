package com.artem.currencyconverter.presentation.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by artemmurashov on 6/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyTest {
    private Currency mCurrency;

    @Before
    public void setUp() {
        mCurrency = new Currency();
        mCurrency.setName("name");
        mCurrency.setCode("code");
        mCurrency.setNominal(1);
        mCurrency.setValue(2.0);
    }

    @Test
    public void testConvertNormalValue() {
        Currency other = new Currency();
        other.setNominal(2);
        other.setValue(8.0);

        assertThat(mCurrency.convertTo(other), is(equalTo(0.5)));
    }
}
