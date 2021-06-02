package com.model.service;

import com.model.constants.Constants;
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

    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    public void exportServicesToFile() {

        File file = new File(Constants.SERVICES_TXT);
        file.delete();

        try (PrintWriter writer = new PrintWriter(file);
             ServiceDao serviceDao = daoFactory.createServiceDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            file.createNewFile();
            List<Service> serviceList = serviceDao.findAll();

            for (Service service : serviceList) {
                writer.println(Constants.SERVICE + service.getNameService());
                writer.println(Constants.TARIFF);
                List<Tariff> tariffList = tariffDao.findAll();

                for (Tariff tariff : tariffList) {
                    if (tariff.getIdServices().equals(service.getId())) {
                        writer.println(Constants.SPAYS + tariff.getNameTariff() + Constants.SPAYS2 + tariff.getCost() + Constants.UAH);
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
