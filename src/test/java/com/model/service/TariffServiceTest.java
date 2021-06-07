package com.model.service;

import com.model.dao.dao_factory.DaoFactory;
import com.model.dao.dao_factory.TariffDao;
import com.model.entity.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TariffServiceTest {
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private TariffDao tariffDao;

    private TariffService subject;

    @BeforeEach
    void setUp() {
        this.subject = new TariffService(daoFactory);
    }

    @Test
    void getAllTariffByServiceAndSort() {
        when(daoFactory.createTariffDao()).thenReturn(tariffDao);

        List<Tariff> expect = new ArrayList<>();
        when(tariffDao.findAllTariffFromOneServiceAndSorted(11L, "sortCommand")).thenReturn(expect);

        List<Tariff> actual = subject.getAllTariffByServiceAndSort(11L, "sortCommand");
        assertSame(expect, actual);
    }
}