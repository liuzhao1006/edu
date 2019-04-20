package com.lz.toy.interceptor;


import com.lz.toy.common.anno.Login;
import com.lz.toy.common.bean.Token;
import com.lz.toy.common.enums.Action;
import com.lz.toy.common.util.HttpUtil;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.entity.SysSetting;
import com.lz.toy.entity.SysUser;
import com.lz.toy.entity.vo.SysMenus;
import com.lz.toy.serivce.ISysMenuService;
import com.lz.toy.serivce.ISysSettingService;
import com.lz.toy.serivce.ISysUserService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 登录拦截器
 * @author liuzhao
 */
@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private ISysUserService sysUserService;

    public LoginInterceptor( ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            //登录验证
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Login login = method.getAnnotation(Login.class);
            if (login != null) {
                if (login.action() == Action.Skip) {
                    return true;
                }
            }
            //正常执行
            /*Token token = TokenUtil.getToken(request);
            if (token == null) {
                if (HttpUtil.isAjax(request)) {
                    HttpUtil.ajaxStatus(response, 302, "session expires.");
                    return false;
                } else {
                    TokenUtil.clearRedirectLogin(request, response);
                    return false;
                }
            } else {
                //保存登录信息
                SysUser me = sysUserService.selectById(token.getUid());
                me.setPassword(token.getOriginalPassword());
                request.setAttribute("me", me);
                // 资源和当前选中菜单
                String res = request.getParameter("res");
                if (StringUtils.isNotBlank(res)) {
                    request.getSession().setAttribute("res", res);
                }
                String cur = request.getParameter("cur");
                if (StringUtils.isNotBlank(cur)) {
                    request.getSession().setAttribute("cur", cur);
                }
            }*/
        }
        //通过拦截
        return true;
    }
}
