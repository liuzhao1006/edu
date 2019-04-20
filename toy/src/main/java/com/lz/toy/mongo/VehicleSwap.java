package com.lz.toy.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 车辆维度中间表
 * @author liuzhao
 */
@Data
@Accessors(chain=true)
@Document(collection="vehicle_swap")
public class VehicleSwap {
    @Id
    private String id;
    @Field("vehicle_no")
    private String vehicleNo; //车牌号
    @Field("vehicle_color")
    private String vehicleColor; //车辆颜色
    @Field("gnss_centerid")
    private String gnssCenterId; //接入平台码
    @Field("platform_name")
    private String platformName; //平台名称
    @Field("province_name")
    private String provinceName; //所属省
    @Field("city_name")
    private String cityName; //所属市
    @Field("enterprise_name")
    private String enterpriseName; //所属业户
    @Field("area_name")
    private String areaName; //所属区
    @Field("enterprise_id")
    private Integer enterpriseId; //业户ID
    @Field("area_id")
    private Integer areaId; //区域ID
    @Field("platform_id")
    private Integer platformId; //平台ID
    @Field("analyse_date")
    private Date analyseDate; //分析日期
    @Field("trade_kind")
    private String tradeKind; //所属行业
    @Field("track_normal")
    private Integer trackNormal; //正常轨迹数
    @Field("alarm_count")
    private Integer alarmCount; //报警总数
    @Field("alarm_processed")
    private Integer alarmProcessed; //报警处理总数
    @Field("overspeed_count")
    private Integer overSpeedCount; //超速报警总数
    @Field("overspeed_processed")
    private Integer overSpeedProcessed; // 超速报警处理总数
    @Field("roadoffset_count")
    private Integer roadOffsetCount; //路线偏离报警总数
    @Field("roadoffset_processed")
    private Integer roadOffsetProcessed; //路线偏离报警处理总数
    @Field("tired_count")
    private Integer tiredCount; //疲劳驾驶报警

    @Field("tired_processed")
    private Integer tiredProcessed; //疲劳驾驶报警处理总数

    @Field("tired_duration")
    private Integer tiredDuration; //疲劳驾驶时长,单位为 秒

    @Field("prohibit_driving_count")
    private Integer prohibitDrivingCount; //2-5点禁行报警

    @Field("prohibit_driving__processed")
    private Integer prohibitDrivingProcessed; //2-5点禁行报警处理总数

    @Field("coordinates_two")
    private Integer coordinatesTwo; //2点位置

    @Field("coordinates_five")
    private Integer coordinatesFive; // 5点位置

    @Field("mileage_2_5")
    private Integer mileage25; //2-5点行驶里程

    @Field("nightly_3rd_count")
    private Integer nightly3rdCount; //夜间三级路面报警

    @Field("nightly_3rd_processed")
    private Integer nightly3rdProcessed; //夜间三级路面报警处理总数

    @Field("over_load_count")
    private Integer overLoadCount; //超员报警

    @Field("over_load_processed")
    private Integer overLoadProcessed; //超员报警处理总数
    @Field("overspeed_100_count")
    private Integer overSpeed100Count; //超速超过100KM/H的条数
    @Field("speed_160_count")
    private Integer speed160Count; //速度超过160KM/H的条数
    @Field("overspeed_order")
    private Integer overSpeedOrder; //超速排名
    @Field("track_complete_rate")
    private Double trackCompleteRate; //轨迹完整率
    @Field("has_track")
    private Boolean hasTrack; //有无轨迹
    @Field("exception_track_count")
    private Integer exceptionTrackCount; //异常轨迹总条数
    @Field("gpstime_exception_count")
    private Integer gpsTimeExceptionCount; //时间异常条数
    @Field("location_exception_count")
    private Integer locationExceptionCount; //位置异常条数
    @Field("speed_exception_count")
    private Integer speedExceptionCount; //速度条数
    @Field("head_exception_count")
    private Integer headExceptionCount; // 方向条数
    @Field("notrack_day_count")
    private Integer noTrackDayCount; //无连续轨迹的天数（无连续轨迹天数，当有轨迹时，此值为0）
    @Field("has_net")
    private Boolean hasNet;//有无入网

}
