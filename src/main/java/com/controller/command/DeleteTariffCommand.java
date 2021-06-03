package com.controller.command;

import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffCommand implements Command {
    TariffService service = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long idTariff = Long.parseLong(request.getParameter("idTariff"));
        service.deleteTariff(idTariff);
        return "redirect:/showAllTariff";
    }
}
