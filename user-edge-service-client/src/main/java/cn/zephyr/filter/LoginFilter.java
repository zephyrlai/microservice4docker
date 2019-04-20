package cn.zephyr.filter;

import cn.zephyr.dto.UserInfoDTO;
import cn.zephyr.utils.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zephyrLai
 * @Description: 登录过滤器
 * @Date: 2019/4/11 13:21
 */
public abstract class LoginFilter implements Filter {
    private Cache<String,UserInfoDTO> localCache = CacheBuilder.newBuilder()
            .maximumSize(10000).expireAfterWrite(3, TimeUnit.MINUTES).build();

    private final String AUTHORIZATION = "Authorization";

    private final String USER_INFO_REQ_URL = "http://user-edge-service:8082/user/authorization";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 获取token(依次从request、session、header、cookie获取)
        String token = (String)request.getParameter(AUTHORIZATION);
        if(StringUtils.isBlank(token)){
            // session
            token = (String)request.getSession().getAttribute(AUTHORIZATION);
            if(StringUtils.isBlank(token)){
                // header
                token = (String)request.getHeader(AUTHORIZATION);
                if(StringUtils.isBlank(token)){
                    // cookie
                    Cookie[] cookies = request.getCookies();
                    if(null != cookies && cookies.length != 0){
                        for (Cookie cookie : cookies) {
                            if(cookie.getName().equals(AUTHORIZATION)){
                                token = cookie.getValue();
                                break;
                            }
                        }
                    }
                }
            }
        }
//        token = "7a504ee90158407e9d97a135be93e540";
        //
        UserInfoDTO userInfoDTO = null;
        if(StringUtils.isNotBlank(token)){
            userInfoDTO = localCache.getIfPresent(token);
            if(null == userInfoDTO){
                userInfoDTO = requestUserInfo(token);
                if(null != userInfoDTO)
                    localCache.put(token,userInfoDTO);
            }
        }
        if(null == userInfoDTO)
            response.sendRedirect("http://user-edge-service:8080/user/login");
        login(request,response,userInfoDTO);
        filterChain.doFilter(request,response);

    }


    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserInfoDTO userDTO);

    public void destroy() {

    }

    /**
     * 根据token请求用户信息
     * @param token
     * @return
     */
    private UserInfoDTO requestUserInfo(String token){
        try {
            Map<String,String> headerMap = new HashMap<String, String>();
            headerMap.put("token",token);
            String resultByGet = HttpClientUtils.getResponseResultByGet(USER_INFO_REQ_URL + "?token="+token,"utf-8",headerMap);
            if(StringUtils.isBlank(resultByGet)){
                return null;
            }
            return JSON.parseObject(resultByGet, UserInfoDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
