package com.controller.command;

import com.model.bean.UserOrderBean;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class ChangeStatusUserCommand implements Command {
    private final UserService userService = new UserService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long idUser = Long.parseLong(request.getParameter("idUser"));

        userService.changeStatusUser(idUser);

        return "redirect:/showUser";
    }
}
