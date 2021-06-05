package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffCommand implements Command {
    TariffService service = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        long idTariff = Long.parseLong(request.getParameter("idTariff"));
        service.deleteTariff(idTariff);
        return Path.REDIRECT_SHOW_ALL_TARIFF;
    }
}
