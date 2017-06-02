package com.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.platform.entity.User;
import com.platform.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("userInfo")
	public String userInfo(HttpServletRequest request,Model model) {
		User user = userService.getUserById(Integer.parseInt(request.getParameter("uid")));
		return user.getUsername();
	}
}
