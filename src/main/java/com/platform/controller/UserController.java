package com.platform.controller;

import com.platform.entity.User;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("userInfo")
    public String userInfo(HttpServletRequest request, Model model) {
        User user = userService.getUserById(Integer.parseInt(request.getParameter("uid")));
        return "userinfo";
    }
}
