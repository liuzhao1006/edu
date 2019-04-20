package com.lz.toy.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Accessors(chain = true)
class Alarms {
    @Field("1")
    private AlarmDetails overSpeeds; //超速
    @Field("2")
    private AlarmDetails triedAlarm; //疲劳
    @Field("10")
    private AlarmDetails overPeron; //超员
    @Field("11")
    private AlarmDetails deviate; //偏离路线
    @Field("14")
    private AlarmDetails stop; //2-5禁行
    @Field("15")
    private AlarmDetails night; //夜间三级路面
}
