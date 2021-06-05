package com.controller.command;

import com.model.bean.UserOrderBean;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class ShowUserCommand implements Command {
    private final UserOrderBeanService orderBeanService = new UserOrderBeanService();
    private final UserService userService = new UserService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long idUser;
        if (request.getParameter("idUser") == null) {
            idUser = (long) request.getSession().getAttribute("idUser");
        } else {
            idUser = Long.parseLong(request.getParameter("idUser"));
        }

        String login = userService.getUserLogin(idUser);
        BigDecimal cash = userService.getUserCash(login);
        String statusUser = userService.getUser(login).getStatus().getName();

        List<UserOrderBean> userOrderList = orderBeanService.getAllOrdersForUserByLogin(login);

        request.getSession().setAttribute("statusUser", statusUser);
        request.getSession().setAttribute("cash", cash);
        request.getSession().setAttribute("userOrderList", userOrderList);
        request.getSession().setAttribute("idUser", idUser);

        return "WEB-INF/admin/showUser.jsp";
    }
}
