package com.artem.currencyconverter.domain.interactor;

import android.os.AsyncTask;
import android.os.Handler;

import com.artem.currencyconverter.data.model.CurrencyEntity;
import com.artem.currencyconverter.data.repository.CurrencyRepository;
import com.artem.currencyconverter.domain.mapper.CurrencyMapper;
import com.artem.currencyconverter.domain.observer.InteractorResultObserver;
import com.artem.currencyconverter.presentation.model.Currency;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class GetCurrenciesInteractor implements Interactor<List<Currency>>{
    private Handler mResultHandler;
    private CurrencyRepository mCurrencyRepository;

    public GetCurrenciesInteractor(Handler resultHandler, CurrencyRepository currencyRepository) {
        mResultHandler = resultHandler;
        mCurrencyRepository = currencyRepository;
    }

    @Override
    public void execute(final InteractorResultObserver<List<Currency>> observer) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<CurrencyEntity> entities = mCurrencyRepository.getCurrencies();

                    if (entities != null && ! entities.isEmpty()) {
                        mResultHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                observer.onResult(CurrencyMapper.mapList(entities));
                            }
                        });
                    } else {
                        mResultHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                observer.onError(new Exception("Invalid result"));
                            }
                        });
                    }
                } catch (final Exception e) {
                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            observer.onError(e);
                        }
                    });
                }

                return null;
            }
        }.execute();
    }
}
