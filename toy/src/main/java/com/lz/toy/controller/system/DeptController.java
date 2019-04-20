package com.lz.toy.controller.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.lz.toy.common.anno.Log;
import com.lz.toy.common.anno.Permission;
import com.lz.toy.common.bean.Response;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.datatables.PageResult;
import com.lz.toy.common.datatables.SearchCondition;
import com.lz.toy.entity.SysDept;
import com.lz.toy.serivce.ISysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 部门控制器
 *
 * @author liuzhao
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends SuperController {

    private final ISysDeptService sysDeptService;

    public DeptController(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    @ResponseBody
    @RequestMapping(value = "/getpagedata", method = RequestMethod.POST)
    public String index(@RequestBody SearchCondition<SysDept> sysUserSearchCondition) {
        int pageNumber = sysUserSearchCondition.getStartIndex() / sysUserSearchCondition.getPageSize();
        if (pageNumber != 0) {
            pageNumber++;
        }
        Page<SysDept> page = getPage(pageNumber, sysUserSearchCondition.getPageSize());
        // 查询分页
        EntityWrapper<SysDept> ew = new EntityWrapper<>();


        if (sysUserSearchCondition.getCondition() != null &&
                StringUtils.isNotBlank(sysUserSearchCondition.getCondition().getDeptName())) {
            ew.like("dept_name", sysUserSearchCondition.getCondition().getDeptName());
        }
        Page<SysDept> pageData = sysDeptService.selectPage(page, ew);
        PageResult<SysDept> result = new PageResult<>(pageData.getRecords(), pageData.getTotal(),
                sysUserSearchCondition.getDraw());

        return JSON.toJSONString(result);
    }

    /**
     * 分页查询部门
     */
    @Permission("listDept")
    @RequestMapping("/list/{pageNumber}")
    public String list() {
        return "system/dept/list";
    }

    /**
     * 新增部门
     */
    @Permission("addDept")
    @RequestMapping("/add")
    public String add() {
        return "system/dept/add";
    }

    /**
     * 执行新增
     */
    @Permission("addDept")
    @Log("创建部门")
    @RequestMapping("/doAdd")
    public String doAdd(SysDept dept) {

        sysDeptService.insert(dept);
        return redirectTo("/system/dept/list/1");
    }

    /**
     * 删除部门
     */
    @Permission("deleteDept")
    @Log("删除部门")
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(String id) {
        sysDeptService.deleteById(id);
        return new Response().success();
    }

    /**
     * 编辑部门
     */
    @Permission("editDept")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        SysDept dept = sysDeptService.selectById(id);
        model.addAttribute("dept", dept);
        return "system/dept/edit";
    }

    /**
     * 执行编辑
     */
    @Permission("editDept")
    @Log("编辑部门")
    @RequestMapping("/doEdit")
    public String doEdit(SysDept sysDept) {
        sysDeptService.updateById(sysDept);
        return redirectTo("/system/dept/list/1");
    }

}
