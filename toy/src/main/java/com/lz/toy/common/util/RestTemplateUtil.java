package com.lz.toy.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/rest")
public class RestTemplateUtil {
    private final RestTemplate restTemplate;
    @Autowired
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/post")
    public String restPost(String url, String postData) {
        JSONObject jsonData = JSONObject.parseObject(postData);
        JSONObject json = restTemplate.postForEntity(url, jsonData, JSONObject.class).getBody();
        return json.toJSONString();
    }

}
