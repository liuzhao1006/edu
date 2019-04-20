package com.lz.toy.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.google.common.base.Preconditions;

import com.lz.toy.common.anno.Login;
import com.lz.toy.common.anno.Time;
import com.lz.toy.common.bean.Token;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.enums.Action;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.entity.SysUser;
import com.lz.toy.serivce.ISysLogService;
import com.lz.toy.serivce.ISysUserService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 登录控制器
 *
 * @author liuzhao
 */
@Log4j
@Controller
@RequestMapping("/login")
public class LoginController extends SuperController {

    /**
     * 用户服务
     */
    private final ISysUserService sysUserService;
    /**
     * 日志服务
     */
    private final ISysLogService sysLogService;

    @Autowired
    public LoginController(ISysUserService sysUserService, ISysLogService sysLogService) {
        this.sysUserService = sysUserService;
        this.sysLogService = sysLogService;
    }

    /**
     * 登录页面
     *
     * @throws UnsupportedEncodingException
     */
    @Login(action = Action.Skip)
    @RequestMapping(value = {"", "/", "/index"})
    public String login(String return_url, Model model) throws UnsupportedEncodingException {
        String index = "/index";
        model.addAttribute("return_url", StringUtils.isNotBlank(return_url) ? URLDecoder.decode(return_url, "UTF-8") : index);
        return "login";
    }

    /**
     * 执行登录
     */
    @Time
    @Login(action = Action.Skip)
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(String userName, String password, String captcha, String return_url) {
        ModelAndView view = new ModelAndView(redirectTo("/index"));
        String sessionCaptcha = new KaptchaExtend().getGeneratedKey(request);
        try {
            Preconditions.checkArgument(StringUtils.isBlank(userName) ||
                    StringUtils.isBlank(captcha) ||
                    StringUtils.isBlank(password) ||
                    sessionCaptcha.equalsIgnoreCase(captcha));
            SysUser sysUser = sysUserService.login(userName, password);
            if (sysUser == null) {
                view.addObject("loginError", "用户名或密码错误.");
                view.setViewName("login");
            } else {
                //登录成功
                Token token = new Token();
                token.setUid(sysUser.getId().toString());
                token.setUname(sysUser.getUserName());
                token.setOriginalPassword(password);
                token.setOrganize_id(sysUser.getUnitId().toString());
                token.setUser_type(sysUser.getUserType());
                TokenUtil.addToken(token, request);
                //记录登录日志
                sysLogService.insertLog("用户登录成功", sysUser.getUserName(), request.getRequestURI(), "******");
                if (StringUtils.isNotBlank(return_url)) {
                    view.setViewName(redirectTo(return_url));
                }
            }
        } catch (IllegalArgumentException e) {
            view.addObject("loginError", "验证码输入有误！");
            view.setViewName("login");
        }
        return view;
    }

    /**
     * 退出系统
     */
    @Login(action = Action.Skip)
    @RequestMapping(value = "/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Token st = TokenUtil.getToken(request);
        TokenUtil.clearLogin(request);
        response.sendRedirect("/login");
        sysLogService.insertLog("用户退出系统", st != null ? st.getUname() : "***", request.getRequestURI(), "******");
    }

    /**
     * 验证码
     */
    @Login(action = Action.Skip)
    @RequestMapping("/captcha")
    @ResponseBody
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        KaptchaExtend kaptchaExtend = new KaptchaExtend();
        kaptchaExtend.captcha(request, response);
    }
    /**
     * 校验验证码
     */
    @Login(action = Action.Skip)
    @RequestMapping("/checkCaptcha/{captcha}")
    @ResponseBody
    public String checkCaptcha(@PathVariable String captcha) {
        String sessionCaptcha = new KaptchaExtend().getGeneratedKey(request);
        boolean b ;
        try {
            Preconditions.checkArgument(StringUtils.isBlank(captcha) ||
                    sessionCaptcha.equalsIgnoreCase(captcha));
            b = true;
        }catch (Exception e){
            b = false;
        }
        return JSON.toJSONString(b);
    }
}
