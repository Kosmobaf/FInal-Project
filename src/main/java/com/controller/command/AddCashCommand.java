package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddCashCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        BigDecimal inputCash;

//check value is not empty or null
        String inputStringCash = request.getParameter("inputCash");
        if (inputStringCash == null || inputStringCash.isEmpty()) {
            return Path.WEB_INF_USER_ADD_CASH_JSP;
        }

//check value is number
        try {
            inputCash = new BigDecimal(inputStringCash);
        } catch (NumberFormatException e) {
            throw new MyException("Value entered must be a number greater than 0");
        }

//check value > 0
        if (inputCash.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MyException("Value must be greater than 0");
        }
// add value to user
        String login = (String) request.getSession().getAttribute("login");
        service.addCashToUser(login, inputCash);

        return Path.REDIRECT_USER_BASIS;
    }
}
