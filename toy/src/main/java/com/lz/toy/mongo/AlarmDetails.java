package com.lz.toy.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author liuzhao
 */
@Data
@Accessors(chain = true)
class AlarmDetails {
    @Field("alarm_type")
    private Integer alarmType;
    @Field("alarm_time")
    private Date alarmTime;
    @Field("alarm_src")
    private Integer alarmSrc;
    @Field("info_id")
    private Integer infoId;
    @Field("rcv_time")
    private Date rcvTime;
    @Field("info_content")
    private String infoContent;
}
