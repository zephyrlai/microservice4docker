package cn.zephyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: zephyrLai
 * @Description: spring boot 启动类
 * @Date: 2019/4/10 14:09
 */
@SpringBootApplication
@EnableZuulProxy
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }

}
