package com.gs.lshly.middleware.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class MybatisPlusConfig {
    /** 
     * mybatis-plus分页插件 
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

    @Bean
    public SqlExecuteTimeCountInterceptor timeCountInterceptor() {
        return new SqlExecuteTimeCountInterceptor();
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
            LocalDateTime now = LocalDateTime.now();
            this.setFieldValByName("flag", Boolean.FALSE, metaObject);
            this.setFieldValByName("cdate", now, metaObject);
            this.setFieldValByName("udate", now, metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.setFieldValByName("udate", LocalDateTime.now(), metaObject);
        }
    }
}
