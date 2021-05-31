package com.controller.command;

import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddCashCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BigDecimal inputCash;
        try {
            inputCash = new BigDecimal(request.getParameter("inputCash"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //TODO повідомлення що не вірно щось із данними
            return "addCash.jsp";
        }
        String login = (String) request.getSession().getAttribute("login");
        service.addCashFromUser(login, inputCash);
        return "redirect:/userBasis";
    }
}
