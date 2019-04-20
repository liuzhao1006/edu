package com.lz.toy.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author liuzhao
 */
@Data
@Accessors(chain = true)
@Document(collection = "alarm_info_realtime")
public class AlarmInfoRealtime {
    @Id
    private String id;

    @Field("biz_id")
    private String bizId;
    @Field("platform_id")
    private Integer platformId;
    @Field("gnsscenter_id")
    private Integer gnsscenterId;
    @Field("enterprise_id")
    private Integer enterpriseId;
    @Field("area_id")
    private Integer areaId;
    @Field("vehicle_id")
    private Integer vehicleId;
    @Field("vehicle_no")
    private String vehicleNo;
    @Field("vehicle_color")
    private Integer vehicleColor;
    private  Alarms alarms;


}

