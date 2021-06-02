package com.controller.command;

import com.model.constants.Constants;
import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllTariffCommand implements Command {
    TariffService tariff = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Tariff> tariffList;

        tariffList = tariff.getAllTariff();
        String sortCommand = request.getParameter("sortCommand");

        if (Constants.SORT_BY_NAME.equals(sortCommand)){
            tariffList = tariff.sortByName(tariffList);
        }
        else if (Constants.SORT_BY_NAME_REVERSE.equals(sortCommand)){
            tariffList = tariff.sortByNameReverse(tariffList);
        }
        else if (Constants.SORT_BY_COAST.equals(sortCommand)) {
            tariffList = tariff.sortByCost(tariffList);
        }

        request.getSession().setAttribute("tariffList", tariffList);

        return "WEB-INF/admin/showTariff.jsp";
    }
}
