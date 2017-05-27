package com.artem.currencyconverter.domain.mapper;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.presentation.model.Currency;

/**
 * Created by Artem on 5/27/2017.
 */

public class CurrencyMapper {
    public static Currency map(CurrencyEntity entity) {
        Currency currency = new Currency();
        currency.setName(entity.getName());
        currency.setCode(entity.getCharCode());
        currency.setNominal(entity.getNominal());
        currency.setValue(entity.getValue());

        return currency;
    }
}
