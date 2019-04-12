package cn.zephyr;

import cn.zephyr.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zephyrLai
 * @Description: spring boot 启动类
 * @Date: 2019/4/10 14:09
 */
@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

    /**
     * 统一添加filter
     * @param courseFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(CourseFilter courseFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        // 拦截路径
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        // 添加拦截器
        filterRegistrationBean.setFilter(courseFilter);
        return filterRegistrationBean;
    }
}
