package com.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminBasisCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return "/WEB-INF/admin/adminBasis.jsp";
    }
}
