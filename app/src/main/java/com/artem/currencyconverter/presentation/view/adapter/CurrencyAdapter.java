package com.artem.currencyconverter.presentation.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.artem.currencyconverter.R;
import com.artem.currencyconverter.presentation.model.Currency;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class CurrencyAdapter extends DefaultSpinnerAdapter {
    private List<Currency> mCurrencies;
    private Context mContext;

    public CurrencyAdapter(@NonNull Context context, @NonNull List<Currency> currencies) {
        mContext = context;
        mCurrencies = currencies;
    }

    @Override
    public int getCount() {
        return mCurrencies.size();
    }

    @Override
    public Object getItem(int position) {
        return mCurrencies.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CurrencyViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.li_currency, parent, false);
            viewHolder = new CurrencyViewHolder();
            viewHolder.mCurrencyTextView = (TextView) convertView.findViewById(R.id.li_currency_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CurrencyViewHolder) convertView.getTag();
        }

        Currency item = mCurrencies.get(position);
        if (item.getName() != null && item.getCode() != null) {
            viewHolder.mCurrencyTextView.setText(String.format("%1$s (%2$s)", item.getName(), item.getCode()));
        }

        return convertView;
    }

    @Override
    public boolean isEmpty() {
        return mCurrencies.isEmpty();
    }

    private static class CurrencyViewHolder {
        TextView mCurrencyTextView;
    }
}
