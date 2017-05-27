package com.artem.currencyconverter.domain.interactor;

import android.os.AsyncTask;

import com.artem.currencyconverter.domain.observer.InteractorResultObserver;
import com.artem.currencyconverter.presentation.model.Currency;

import java.util.List;

/**
 * Created by Artem on 5/27/2017.
 */

public class GetCurrenciesInteractor implements Interactor<List<Currency>>{
    @Override
    public void execute(InteractorResultObserver<List<Currency>> observer) {

    }
}
