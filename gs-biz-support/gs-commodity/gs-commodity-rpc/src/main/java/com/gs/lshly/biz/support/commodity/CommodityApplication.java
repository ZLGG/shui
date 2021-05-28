package com.gs.lshly.biz.support.commodity;

import com.gs.lshly.biz.support.commodity.service.test.IESTestSrv;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * CommodityApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication(
    //启用es,注释exclude
    exclude = {ElasticsearchDataAutoConfiguration.class, ElasticsearchRepositoriesAutoConfiguration.class}
)
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.commodity.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.commodity.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.redis",
        "com.gs.lshly.biz.support.commodity",
        "com.gs.lshly.middleware.oss"}
    //启用es,注释exclude
    , excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = IESTestSrv.class)}
)
public class CommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class, args);
    }
}
