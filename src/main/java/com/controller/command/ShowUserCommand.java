package com.controller.command;

import com.controller.Path;
import com.model.bean.UserOrderBean;
import com.model.entity.User;
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
        String stringIdUser = request.getParameter("idUser");
        long idUser;
        if (stringIdUser == null) {
            idUser = (long) request.getSession().getAttribute("idUser");

        } else {
            idUser = Long.parseLong(stringIdUser);
        }

        User user = userService.getUser(idUser);
        List<UserOrderBean> userOrderList = orderBeanService.getAllOrdersForUserByLogin(user.getLogin());

        request.setAttribute("userOrderList", userOrderList);
        request.setAttribute("user", user);

        return Path.WEB_INF_ADMIN_SHOW_USER_JSP;
    }
}
