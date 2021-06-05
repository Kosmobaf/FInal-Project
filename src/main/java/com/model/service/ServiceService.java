package com.model.service;

import com.model.dao.dao_factory.DaoFactory;
import com.model.dao.dao_factory.ServiceDao;
import com.model.dao.dao_factory.TariffDao;
import com.model.entity.Service;
import com.model.entity.Tariff;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServiceService {

    private static final String SPAYS2 = " - ";
    private static final String UAH = " UAH";
    private static final String SPAYS = "         ";
    private static final String TARIFF = "-Tariff: ";
    private static final String SERVICE = "Service: ";
    private static final String SERVICES_TXT = "Services.txt";

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void exportServicesToFile()  {

        File file = new File(SERVICES_TXT);
        file.delete();

        try (PrintWriter writer = new PrintWriter(file);
             ServiceDao serviceDao = daoFactory.createServiceDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            file.createNewFile();
            List<Service> serviceList = serviceDao.findAll();

            for (Service service : serviceList) {
                writer.println(SERVICE + service.getNameService());
                writer.println(TARIFF);
                List<Tariff> tariffList = tariffDao.findAll();

                for (Tariff tariff : tariffList) {
                    if (tariff.getIdServices().equals(service.getId())) {
                        writer.println(SPAYS + tariff.getNameTariff() + SPAYS2 + tariff.getCost() + UAH);
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Service> getAllService() {
        try (ServiceDao dao = daoFactory.createServiceDao()) {

            return dao.findAll();
        }
    }
}
