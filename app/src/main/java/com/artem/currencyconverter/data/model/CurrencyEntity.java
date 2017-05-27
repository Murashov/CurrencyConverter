package com.artem.currencyconverter.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Artem on 5/27/2017.
 */
@Root(name = "Valute")
public class CurrencyEntity {
    @Attribute(name = "ID")
    private String mId;

    @Element(name = "NumCode")
    private int mNumCode;

    @Element(name = "CharCode")
    private String mCharCode;

    @Element(name = "Nominal")
    private int mNominal;

    @Element(name = "Name")
    private String mName;

    @Element(name = "Value")
    private double mValue;

    public CurrencyEntity(String id, int numCode, String charCode, int nominal, String name, double value) {
        mId = id;
        mNumCode = numCode;
        mCharCode = charCode;
        mNominal = nominal;
        mName = name;
        mValue = value;
    }

    public String getId() {
        return mId;
    }

    public int getNumCode() {
        return mNumCode;
    }

    public String getCharCode() {
        return mCharCode;
    }

    public int getNominal() {
        return mNominal;
    }

    public String getName() {
        return mName;
    }

    public double getValue() {
        return mValue;
    }
}