package com.lz.toy.config;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;

import com.lz.toy.common.StringToDateConverter;
import com.lz.toy.serivce.*;
import com.lz.toy.serivce.impl.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Configuration
@MapperScan("com.lz.toy.mapper")
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class AdminServiceAutoConfiguration {
    /**
     * 數據庫分頁配置
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
        return paginationInterceptor;
    }

    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public StringToDateConverter stringToDateConverter() {
        return new StringToDateConverter();
    }

    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean getConversionService(StringToDateConverter stringToDateConverter) {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(stringToDateConverter);
        bean.setConverters(converters);
        return bean;
    }

    @Configuration
    public static class AdminServiceModule {
        @Bean
        @ConditionalOnMissingBean
        public ISysDeptService sysDeptService() {
            return new SysDeptServiceImpl();
        }

        @Bean
        @ConditionalOnMissingBean
        public ISysLogService sysLogService() {
            return new SysLogServiceImpl();
        }

        @Bean
        @ConditionalOnMissingBean
        public ISysSettingService sysSettingService() {
            return new SysSettingServiceImpl();
        }

        @Bean
        @ConditionalOnMissingBean
        public ITbPostUserInfoService tbPostUserInfoService() {
            return new ITbPostUserInfoServiceImpl();
        }


        @Bean
        @ConditionalOnMissingBean
        public IPermissionService permissionService(ISysMenuService sysMenuService) {
            return new PermissionServiceImpl(sysMenuService);
        }


    }

}
