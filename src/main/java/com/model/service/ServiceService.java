package com.model.service;

import com.model.dao.DaoFactory;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class ServiceService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    public void exportServicesToFile() {

        File file = new File("Services.txt");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            List<Service> serviceList = daoFactory.createServiceDao().findAll();

            for (Service service : serviceList) {
                writer.println("Service: " + service.getNameService());
                writer.println("-Tariff: ");
                List<Tariff> tariffList = daoFactory.createTariffDao().findAll();
                for (Tariff tariff : tariffList) {
                    if (tariff.getId_Services().equals(service.getId())) {
                        writer.println("         " + tariff.getNameTariff() + " - " + tariff.getCost() + " UAH");
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public List<Service> getAllService() {
       return daoFactory.createServiceDao().findAll();
    }
}
