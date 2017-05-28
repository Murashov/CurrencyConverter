package com.artem.currencyconverter.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Artem on 5/27/2017.
 */
@Root(name = "Valute")
public class CurrencyEntity {
    public static final String TABLE_NAME = "currencies";

    private static final String TEXT_TYPE   = "TEXT";
    private static final String INT_TYPE    = "INTEGER";
    private static final String REAL_TYPE   = "REAL";

    public static final String COLUMN_NAME_ID           = "id";
    public static final String COLUMN_NAME_NUM_CODE     = "num_code";
    public static final String COLUMN_NAME_CHAR_CODE    = "char_code";
    public static final String COLUMN_NAME_NOMINAL      = "nominal";
    public static final String COLUMN_NAME_NAME         = "name";
    public static final String COLUMN_NAME_VALUE        = "value";

    public static final String COLUMN_TYPE_ID           = TEXT_TYPE;
    public static final String COLUMN_TYPE_NUM_CODE     = INT_TYPE;
    public static final String COLUMN_TYPE_CHAR_CODE    = TEXT_TYPE;
    public static final String COLUMN_TYPE_NOMINAL      = INT_TYPE;
    public static final String COLUMN_TYPE_NAME         = TEXT_TYPE;
    public static final String COLUMN_TYPE_VALUE        = REAL_TYPE;

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
    private String mValue;

    private Double mDoubleValue;

    public CurrencyEntity(String id, int numCode, String charCode, int nominal, String name, String value) {
        mId = id;
        mNumCode = numCode;
        mCharCode = charCode;
        mNominal = nominal;
        mName = name;
        mValue = value;
    }

    public CurrencyEntity(String id, int numCode, String charCode, int nominal, String name, double value) {
        mId = id;
        mNumCode = numCode;
        mCharCode = charCode;
        mNominal = nominal;
        mName = name;
        mDoubleValue = value;
    }

    public CurrencyEntity() {}

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
        //Симпл хмл не принимает запятую в качестве разделителя
        if (mDoubleValue == null) {
            try {
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                mDoubleValue = format.parse(mValue).doubleValue();
            } catch (ParseException ignore) {
                return 0;
            }
        }

        return mDoubleValue;
    }
}
