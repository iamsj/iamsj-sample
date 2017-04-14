/**
 * 
 */
package com.iamsj.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.iamsj.model.User;

/**
 * @author jason
 *
 */
public abstract class BaseController {
	
	public Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public User getUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}
