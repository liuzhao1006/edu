package com.lz.toy.config;

import com.lz.toy.interceptor.LoginInterceptor;
import com.lz.toy.interceptor.PermissionInterceptor;
import com.lz.toy.serivce.IPermissionService;
import com.lz.toy.serivce.ISysMenuService;
import com.lz.toy.serivce.ISysSettingService;
import com.lz.toy.serivce.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyWebAppConfigure  extends WebMvcConfigurerAdapter {

    private PermissionInterceptor permissionInterceptor;
    private LoginInterceptor loginInterceptor;

    @Autowired
    public MyWebAppConfigure(IPermissionService permissionService,
                             ISysUserService sysUserService) {
        this.permissionInterceptor = new PermissionInterceptor(permissionService);
        this.loginInterceptor =  new LoginInterceptor(sysUserService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
