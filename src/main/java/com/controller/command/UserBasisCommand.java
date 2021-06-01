package com.controller.command;

import com.model.bean.UserOrderBean;
import com.model.constants.Constants;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class UserBasisCommand implements Command {
    private final UserOrderBeanService orderBeanService = new UserOrderBeanService();
    private final UserService userService = new UserService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        BigDecimal cash = userService.getUserCash(login);
        List<UserOrderBean> userOrderList = null;

        try {
            userOrderList = orderBeanService.getAllOrdersForUserByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("cash", cash);
        request.getSession().setAttribute("userOrderList", userOrderList);
        return Constants.WEB_INF_USER_USERBASIS_JSP;
    }
}
