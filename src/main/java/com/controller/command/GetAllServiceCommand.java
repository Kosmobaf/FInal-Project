package com.controller.command;

import com.controller.Path;
import com.model.entity.Service;
import com.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllServiceCommand implements Command {
    ServiceService service = new ServiceService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Service> serviceList;
        serviceList = service.getAllService();

        request.setAttribute("serviceList", serviceList);
        return Path.WEB_INF_USER_SHOW_LIST_SERVICES_JSP;
    }
}
