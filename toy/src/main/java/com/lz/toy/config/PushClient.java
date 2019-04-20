package com.lz.toy.config;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class PushClient {
    private static final MediaType JSON_TYPE
            = MediaType.parse("application/json; charset=utf-8");
    private static final String URL = "http://msg.umeng.com/api/send";
    private static final String APP_KEY = "59e9909bb27b0a75b4001369";
    private static final String APP_MASTER_SECRET = "oex7owtbhola9wyty1p7nzqzhrkeni2c";
    private static final OkHttpClient client = new OkHttpClient();
    @SneakyThrows
    static boolean send(String str){
        String sign = DigestUtils.md5Hex(("POST" + URL + str + APP_MASTER_SECRET).getBytes("utf8"));
        String postUrl = URL+"?sign="+sign;
        System.out.println(sign);
        RequestBody body = RequestBody.create(JSON_TYPE, str);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        log.info(response.body().string());
        return  response.isSuccessful();
    }

    public static boolean sendMessage(PushEntity pushEntity){
        String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
        PushEntity push = pushEntity.setAppkey(APP_KEY)
                .setType("customizedcast")
                .setProduction_mode("true")
                .setTimestamp(timestamp);
        return send(JSON.toJSONString(push));
    }

     static boolean sendMessage(String userId,String description, PushEntity.PayloadBean payloadBean){
       return sendMessage(new PushEntity()
                .setDescription(description)
                .setAlias(userId)
                .setAlias_type("4")
                .setPayload(payloadBean));
    }

    public static void main(String[] args) {
        sendMessage(new PushEntity()
                .setDescription("用车报警")
                .setAlias("61")
                .setAlias_type("4")
                .setPayload(new PushEntity.PayloadBean()
                        .setBody(new PushEntity.PayloadBean.BodyBean()
                                .setTicker("用车报警推送")
                                .setTitle("实时报警")
                                .setText("超速报警(速度=72Km/h)"))));
    }
}
