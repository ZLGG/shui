package com.gs.lshly.biz.support.foundation.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootConfiguration
public class MybatisPlusConfig {
    /** 
     * mybatis-plus分页插件 
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }


    @Bean
    public MybatisPlusFiller filler()  {
        return new MybatisPlusFiller();
    }

    public static class MybatisPlusFiller implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.setFieldValByName("flag", Boolean.FALSE, metaObject);
            this.setFieldValByName("udate", LocalDateTime.now(), metaObject);
            this.setFieldValByName("cdate", LocalDateTime.now(), metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.setFieldValByName("udate", LocalDateTime.now(), metaObject);
        }
    }
}
