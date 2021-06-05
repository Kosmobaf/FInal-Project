package com.controller;

public final class Path {
    private Path() {
    }

    // pages
    public static final String WEB_INF_USER_SHOW_LIST_TARIFF_JSP = "/WEB-INF/user/showListTariff.jsp";
    public static final String WEB_INF_USER_USERBASIS_JSP = "/WEB-INF/user/userBasis.jsp";
    public static final String WEB_INF_USER_SHOW_LIST_SERVICES_JSP = "/WEB-INF/user/showListServices.jsp";
    public static final String WEB_INF_ADMIN_CREATE_USER_JSP = "/WEB-INF/admin/createUser.jsp";
    public static final String REDIRECT_INDEX_JSP = "redirect:/index.jsp";
    public static final String WEB_INF_ERROR_JSP = "/WEB-INF/error.jsp";

    //commands
    public static final String REDIRECT_ADMIN_BASIS = "redirect:/adminBasis";
    public static final String REDIRECT_USER_BASIS = "redirect:/userBasis";
}
