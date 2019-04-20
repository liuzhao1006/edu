package com.lz.toy.controller;

import com.lz.toy.common.bean.Token;
import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.serivce.ISysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 首页控制器
 * @author liuzhao
 *
 */
@Controller
@RequestMapping("/")
public class IndexController extends SuperController {

    private final ISysUserService iSysUserService;



    public IndexController(ISysUserService iSysUserService) {


        this.iSysUserService = iSysUserService;

    }

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        Token token = TokenUtil.getToken(request);
        return "";

    }





    private Integer[] putValue(Integer[] attr, int len) {
        for (int i = 0; i < len; i++) {
            attr[i] = 0;
        }
        return attr;
    }

    private String getBeforeDayTime(Date currDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        Date time = null;
        try {
            //当前时间
            time = date.parse(date.format(currDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获取前一天时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String Up_date = date.format(calendar.getTime());
        return Up_date;
    }


}
