package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivateTariffCommand implements Command {
    UserService userService = new UserService();
    public static final String NOT_ENOUGH_CASH_PLEASE_TOP_UP_YOUR_CASH_ACCOUNT =
            "Not enough cash, please top up your cash account";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        String login = (String) request.getSession().getAttribute("login");
        long idTariff = Long.parseLong(request.getParameter("idTariff"));

//withdraw cash from the user at the tariff
        if (!userService.withdrawCashFromUser(login, idTariff)){
            throw new MyException(NOT_ENOUGH_CASH_PLEASE_TOP_UP_YOUR_CASH_ACCOUNT);
        }

        return Path.REDIRECT_USER_BASIS;
    }
}
