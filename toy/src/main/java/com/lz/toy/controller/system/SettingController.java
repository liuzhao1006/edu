package com.lz.toy.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.lz.toy.common.anno.Log;
import com.lz.toy.common.anno.Permission;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.entity.SysSetting;
import com.lz.toy.serivce.ISysSettingService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统设置控制器
 * @author liuzhao
 */
@Controller
@RequestMapping("/system/setting")
public class SettingController extends SuperController {
	
	private ISysSettingService sysSettingService;

	public SettingController(ISysSettingService sysSettingService) {
		this.sysSettingService = sysSettingService;
	}

	/**
	 * 查询系统设置
	 */
	@Permission("listSetting")
    @RequestMapping("/page")  
    public  String page(Model model){
    	List<SysSetting> list =  sysSettingService.selectList(new EntityWrapper<SysSetting>().orderBy("sort",true));
    	model.addAttribute("list",list);
		return "system/setting/page";
    } 
    
	@Permission("doSetting")
    @Log("更新系统设置")
    @RequestMapping("/doSetting")
    public String doSetting(String[] id,String[] sysValue,RedirectAttributes redirectAttributes){
    	
    	List<SysSetting> sysSettings = new ArrayList<>();
    	if(ArrayUtils.isNotEmpty(id)){
    		for(int i=0;i<id.length;i++){
    			SysSetting setting	= new SysSetting();
        		setting.setId(id[i]);
        		setting.setSysValue(sysValue[i]);
        		sysSettings.add(setting);
    		}
    	}
    	sysSettingService.updateBatchById(sysSettings);
    	redirectAttributes.addFlashAttribute("info","OK,更新成功!");
		return redirectTo("/system/setting/page.ftl");
		
    }
    
}
