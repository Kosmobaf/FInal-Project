package com.controller.command;

import com.controller.Path;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddCashCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BigDecimal inputCash;

        if (request.getParameter("inputCash") != null) {
            try {
                inputCash = new BigDecimal(request.getParameter("inputCash"));
                if (inputCash.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();

                String errorMessage = "Incorrect input";
                request.getSession().setAttribute("errorMessage", errorMessage);
                return Path.WEB_INF_ERROR_JSP;
            }
            String login = (String) request.getSession().getAttribute("login");
            service.addCashFromUser(login, inputCash);

            return "redirect:/userBasis";
        }
        return "/WEB-INF/user/addCash.jsp";
    }
}
