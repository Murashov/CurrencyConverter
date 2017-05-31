package com.artem.currencyconverter.data.repository;

import com.artem.currencyconverter.data.datastore.CurrencyDataStore;
import com.artem.currencyconverter.data.datastore.factrory.CurrencyDataStoreFactory;
import com.artem.currencyconverter.data.datastore.factrory.LocalCurrencyDataStoreFactory;
import com.artem.currencyconverter.data.model.CurrencyEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by artemmurashov on 5/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyRepositoryTest {
    private final CurrencyEntity FAKE_LOCAL_ENTITY = new CurrencyEntity("id", 123, "abc", 1, "local", 10.0);
    private final CurrencyEntity FAKE_REMOTE_ENTITY = new CurrencyEntity("id1", 312, "def", 2, "remote", 5.0);

    private final List<CurrencyEntity> FAKE_LOCAL_LIST = Arrays.asList(FAKE_LOCAL_ENTITY);
    private final List<CurrencyEntity> FAKE_REMOTE_LIST = Arrays.asList(FAKE_REMOTE_ENTITY);

    private CurrencyRepository mCurrencyRepository;

    @Mock private LocalCurrencyDataStoreFactory mLocalDataStoreFactoryMock;
    @Mock private CurrencyDataStoreFactory mRemoteDataStoreFactoryMock;
    @Mock private CurrencyDataStore mLocalDataStoreMock;
    @Mock private CurrencyDataStore mRemoteDataStoreMock;

    @Before
    public void setUp() {
        given(mLocalDataStoreFactoryMock.create()).willReturn(mLocalDataStoreMock);
        given(mRemoteDataStoreFactoryMock.create()).willReturn(mRemoteDataStoreMock);

        mCurrencyRepository = new CurrencyRepository(mLocalDataStoreFactoryMock, mRemoteDataStoreFactoryMock);
    }

    @Test
    public void testGetCurrenciesFromRemoteStoreWithNotEmptyLocalStore() {
        given(mLocalDataStoreMock.getCurrencies()).willReturn(FAKE_LOCAL_LIST);
        given(mRemoteDataStoreMock.getCurrencies()).willReturn(FAKE_REMOTE_LIST);

        List<CurrencyEntity> currencyEntities = mCurrencyRepository.getCurrencies();
        verify(mRemoteDataStoreMock).getCurrencies();
        verify(mLocalDataStoreMock, never()).getCurrencies();

        assertThat(currencyEntities, is(notNullValue()));
        assertThat(currencyEntities, is(equalTo(FAKE_REMOTE_LIST)));
    }

    @Test
    public void testGetCurrenciesWithNoConnectionNotEmptyLocalStore() {
        given(mRemoteDataStoreMock.getCurrencies()).willReturn(null);
        given(mLocalDataStoreMock.getCurrencies()).willReturn(FAKE_LOCAL_LIST);

        List<CurrencyEntity> currencyEntities = mCurrencyRepository.getCurrencies();
        verify(mRemoteDataStoreMock).getCurrencies();
        verify(mLocalDataStoreMock).getCurrencies();

        assertThat(currencyEntities, is(notNullValue()));
        assertThat(currencyEntities, is(equalTo(FAKE_LOCAL_LIST)));
    }

    @Test
    public void testGetCurrenciesWithNoConnectionEmptyLocalStore() {
        given(mRemoteDataStoreMock.getCurrencies()).willReturn(null);
        given(mLocalDataStoreMock.getCurrencies()).willReturn(null);

        List<CurrencyEntity> currencyEntities = mCurrencyRepository.getCurrencies();
        verify(mRemoteDataStoreMock).getCurrencies();
        verify(mLocalDataStoreMock).getCurrencies();

        assertThat(currencyEntities, is(nullValue()));
    }
}
