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
        if (request.getParameter("sortCommand") != null) {
            String sortCommand = request.getParameter("sortCommand");
            tariffList = tariff.sortList(sortCommand);
        }
/*
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        tariff.
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> list = dao.viewAllEmployees((page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("employeeList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("displayEmployee.jsp");
        view.forward(request, response);*/


        request.getSession().setAttribute("tariffList", tariffList);

        return "WEB-INF/admin/showAllTariff.jsp";
    }
}
