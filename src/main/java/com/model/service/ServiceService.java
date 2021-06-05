package com.model.service;

import com.model.dao.DaoFactory;
import com.model.dao.ServiceDao;
import com.model.dao.TariffDao;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class ServiceService {
    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    private static final String SPAYS2 = " - ";
    private static final String UAH = " UAH";
    private static final String SPAYS = "         ";
    private static final String TARIFF = "-Tariff: ";
    private static final String SERVICE = "Service: ";
    private static final String SERVICES_TXT = "Services.txt";

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void exportServicesToFile() {

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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Service> getAllService() {
        try (ServiceDao dao = daoFactory.createServiceDao()) {
            return dao.findAll();

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
