package com.artem.currencyconverter.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.artem.currencyconverter.R;
import com.artem.currencyconverter.presentation.model.Currency;
import com.artem.currencyconverter.presentation.presenter.ConverterPresenter;
import com.artem.currencyconverter.presentation.presenter.factory.ConverterPresenterFactory;
import com.artem.currencyconverter.presentation.presenter.loader.PresenterLoader;
import com.artem.currencyconverter.presentation.view.ConverterView;
import com.artem.currencyconverter.presentation.view.adapter.CurrencyAdapter;
import com.artem.currencyconverter.utils.Constants;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class ConverterActivity extends BaseActivity<ConverterPresenter> implements ConverterView {
    private Button      mConvertButton;
    private EditText    mSourceEditText;
    private EditText    mTargetEditText;
    private Spinner     mSourceSpinner;
    private Spinner     mTargetSpinner;

    private ConverterPresenter mPresenter;
    private CurrencyAdapter    mAdapter;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View rootView = super.onCreateView(parent, name, context, attrs);

        mConvertButton  = (Button) rootView.findViewById(R.id.a_conv_convert_button);
        mSourceEditText = (EditText) rootView.findViewById(R.id.a_conv_source_et);
        mTargetEditText = (EditText) rootView.findViewById(R.id.a_conv_target_et);
        mSourceSpinner  = (Spinner) rootView.findViewById(R.id.a_conv_source_sp);
        mTargetSpinner  = (Spinner) rootView.findViewById(R.id.a_conv_target_sp);

        mPresenter.setView(this);
        mPresenter.initialize();

        return rootView;
    }

    @Override
    public void addCurrencyList(List<Currency> currencies) {
        mAdapter = new CurrencyAdapter(this, currencies);
        mSourceSpinner.setAdapter(mAdapter);
        mTargetSpinner.setAdapter(mAdapter);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.a_converter;
    }

    @Override
    protected int getLoaderId() {
        return Constants.CONVERTER_ACTIVITY_LOADER_ID;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public Loader<ConverterPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new ConverterPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<ConverterPresenter> loader, ConverterPresenter data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<ConverterPresenter> loader) {
        mPresenter = null;
    }
}
