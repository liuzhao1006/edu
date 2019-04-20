package com.lz.toy.controller.system;

import com.alibaba.fastjson.JSON;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.util.CommonUtil;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.entity.*;
import com.lz.toy.serivce.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;


/**
 * 用户中心控制器
 * @author liuzhao
 */
@Controller
@RequestMapping("/system/me")
public class MeController extends SuperController {
	
	 private ISysUserService sysUserService;

	public MeController(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	 * 个人信息
	 */
    @RequestMapping("/info")  
    public  String info(Model model){
    	SysUser sysUser = sysUserService.selectById(TokenUtil.getToken(request).getUid());
    	model.addAttribute("sysUser", sysUser);
		return "system/me/info";
    } 
    
    
    /**
	 * 修改密码页面
	 */
    @RequestMapping("/pwd")  
    public  String pwd(Model model){
		SysUser user = sysUserService.selectById(TokenUtil.getToken(request).getUid());
		model.addAttribute("sysUser", user);
		return "system/me/pwd";
    } 
    
    /**
     * 修改密码
     */
    @RequestMapping("/doChangePwd")
	@ResponseBody
    public String doChangePwd(String newpassword2,String[] roleId){

		SysUser user = sysUserService.selectById(TokenUtil.getToken(request).getUid());
    	user.setPassword(CommonUtil.MD5(newpassword2));
		sysUserService.updateUser(user,roleId);
    	

		return JSON.toJSONString(true);
    }
    
    /**
     * 更新用户
     */
    @RequestMapping("/updateUser")
	@ResponseBody
    public String updateUser(SysUser sysUser,Model model,RedirectAttributes redirectAttributes){
    	
    	SysUser user = sysUserService.selectById(sysUser.getId());
    	if(StringUtils.isNotBlank(user.getUserImg())){
    		user.setUserImg(sysUser.getUserImg());
    	}
    	sysUserService.updateById(user);
    	model.addAttribute("sysUser", user);
    	/*redirectAttributes.addFlashAttribute("info","更新成功.");
    	return redirectTo("/system/me/info");*/
    	return JSON.toJSONString(true);
    }
	@ResponseBody
	@RequestMapping("/getoldpwd")
	public  String Getoldpwd(String newpwd){
		HashMap<Object,Object> param=new HashMap<>();
		SysUser user = sysUserService.selectById(TokenUtil.getToken(request).getUid());
		String oldpwd = user.getPassword();
		String newpwds = CommonUtil.MD5(newpwd);
		if(oldpwd.equals(newpwds)) {
			param.put("isright", "旧密码填写正确");
		}else{
			param.put("isright", "旧密码填写错误");
		}
		return  JSON.toJSONString(param);
	}
}
