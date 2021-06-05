package com.controller.command;

import com.controller.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminBasisCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return Path.WEB_INF_ADMIN_ADMIN_BASIS_JSP;
    }
}
