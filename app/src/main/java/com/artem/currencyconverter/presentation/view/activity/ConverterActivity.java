package com.artem.currencyconverter.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class ConverterActivity extends BaseActivity<ConverterPresenter> implements ConverterView,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    private Button          mConvertButton;
    private EditText        mSourceEditText;
    private EditText        mTargetEditText;
    private Spinner         mSourceSpinner;
    private Spinner         mTargetSpinner;
    private ProgressBar     mProgressBar;
    private LinearLayout    mContentLayout;
    private LinearLayout    mErrorLayout;
    private Button          mRetryButton;

    private ConverterPresenter mPresenter;
    private CurrencyAdapter    mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConvertButton  = (Button) findViewById(R.id.a_conv_convert_button);
        mSourceEditText = (EditText) findViewById(R.id.a_conv_source_et);
        mTargetEditText = (EditText) findViewById(R.id.a_conv_target_et);
        mSourceSpinner  = (Spinner) findViewById(R.id.a_conv_source_sp);
        mTargetSpinner  = (Spinner) findViewById(R.id.a_conv_target_sp);
        mProgressBar    = (ProgressBar) findViewById(R.id.a_conv_progress_pb);
        mContentLayout  = (LinearLayout) findViewById(R.id.a_conv_content_ll);
        mErrorLayout    = (LinearLayout) findViewById(R.id.a_conv_error_ll);
        mRetryButton    = (Button) findViewById(R.id.a_conv_retry_button);

        mConvertButton.setOnClickListener(this);
        mRetryButton.setOnClickListener(this);
        mSourceSpinner.setOnItemSelectedListener(this);
        mTargetSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mPresenter.setView(this);

        if (savedInstanceState == null) {
            mPresenter.initialize();
        }
    }

    @Override
    public void addCurrencyList(List<Currency> currencies) {
        mAdapter = new CurrencyAdapter(this, currencies);
        mSourceSpinner.setAdapter(mAdapter);
        mTargetSpinner.setAdapter(mAdapter);
    }

    @Override
    public void setTargetValue(double value) {
        mTargetEditText.setText(String.valueOf(value));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_conv_convert_button:
                convert();
                break;
            case R.id.a_conv_retry_button:
                mPresenter.reloadData();
                break;
        }

    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (parent.getId() == R.id.a_conv_source_sp) {
            mPresenter.setSourcePosition(position);
        } else if (parent.getId() == R.id.a_conv_target_sp) {
            mPresenter.setTargetPosition(position);
        }
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {

    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.INVISIBLE);
        mErrorLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void stopLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mContentLayout.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mContentLayout.setVisibility(View.INVISIBLE);
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSourcePosition(final int position) {
        mSourceSpinner.setSelection(position);
    }

    @Override
    public void setTargetPosition(final int position) {
        mTargetSpinner.setSelection(position);
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

    private void convert() {
        String strValue = mSourceEditText.getText().toString();
        if (! strValue.isEmpty()) {
            try {
                double value = Double.valueOf(strValue);
                mPresenter.convert(value, mSourceSpinner.getSelectedItemPosition(), mTargetSpinner.getSelectedItemPosition());
            } catch (NumberFormatException ignore) {

            }
        }
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
