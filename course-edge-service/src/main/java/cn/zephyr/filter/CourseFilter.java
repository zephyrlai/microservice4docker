package cn.zephyr.filter;

import cn.zephyr.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 17:01
 */
@Component
public class CourseFilter extends LoginFilter {

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserInfoDTO userDTO) {
//        HttpServletRequest httpRequest =  (HttpServletRequest)request;
        System.err.println("---用户登录课程服务---");
    }
}
