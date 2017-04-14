package com.iamsj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iamsj.model.User;

@Controller
@RequestMapping("/sys")
public class MenuController extends BaseController {

	/**
	 * 系统菜单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/menu")
	public String menu(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = this.getUser();

		return "";
	}
}
