package com.artem.currencyconverter.domain.mapper;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.presentation.model.Currency;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Currency> mapList(List<CurrencyEntity> entities) {
        List<Currency> result = new ArrayList<>(entities.size());

        for (CurrencyEntity entity : entities) {
            result.add(map(entity));
        }

        return result;
    }
}
