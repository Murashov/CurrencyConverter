package com.artem.currencyconverter.presentation.presenter;

import com.artem.currencyconverter.domain.interactor.GetCurrenciesInteractor;
import com.artem.currencyconverter.domain.observer.InteractorResultObserver;
import com.artem.currencyconverter.presentation.view.ConverterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

/**
 * Created by artemmurashov on 6/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterPresenterTest {
    private ConverterPresenter mConverterPresenter;

    @Mock
    GetCurrenciesInteractor mInteractor;

    @Mock
    ConverterView mConverterView;

    @Before
    public void setUp() {
        mConverterPresenter = new ConverterPresenter(mInteractor);
        mConverterPresenter.setView(mConverterView);
    }

    @Test
    public void testInitialize() {
        mConverterPresenter.initialize();
        verify(mInteractor).execute(any(InteractorResultObserver.class));
    }
}
