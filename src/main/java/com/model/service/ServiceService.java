package com.model.service;

import com.model.dao.DaoFactory;
import com.model.dto.ServiceDto;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServiceService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    public List<ServiceDto> getServicesDto() {
        List<Service> serviceList = daoFactory.createServiceDao().findAll();
        List<Tariff> tariffList = daoFactory.createTariffDao().findAll();
        List<ServiceDto> serviceDto = new ArrayList<>();
        for (Service service : serviceList) {
            List<Tariff> tariffList1 = new ArrayList<>();
            for (Tariff tariff : tariffList) {
                if (tariff.getService().getId() == service.getId()) {
                    tariffList1.add(tariff);
                }
            }
            serviceDto.add(new ServiceDto(
                    service.getId(),
                    service.getNameService(),
                    tariffList1));
        }
        return serviceDto;
    }


    public void exportServicesToFile() {

        File file = new File("Services.txt");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            for (ServiceDto dto : getServicesDto()) {
                writer.println("Service: " + dto.getNameService());
                writer.print("Tariff: ");

                for (Tariff tariff : dto.getTariffList()) {
                    writer.println(tariff.getNameTariff() + " - " + tariff.getCost() + " UAH");
                }
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
