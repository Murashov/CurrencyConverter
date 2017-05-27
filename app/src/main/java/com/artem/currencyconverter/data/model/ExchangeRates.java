package com.artem.currencyconverter.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */
@Root(name = "ValCurs")
public class ExchangeRates {
    @ElementList(required = true, inline = true)
    private List<CurrencyEntity> mCurrencyEntities;

    public ExchangeRates() {
        mCurrencyEntities = new ArrayList<>(0);
    }

    public List<CurrencyEntity> getCurrencyEntities() {
        return mCurrencyEntities;
    }
}
