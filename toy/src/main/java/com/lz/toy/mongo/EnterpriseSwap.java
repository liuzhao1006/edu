package com.lz.toy.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 业户维度中间表
 * @author liuzhao
 */
@Data
@Accessors(chain = true)
@Document(collection = "enterprise_swap")
public class EnterpriseSwap implements Serializable {
    @Id
    private String id;
    @Field("enterprise_id")
    private Integer enterpriseId;
    @Field("enterprise_name")
    private String enterpriseName; //业户名称
    @Field("analyse_date")
    private Date analyseDate; //分析日期
    private Integer score;//得分
    @Field("check_count")
    private Integer checkCount; //查岗次数
    @Field("reply_count")
    private Integer replyCount; //回复次数
    @Field("reply_correct_count")
    private Integer replyCorrectCount; //回复正确次数
    @Field("reply_rate")
    private Double replyRate; //回复率（即在岗率）
    @Field("vehicle_count")
    private Integer vehicleCount; //车辆总数
    @Field("vehicle_access_count")
    private Integer vehicleAccessCount; //入网车辆数
    @Field("vehicle_online_count")
    private Integer vehicleOnlineCount; //上线数
    @Field("alarm_vehicle_count")
    private Integer alarmVehicleCount; //报警车辆总数

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
    @Field("track_complete_rate")
    private Double trackCompleteRate; //轨迹完整率(1分钟内至少有一条轨迹）
    @Field("exception_vehicle_count")
    private Integer exceptionVehicleCount; //异常车辆总数
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
    @Field("data_fault_rate")
    private Double dataFaultRate; //数据错误率
    @Field("data_correct_rate")
    private Double dataCorrectRate;//数据合格率(即1-数据错误率 乘以100)
    @Field("notrack_vehicle_count")
    private  Integer noTrackVehicleCount;//无轨迹车辆数（超过10天不上传轨迹的车辆数）

}
