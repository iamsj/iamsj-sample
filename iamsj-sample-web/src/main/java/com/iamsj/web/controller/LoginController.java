package com.iamsj.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private Producer producer;

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		this.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String password, String captcha, Model model) throws IOException {

		String kaptcha = this.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			model.addAttribute("errorMsg", "验证码不正确");
			return "login";
		}

		try {
			Subject subject = this.getSubject();
			// sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "login";
		} catch (LockedAccountException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "login";
		} catch (AuthenticationException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "login";
		}

		return "home";
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:login.html";
	}
}
