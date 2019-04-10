package cn.zephyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zephyrLai
 * @Description: spring boot 启动类
 * @Date: 2019/4/10 14:09
 */
@SpringBootApplication
@ComponentScan("cn.zephyr")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }
}
