package com.lz.toy.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期转换器，解决在post请求中日期类型参数自动转Date类型
 * @author liuzhao
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    @Override
    public Date convert(String s) {
        Date date = null;
        if(StringUtils.isNotBlank(s)){
            LocalDateTime parse ;
            if(s.contains(":")){
                parse = LocalDateTime.parse(s, DateTimeFormatter.ofPattern(dateFormat));
            }else {
                parse = LocalDate.parse(s).atStartOfDay();
            }
            Instant instant = parse.atZone(ZoneId.systemDefault()).toInstant();
            date =Date.from(instant);
        }
        return date;
    }
}