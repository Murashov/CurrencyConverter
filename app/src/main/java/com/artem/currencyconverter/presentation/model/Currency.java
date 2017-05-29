package com.artem.currencyconverter.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Artem on 5/27/2017.
 */

public class Currency implements Parcelable {
    private String  mName;
    private String  mCode;
    private Integer mNominal;
    private Double  mValue;

    public final static Creator<Currency> CREATOR = new Parcelable.Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel source) {
            Currency currency = new Currency();
            currency.mName      = (String) source.readValue(String.class.getClassLoader());
            currency.mCode      = (String) source.readValue(String.class.getClassLoader());
            currency.mNominal   = (Integer) source.readValue(Integer.class.getClassLoader());
            currency.mValue     = (Double) source.readValue(Double.class.getClassLoader());

            return currency;
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    public double convertTo(Currency currency) {
        double r1 = mValue / mNominal;
        double r2 = currency.getValue() / currency.getNominal();
        return r1 / r2;
    }

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }

    public Integer getNominal() {
        return mNominal;
    }

    public Double getValue() {
        return mValue;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public void setNominal(Integer nominal) {
        mNominal = nominal;
    }

    public void setValue(Double value) {
        mValue = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mName);
        dest.writeValue(mCode);
        dest.writeValue(mNominal);
        dest.writeValue(mValue);
    }
}
