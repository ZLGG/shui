package com.citydo.appraisal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(basePackages = {"com.citydo.appraisal.mapper"})
@SpringBootApplication
public class AppraisalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppraisalApplication.class, args);
    }

}
