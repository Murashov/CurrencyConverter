package com.artem.currencyconverter.domain.observer;

/**
 * Created by Artem on 5/27/2017.
 */

public interface InteractorResultObserver<T> {
    void onResult(T result);
    void onError(Throwable error);
}
