package com.artem.currencyconverter.domain.mapper;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.domain.mapper.CurrencyMapper;
import com.artem.currencyconverter.presentation.model.Currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by artemmurashov on 6/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyMapperTest {
    private CurrencyMapper mCurrencyMapper;

    @Before
    public void setUp() {
        mCurrencyMapper = new CurrencyMapper();
    }

    @Test
    public void testMapValidInput() {
        CurrencyEntity entity = new CurrencyEntity("id", 213, "123", 1, "name", 1.0);
        Currency currency = mCurrencyMapper.map(entity);

        assertThat(currency, is(notNullValue()));
        assertThat(currency.getName(), is(equalTo(entity.getName())));
        assertThat(currency.getCode(), is(equalTo(entity.getCharCode())));
        assertThat(currency.getNominal(), is(equalTo(entity.getNominal())));
        assertThat(currency.getValue(), is(equalTo(entity.getValue())));
    }

    @Test
    public void testMapListValidInput() {
        CurrencyEntity entity = new CurrencyEntity("id", 213, "123", 1, "name", 1.0);
        List<CurrencyEntity> list = Arrays.asList(entity);

        List<Currency> resultList = mCurrencyMapper.mapList(list);

        assertThat(resultList, is(notNullValue()));
        assertThat(resultList.size(), is(equalTo(list.size())));
    }
}
