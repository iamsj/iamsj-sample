package com.iamsj.utils.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SuppressWarnings("unchecked")
public final class RequestUtil {

    /**
     * 获取请求中的数据
     * 
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> findRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // try {
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            // } catch (UnsupportedEncodingException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            params.put(name, valueStr);
        }
        return params;
    }

    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    public static String getAppURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0)
            port = 80;
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if (scheme.equals("http") && port != 80 || scheme.equals("https") && port != 443) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        Cookie returnCookie = null;
        if (cookies == null)
            return returnCookie;
        int i = 0;
        do {
            if (i >= cookies.length)
                break;
            Cookie thisCookie = cookies[i];
            if (thisCookie.getName().equals(name) && !thisCookie.getValue().equals("")) {
                returnCookie = thisCookie;
                break;
            }
            i++;
        } while (true);
        return returnCookie;
    }

    public static final String getErrorUrl(HttpServletRequest request) {
        String errorUrl = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (errorUrl == null)
            errorUrl = (String) request.getAttribute("javax.servlet.forward.request_uri");
        if (errorUrl == null)
            errorUrl = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (errorUrl == null)
            errorUrl = request.getRequestURL().toString();
        return errorUrl;
    }

    public static Cookie getEStoreCookie(HttpServletRequest request) {
        return getCookie(request, "eStore");
    }

    public static final String getFullRequestUrl(HttpServletRequest req) {
        return (req.getQueryString() != null ? req.getRequestURL().append("?").append(req.getQueryString()) : req.getRequestURL()).toString();
    }

    public static Integer getInteger(HttpServletRequest request, String paramName) {
        String id = request.getParameter(paramName);
        if (id != null && !id.equals(""))
            try {
                return new Integer(id);
            } catch (Exception e) {
                return null;
            }
        else
            return null;
    }

    public static Integer[] getIntegerArray(HttpServletRequest request, String paramName) {
        Integer iIds[] = null;
        String strIds[] = request.getParameterValues(paramName);
        if (strIds != null && strIds.length > 0) {
            iIds = new Integer[strIds.length];
            for (int i = 0; i < strIds.length; i++)
                iIds[i] = new Integer(strIds[i]);

        }
        return iIds;
    }

    public static String getParameterNullSafe(HttpServletRequest request, String paramName) {
        String ret = request.getParameter(paramName);
        if (ret == null)
            ret = "";
        return ret.trim();
    }

    public static String getRequestedPageName(HttpServletRequest req, String defaultViewName) {
        int idx2 = req.getRequestURI().lastIndexOf(".");
        if (idx2 > 0)
            return req.getRequestURI().substring(getUrlPrefixIdx(req), idx2);
        String uri = req.getRequestURI().substring(getUrlPrefixIdx(req));
        if (!uri.endsWith("/"))
            uri = (new StringBuilder()).append(uri).append("/").append(defaultViewName).toString();
        else
            uri = (new StringBuilder()).append(uri).append(defaultViewName).toString();
        return uri;
    }

    public static Map getRequestMap(HttpServletRequest req) {
        Map params = new HashMap();
        Enumeration emu = req.getParameterNames();
        do {
            if (!emu.hasMoreElements())
                break;
            String key = (String) emu.nextElement();
            String values[] = req.getParameterValues(key);
            if (values != null)
                if (values.length == 1)
                    params.put(key, values[0]);
                else
                    params.put(key, values);
        } while (true);
        return Collections.unmodifiableMap(params);
    }

    public static int getUrlPrefixIdx(HttpServletRequest req) {
        if (urlPrefixIdx == 0) {
            String contextPath = req.getContextPath();
            int idx1 = req.getRequestURI().indexOf(contextPath);
            urlPrefixIdx = idx1 + contextPath.length();
        }
        return urlPrefixIdx;
    }

    public static String getUserLocaleCode(HttpServletRequest request) {
        return (String) request.getAttribute("CUR_LOCALE_CODE");
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String path, String domain) {
        setCookie(response, name, value, path, 30 * 24 * 60 * 60);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        if (logger.isDebugEnabled())
            logger.debug((new StringBuilder()).append("Setting cookie '").append(name).append("' on path '").append(path).append("'").toString());
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void setUserIdCookie(HttpServletResponse response, Integer userId, String ctxPath) {
        Cookie cookie = new Cookie("UID", String.valueOf(userId));
        cookie.setPath(ctxPath);
        response.addCookie(cookie);
    }

    public static String notifyOtherHosts(String url) {
        String str_1 = "";
        String str_2 = "";

        try {
            String newsURLName = url;
            URL servlet = new URL(newsURLName);
            URLConnection con = servlet.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            java.io.InputStream uin = servlet.openStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(uin));

            while ((str_1 = in.readLine()) != null) {
                if (!str_1.equals("")) {
                    str_2 = str_1;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return str_2;
    }

     

    public static String getURLData(String urlStr) {

        StringBuilder sb = new StringBuilder();

        try {

            URL url = new URL(urlStr);

            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

            urlConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            String line = "";

            while ((line = reader.readLine()) != null) {

                sb.append(line);

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return sb.toString();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    private RequestUtil() {
    }

    private static transient Log logger = LogFactory.getLog(RequestUtil.class);
    private static int urlPrefixIdx = 0;
    private static final String NGINX_IP_HEADER = "X-Real-IP";
    private static final String NGINX_X_Forwarded_For = "X-Forwarded-For";

    /**
     * 功能描述: 获取ip（兼容nginx转发）
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ips = request.getHeader(NGINX_X_Forwarded_For);
        String[] ipArray = org.apache.commons.lang3.StringUtils.split(ips, ",");
        if (ArrayUtils.isNotEmpty(ipArray)) {
            return org.apache.commons.lang3.StringUtils.trim(ipArray[0]);
        } else {
            String ip = request.getHeader(NGINX_IP_HEADER);
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }

    /**
     * 获取用户代理
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent;
    }

    /**
     * 
     * 功能描述: <br>
     * 获取hostname
     *
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getHostName(HttpServletRequest request) {
        String host = request.getHeader("Host");
        return host;
    }
}
