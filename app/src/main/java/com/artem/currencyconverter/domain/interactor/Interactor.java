package com.artem.currencyconverter.domain.interactor;

import com.artem.currencyconverter.domain.observer.InteractorResultObserver;

/**
 * Created by Artem on 5/27/2017.
 */

public interface Interactor<T> {
    void execute(InteractorResultObserver<T> observer);
}
