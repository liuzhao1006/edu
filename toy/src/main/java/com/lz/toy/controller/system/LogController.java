package com.lz.toy.controller.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import com.lz.toy.common.anno.Permission;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.datatables.PageResult;
import com.lz.toy.common.datatables.SearchCondition;
import com.lz.toy.entity.SysLog;
import com.lz.toy.serivce.ISysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 日志控制器
 *
 *@author liuzhao
 */
@Controller
@RequestMapping("/system/log")
public class LogController extends SuperController {

    private final ISysLogService sysLogService;

    public LogController(ISysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @ResponseBody
    @RequestMapping(value = "/getpagedata", method = RequestMethod.POST)
    public String index(@RequestBody SearchCondition<SysLog> sysLogSearchCondition) {
        int pageNumber = sysLogSearchCondition.getStartIndex() / sysLogSearchCondition.getPageSize();
        if (pageNumber != 0) {
            pageNumber++;
        }
        Page<SysLog> page = getPage(pageNumber, sysLogSearchCondition.getPageSize());
        if( sysLogSearchCondition.getOrderColumns()!=null && sysLogSearchCondition.getOrderColumns().size() > 0){
            page.setOrderByField(sysLogSearchCondition.getOrderColumns().get(0).getOrderColumn());
            //TODO 此处要确认非升序就是降序？
            if(sysLogSearchCondition.getOrderColumns().get(0).getOrderDir().equals("asc")){
                page.setAsc(true);
            }else{
                page.setAsc(false);
            }
        }else{
            page.setOrderByField("id");
            page.setAsc(false);
        }
        HashMap<String,Object> param=new HashMap<>();
        if(sysLogSearchCondition.getCondition()!=null){
            if(StringUtils.isNotBlank(sysLogSearchCondition.getCondition().getTitle())){

                param.put("title",sysLogSearchCondition.getCondition().getTitle());}

            if(StringUtils.isNotBlank(sysLogSearchCondition.getCondition().getStartDate())){

                param.put("startDate",sysLogSearchCondition.getCondition().getStartDate());}

            if(StringUtils.isNotBlank(sysLogSearchCondition.getCondition().getEndDate())){

                param.put("endDate",sysLogSearchCondition.getCondition().getEndDate());}

        }
        if(page.getSize()==-1){
            page.setSize(10);
            Page<SysLog> pageData = sysLogService.selectLogList(page,param);
            page.setSize(pageData.getTotal());
        }
        Page<SysLog> pageData = sysLogService.selectLogList(page,param);
        PageResult<SysLog> result =  new PageResult<SysLog>(pageData.getRecords(),pageData.getTotal(),sysLogSearchCondition.getDraw());
        return  JSON.toJSONString(result);
    }

    /**
     * 分页查询日志
     */

    @RequestMapping("/list/{pageNumber}")
    public String list() {
        return "system/log/list";
    }

    /**
     * 获取参数
     */
    @Permission("listLog")
    @RequestMapping("/params/{id}")
    @ResponseBody
    public String params(@PathVariable String id, Model model) {
        SysLog sysLog = sysLogService.selectById(id);
        return sysLog.getParams();
    }

}
