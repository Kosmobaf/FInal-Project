package com.controller.command;

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

        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = getOffset(page, recordsPerPage);
        tariffList = tariff.getTariffsFromPage(offset, recordsPerPage);


        int noOfRecords = tariff.getNoOfRecords();
        int noOfPages = getNoOfPages(recordsPerPage, noOfRecords);

        request.getSession().setAttribute("tariffList", tariffList);
        request.getSession().setAttribute("noOfPages", noOfPages);
        request.getSession().setAttribute("currentPage", page);

        return "WEB-INF/admin/showAllTariff.jsp";
    }

    public int getNoOfPages(int recordsPerPage, int noOfRecords) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }

    public int getOffset(int page, int recordsPerPage) {
        return (page - 1) * recordsPerPage;
    }
}
