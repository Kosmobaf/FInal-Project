package com.controller.command;

import com.model.entity.Service;
import com.model.service.ServiceService;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddTariffWithServiceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TariffService service = new TariffService();

        service.addTariff((Long)request.getSession().getAttribute("idTariff"));
        return "/WEB-INF/user/userbasis.jsp.jsp";
    }
}
