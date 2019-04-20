package com.lz.toy.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;

import com.lz.toy.common.anno.Log;
import com.lz.toy.common.anno.Permission;
import com.lz.toy.common.bean.Response;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.datatables.PageResult;
import com.lz.toy.common.datatables.SearchCondition;
import com.lz.toy.entity.SysMenu;
import com.lz.toy.serivce.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author liuzhao
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends SuperController {

    /**
     * 菜单服务
     */
    private ISysMenuService sysMenuService;

    public MenuController(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }


    @Permission("listMenu")
    @RequestMapping(value = "/index")
    public String index() {
        return "system/menu/list";
    }

    @ResponseBody
    @RequestMapping(value = "/getpagedata", method = RequestMethod.POST)
    public String index(@RequestBody SearchCondition<SysMenu> sysUserSearchCondition) {

        int pageNumber = sysUserSearchCondition.getStartIndex() / sysUserSearchCondition.getPageSize();
        if (pageNumber != 0) {
            pageNumber++;
        }
        Page<SysMenu> page = getPage(pageNumber, sysUserSearchCondition.getPageSize());
        if (sysUserSearchCondition.getOrderColumns() != null && !sysUserSearchCondition.getOrderColumns().isEmpty()) {
            page.setOrderByField(sysUserSearchCondition.getOrderColumns().get(0).getOrderColumn());
            if (sysUserSearchCondition.getOrderColumns().get(0).getOrderDir().equals("asc")) {
                page.setAsc(true);
            }else{
                page.setAsc(false);
            }
        }else{
            page.setOrderByField("id");
            page.setAsc(false);
        }
        EntityWrapper<SysMenu> ew = new EntityWrapper<>();
        if (sysUserSearchCondition.getCondition() != null &&
                StringUtils.isNotBlank(sysUserSearchCondition.getCondition().getMenuName())) {
            ew.like("menu_name", sysUserSearchCondition.getCondition().getMenuName());
        }
        if (sysUserSearchCondition.getCondition() != null &&
                StringUtils.isNotBlank(sysUserSearchCondition.getCondition().getMenuParentName())) {
            EntityWrapper<SysMenu> ewmenu = new EntityWrapper<>();
            ewmenu.eq("menu_name", sysUserSearchCondition.getCondition().getMenuParentName());
            SysMenu menuOne = sysMenuService.selectOne(ewmenu);
            if(menuOne != null){
                ew.eq("pid", menuOne.getId());
            }else{
                ew.eq("pid", "");
            }
        }

        Page<SysMenu> pageData = sysMenuService.selectPage(page, ew);
        getViewMenu(pageData);

        PageResult<SysMenu> result = new PageResult<>(pageData.getRecords(), pageData.getTotal(), sysUserSearchCondition.getDraw());
        return JSON.toJSONString(result);
    }

    private void getViewMenu(Page<SysMenu> pageData) {
        for (SysMenu menu : pageData.getRecords()) {
            if (menu.getPid() == null || menu.getDeep() != 3) {
                menu.setMenuName(StringUtils.join("<i class='fa fa-folder-open'></i> ", menu.getMenuName()));
            } else {
                menu.setMenuName(StringUtils.join("<i class='fa fa-file'></i> ", menu.getMenuName()));
            }
            for (int i = 1; i < menu.getDeep(); i++) {
                menu.setMenuName(StringUtils.join("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", menu.getMenuName()));
            }

        }
    }

    /**
     * 分页查询菜单
     */
    @Permission("listMenu")
    @RequestMapping("/list/{pageNumber}")
    public String list() {
        return "system/menu/list";
    }

    /**
     * 增加菜单
     */
    @Permission("addMenu")
    @RequestMapping("/add")
    public String add(Model model) {

        EntityWrapper<SysMenu> ew = new EntityWrapper<>();
        ew.orderBy("code", true);
        ew.eq("pid", "0");
        List<SysMenu> list = sysMenuService.selectList(ew);
        model.addAttribute("list", list);
        return "system/menu/add";

    }

    /**
     * 添加目录
     */
    @Permission("addMenu")
    @Log("创建目录菜单")
    @RequestMapping("/doAddDir")
    public String doAddDir(SysMenu sysMenu) {
        if(sysMenu.getMenuName()!="") {
            sysMenu.setPid("0");
            sysMenu.setDeep(1);
            sysMenuService.insert(sysMenu);
        }
        return redirectTo("/system/menu/list/1");
    }

    /**
     * 添加菜单
     */
    @Permission("addMenu")
    @Log("创建菜单")
    @RequestMapping("/doAddMenu")
    public String doAddMenu(SysMenu sysMenu) {
        if(sysMenu.getMenuName()!="") {
                sysMenu.setDeep(2);
                sysMenuService.insert(sysMenu);
        }

        return redirectTo("/system/menu/list/1.html");
    }

    /**
     * 编辑菜单
     */
    @Permission("editMenu")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        SysMenu sysMenu = sysMenuService.selectById(id);
        model.addAttribute("sysMenu", sysMenu);

        if (sysMenu.getDeep() == 1) {
            return "/system/menu/edit";
        } else if (sysMenu.getDeep() == 2) {
            EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
            ew.orderBy("code", true);
            ew.eq("pid", "0");
            List<SysMenu> list = sysMenuService.selectList(ew);
            model.addAttribute("list", list);
            return "/system/menu/editMenu";
        } else {
            EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
            ew.orderBy("code", true);
            ew.eq("pid", "0");
            List<SysMenu> list = sysMenuService.selectList(ew);
            model.addAttribute("list", list);
            model.addAttribute("pSysMenu", sysMenuService.selectById(sysMenu.getPid()));
            return "/system/menu/editAction";
        }
    }

    /**
     * 执行编辑菜单
     */
    @Permission("editMenu")
    @Log("编辑菜单")
    @RequestMapping("/doEdit")
    @ResponseBody
    public String doEdit(SysMenu sysMenu, Model model) {
        boolean b = sysMenuService.updateById(sysMenu);
      /*  return redirectTo("/system/menu/list/1.html");*/
        return JSON.toJSONString(b);
    }

    /**
     * 执行编辑菜单
     */
    @Permission("deleteMenu")
    @Log("删除菜单")
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(String id) {
        sysMenuService.deleteById(id);
        return new Response().success();
    }

    /**
     * 根据父节点获取子菜单
     */
    @RequestMapping("/json")
    @ResponseBody
    public JSONObject json(String pid) {
        Map<String, Object> maps = new HashMap<>();
        EntityWrapper<SysMenu> ew = new EntityWrapper<>();
        ew.orderBy("sort");
        ew.addFilter("pid = {0} ", pid);
        List<SysMenu> list = sysMenuService.selectList(ew);

        List<Map<String, Object>> listMap = new ArrayList<>();
        for (SysMenu m : list) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", m.getId());
            map.put("text", StringUtils.join(m.getCode(), "-", m.getMenuName()));
            listMap.add(map);
        }
        maps.put("datainfo", listMap);
        return new JSONObject(maps);
    }


    /**
     * 验证菜单资源名称是否存在
     */
    @RequestMapping("/checkMenuResource")
    @ResponseBody
    public String checkMenuResource(String resource) {

        List<SysMenu> list = sysMenuService.selectList(new EntityWrapper<SysMenu>().addFilter("resource = {0}", resource));
        if (list.size() > 0) {
            return "{\"error\":\" " + resource + " 资源已存在,请换一个尝试.\"}";
        }
        return "{\"ok\":\"资源名称很棒.\"}";
    }

    @Permission("addMenu")
    @Log("新增功能菜单")
    @RequestMapping("/doAddAction")
    public String doAddAction(SysMenu sysMenu) {
        if(sysMenu.getMenuName()!="") {
            sysMenu.setDeep(3);
            sysMenuService.insert(sysMenu);
        }
        return redirectTo("/system/menu/list/1");
    }


    @RequestMapping("/details/{id}")
    public String detail(@PathVariable String id, Model model) {
        SysMenu sysMenu = sysMenuService.selectById(id);
        Integer menuDeep = sysMenu.getDeep();
        model.addAttribute("sysMenu",sysMenu);
        if (menuDeep == 1) {
            return "/system/menu/parentDetail";
        } else if (menuDeep == 2) {
            String pid=sysMenu.getPid();
            SysMenu pMenu=sysMenuService.selectOne(new EntityWrapper<SysMenu>().eq("id",pid));
            model.addAttribute("pMenu",pMenu);
            return "/system/menu/sonDetail";
        } else {
            String pid=sysMenu.getPid();
            SysMenu pMenu=sysMenuService.selectOne(new EntityWrapper<SysMenu>().eq("id",pid));
            model.addAttribute("pMenu",pMenu);
            String bigPid=pMenu.getPid();
            SysMenu Menu=sysMenuService.selectOne(new EntityWrapper<SysMenu>().eq("id",bigPid));
            model.addAttribute("Menu",Menu);
            return "/system/menu/childDetail";

        }
    }
}
