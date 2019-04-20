package com.lz.toy.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lz.toy.entity.SysUserRole;
import com.lz.toy.serivce.ISysUserRoleService;
import com.lz.toy.serivce.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SchedulingJobsService {

    private final ISysUserRoleService sysUserRoleService; //用户角色表

    private final ISysUserService sysUserService; //从业人员

    public SchedulingJobsService(ISysUserRoleService sysUserRoleService,
                                 ISysUserService sysUserService
                                 ) {

        this.sysUserRoleService = sysUserRoleService;

        this.sysUserService = sysUserService;

    }

    public void sendAlarm() {
        List<SysUserRole> sysUserRoles = sysUserRoleService.selectList(new EntityWrapper<>());
    }


    /**
     * 判断时间是否发送
     *
     * @param date                     当前时间
     * @param timeDifference           发送时间差
     * @param senderTimeDifferenceType 间隔发送时间类型
     */
    private boolean isReference(Date date, Integer timeDifference, Integer senderTimeDifferenceType) {
        boolean result;
        LocalDateTime now = LocalDateTime.now(); //当前时间
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        switch (senderTimeDifferenceType) { //发送时间差类型
            case 0: //年
                result = now.isAfter(localDateTime.plusYears(timeDifference)); //符合发送时间
                break;
            case 1: //月
                result = now.isAfter(localDateTime.plusMonths(timeDifference));
                break;
            case 2: //日
                result = now.isAfter(localDateTime.plusDays(timeDifference));
                break;
            case 3: //小时
                result = now.isAfter(localDateTime.plusHours(timeDifference));
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 按天判断时间是否发送
     *
     * @param date           当前时间
     * @param timeDifference 发送时间差
     */
    private boolean isReference(Date date, Integer timeDifference) {
        boolean result;
        LocalDate now = LocalDate.now(); //当前时间
        LocalDate localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        result = now.isAfter(localDateTime.plusDays(timeDifference))
                || now.isEqual(localDateTime.plusDays(timeDifference));
        return result;
    }

}

