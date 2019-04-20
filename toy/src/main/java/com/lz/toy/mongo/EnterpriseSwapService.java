package com.lz.toy.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 联网联控考核预警类
 */
@Slf4j
@Service
public class EnterpriseSwapService {

    private final MongoTemplate mongoTemplate;


    public EnterpriseSwapService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }

    public void sum() {
        Date start = Date.from(LocalDate.now().plusDays(-1).atTime(0, 0, 0).atZone(ZoneId.systemDefault()).toInstant());
        Date stop = Date.from(LocalDate.now().plusDays(-1).atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
        List<EnterpriseSwap> enterprise = mongoTemplate
                .find(query(where("analyse_date").gte(start).lte(stop)), EnterpriseSwap.class);

        Integer overSpeeds = enterprise.stream()
                .map(EnterpriseSwap::getOverSpeedCount)
                .reduce((a, b) -> a + b).orElse(0); //所有企业超速总和

        Integer tiredDuration = enterprise.stream()
                .map(EnterpriseSwap::getTiredDuration)
                .reduce((a, b) -> a + b).orElse(0); //所有企业疲劳驾驶总时长

        Integer vehicleCount = enterprise.stream()
                .map(EnterpriseSwap::getVehicleCount)
                .reduce((a, b) -> a + b).orElse(1); // 所有企业的车辆总数

        double avgOverSpeed = (overSpeeds / vehicleCount) < 1 ? 1 : (overSpeeds / vehicleCount); //区域平均车辆超速值

        double avgTired = (tiredDuration / vehicleCount) < 1 ? 1 : (tiredDuration / vehicleCount); //区域平均疲劳驾驶时长


    }


    public void realTimeAlarm(int s) { //s:-39
        LocalDateTime now = LocalDateTime.now();
        Date start = Date.from(now.plusSeconds(s).atZone(ZoneId.systemDefault()).toInstant());
        Date stop = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
//        log.info("**********************************start:"+start);
//        log.info("**********************************stop:"+stop);

        long startTime = start.getTime();
        long stopTime = stop.getTime();
        List<AlarmInfoRealtime> enterprise = mongoTemplate.
                find(query(new Criteria()
                                .orOperator(where("alarms.1.alarm_time").gte(start).lte(stop),
                                        where("alarms.2.alarm_time").gte(start).lte(stop),
                                        where("alarms.10.alarm_time").gte(start).lte(stop),
                                        where("alarms.11.alarm_time").gte(start).lte(stop),
                                        where("alarms.14.alarm_time").gte(start).lte(stop),
                                        where("alarms.15.alarm_time").gte(start).lte(stop)))
                        , AlarmInfoRealtime.class);

        //  enterprise.forEach(e -> log.info("enterprise.list:"+e.toString()));

        Map<Integer, List<AlarmInfoRealtime>> listMap = enterprise.stream()
                .collect(Collectors.groupingBy(AlarmInfoRealtime::getEnterpriseId));


    }


    /**
     * 计算得分(数据比率小于报警门限不得分)
     *
     * @param alarmRate 报警门限
     * @param value     当前值
     * @param score     得分值
     */
    private double calc(double alarmRate, double value, int score) {
        double result;
        if (value <= alarmRate) { //数据比率小于报警门限不得分
            result = 0;
        } else {
            result = score * value;
        }
        return result;
    }


}
