package com.lz.toy.controller;

import com.lz.toy.common.controller.SuperController;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuzhao
 */
@Log4j
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends SuperController {
    @RequestMapping(value = "/index")
    public String index() {
        return "system/attachment/index";
    }
}
