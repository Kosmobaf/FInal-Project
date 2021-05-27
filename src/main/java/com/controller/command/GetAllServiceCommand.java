package com.controller.command;

import com.model.entity.Service;
import com.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetAllServiceCommand implements Command {
    List<Service> serviceList = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceService service = new ServiceService();
        serviceList = service.getAllService();
        request.getSession().setAttribute("serviceList", serviceList);
        return "/WEB-INF/user/showListServices.jsp";
    }
}
