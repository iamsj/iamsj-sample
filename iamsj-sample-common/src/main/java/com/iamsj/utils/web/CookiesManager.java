package com.iamsj.utils.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookiesManager {

    private static Logger logger = LoggerFactory.getLogger(CookiesManager.class);

    // session 最小生命周期
    public static final int SESSION_MINAGE = -1;

    // session最大生命周期
    public static final int SESSION_MAXAGE = 3 * 24 * 60 * 60;

    /**
     * 对当前域名添加cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge A positive value indicates that the cookie will expire after that many seconds have passed. Note
     *            that the value is the maximum age when the cookie will expire, not the cookie's current age.
     * 
     *            A negative value means that the cookie is not stored persistently and will be deleted when the Web
     *            browser exits. A zero value causes the cookie to be deleted.
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        String domain = parseDomainFromRequest(request);
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 
     * 功能描述: <br>
     * 对顶级域名进行cookie操作
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @throws UnsupportedEncodingException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setTopDomainCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge)
            throws UnsupportedEncodingException {
        String domain = parseDomainFromRequest(request);
        if (StringUtils.isNotEmpty(value)) {
            value = URLEncoder.encode(value, "utf-8");
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(parseTopDomainFromRequest(domain));
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (StringUtils.equals(cookie.getName(), name)) {
                    return decode(cookie.getValue());
                }
            }
        }
        return null;
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        setCookie(request, response, name, StringUtils.EMPTY, 0);
    }

    private static String parseDomainFromRequest(HttpServletRequest request) {
        String host = RequestUtil.getHostName(request);
        int i = host.indexOf(":");
        if (i > 0) {
            host = host.substring(0, i);
        }
        return host;
    }

    private static String parseTopDomainFromRequest(String domain) {

        if (StringUtils.countMatches(domain, ".") > 1) {
            domain = domain.substring(domain.indexOf(".") + 1, domain.length());
            return parseTopDomainFromRequest(domain);
        } else {
            return domain;
        }

    }

    private static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}
