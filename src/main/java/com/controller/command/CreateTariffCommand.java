package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class CreateTariffCommand implements Command {
    TariffService service = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        String stringIdService = request.getParameter("id_service");

        if (stringIdService == null) {
            return Path.WEB_INF_ADMIN_CREATE_TARIFF_JSP;
        }

        long idService = Long.parseLong(stringIdService);
        String nameTariff = request.getParameter("nameTariff");
        BigDecimal cost = new BigDecimal(request.getParameter("cost"));

// add tariff to all tariffs
        service.addTariff(idService, nameTariff, cost);

        return Path.REDIRECT_SHOW_ALL_TARIFF;

    }
}
