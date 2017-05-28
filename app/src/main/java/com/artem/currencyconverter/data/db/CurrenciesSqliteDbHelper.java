package com.artem.currencyconverter.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.artem.currencyconverter.data.model.CurrencyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 5/28/2017.
 */

public class CurrenciesSqliteDbHelper extends SQLiteOpenHelper implements CurrenciesDbHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Converter.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + CurrencyEntity.TABLE_NAME + " (" +
                    CurrencyEntity.COLUMN_NAME_ID           + " " + CurrencyEntity.COLUMN_TYPE_ID + " " + " PRIMARY KEY," +
                    CurrencyEntity.COLUMN_NAME_NUM_CODE     + " " + CurrencyEntity.COLUMN_TYPE_NUM_CODE + "," +
                    CurrencyEntity.COLUMN_NAME_CHAR_CODE    + " " + CurrencyEntity.COLUMN_TYPE_CHAR_CODE + "," +
                    CurrencyEntity.COLUMN_NAME_NOMINAL      + " " + CurrencyEntity.COLUMN_TYPE_NOMINAL + "," +
                    CurrencyEntity.COLUMN_NAME_NAME         + " " + CurrencyEntity.COLUMN_TYPE_NAME + "," +
                    CurrencyEntity.COLUMN_NAME_VALUE        + " " + CurrencyEntity.COLUMN_TYPE_VALUE +
            " )";

    private static final String SQL_CLEAR_TABLE = "DELETE FROM " + CurrencyEntity.TABLE_NAME;
    private static final String SQL_DROP_TABLE  = "DROP TABLE IF EXISTS " + CurrencyEntity.TABLE_NAME;

    public CurrenciesSqliteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public List<CurrencyEntity> getCurrencies() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
            CurrencyEntity.COLUMN_NAME_ID,
            CurrencyEntity.COLUMN_NAME_NUM_CODE,
            CurrencyEntity.COLUMN_NAME_CHAR_CODE,
            CurrencyEntity.COLUMN_NAME_NOMINAL,
            CurrencyEntity.COLUMN_NAME_NAME,
            CurrencyEntity.COLUMN_NAME_VALUE,
        };

        List<CurrencyEntity> result = new ArrayList<>();
        Cursor cursor = db.query(CurrencyEntity.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                String id       = cursor.getString(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_ID));
                int numCode     = cursor.getInt(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_NUM_CODE));
                String charCode = cursor.getString(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_CHAR_CODE));
                int nominal     = cursor.getInt(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_NOMINAL));
                String name     = cursor.getString(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_NAME));
                double value    = cursor.getDouble(cursor.getColumnIndex(CurrencyEntity.COLUMN_NAME_VALUE));

                result.add(new CurrencyEntity(id, numCode, charCode, nominal, name, value));
            } while(cursor.moveToNext());
        }
        cursor.close();

        return result;
    }

    @Override
    public void refreshCurrencies(List<CurrencyEntity> currencies) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_CLEAR_TABLE);

        ContentValues values = new ContentValues();
        for (CurrencyEntity currency : currencies) {
            values.put(CurrencyEntity.COLUMN_NAME_ID, currency.getId());
            values.put(CurrencyEntity.COLUMN_NAME_NUM_CODE, currency.getNumCode());
            values.put(CurrencyEntity.COLUMN_NAME_CHAR_CODE, currency.getCharCode());
            values.put(CurrencyEntity.COLUMN_NAME_NOMINAL, currency.getNominal());
            values.put(CurrencyEntity.COLUMN_NAME_NAME, currency.getName());
            values.put(CurrencyEntity.COLUMN_NAME_VALUE, currency.getValue());

            db.insert(CurrencyEntity.TABLE_NAME, null, values);
        }
    }
}
