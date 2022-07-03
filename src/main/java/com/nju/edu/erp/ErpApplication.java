package com.nju.edu.erp;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
@SpringBootApplication
public class ErpApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }
}
