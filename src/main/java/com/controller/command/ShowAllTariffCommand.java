package com.controller.command;

import com.controller.Path;
import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllTariffCommand implements Command {
    public static final int RECORDS_PER_PAGE = 5;
    public static final int START_PAGE = 1;
    public static final int ONE = 1;
    public static final double ONE_DOUBLE = 1.0;
    TariffService tariff = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tariff> tariffList;

        int page = START_PAGE;
        int recordsPerPage = RECORDS_PER_PAGE;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = getOffset(page, recordsPerPage);
        tariffList = tariff.getTariffsFromPage(offset, recordsPerPage);
        int noOfRecords = tariff.getNoOfRecords();
        int noOfPages = getNoOfPages(recordsPerPage, noOfRecords);

        request.setAttribute("tariffList", tariffList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return Path.WEB_INF_ADMIN_SHOW_ALL_TARIFF_JSP;
    }

    public int getNoOfPages(int recordsPerPage, int noOfRecords) {
        return (int) Math.ceil(noOfRecords * ONE_DOUBLE / recordsPerPage);
    }

    public int getOffset(int page, int recordsPerPage) {
        return (page - ONE) * recordsPerPage;
    }
}
