package com.controller.command;

import com.model.bean.UserOrderBean;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetListOrdersForUserCommand implements Command {
    List<UserOrderBean> list = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService service = new UserService();
        String login = (String) request.getSession().getAttribute("login");
        list = service.getOrdersForUser(login);

        request.getSession().setAttribute("userOrderList", list);
        return "/WEB-INF/user/userbasis.jsp";
    }
}
