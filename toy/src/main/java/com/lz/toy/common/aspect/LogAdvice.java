package com.lz.toy.common.aspect;

import com.alibaba.fastjson.JSON;
import com.lz.toy.common.anno.Log;
import com.lz.toy.common.bean.Token;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.entity.SysLog;
import com.lz.toy.serivce.ISysLogService;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 正常业务日志记录
 * @author liuzhao
 *
 */
@Log4j
@Aspect
@Component
public class LogAdvice {

	private ISysLogService sysLogService;
	@Autowired
	public LogAdvice(ISysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	@Pointcut("@annotation(com.lz.toy.common.anno.Log)")
	public void controllerAspect() {
		
	}
	/**
	 * 当方法正常返回是执行
	 * @param joinPoint 不知道
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Log logger =  method.getAnnotation(Log.class);
		Token st = TokenUtil.getToken(request);
		if(logger != null){
			SysLog sysLog  =new SysLog();
			sysLog.setCreateTime(new Date());
			sysLog.setTitle(logger.value());
			sysLog.setUserName((st != null )? st.getUname() : "system");
			sysLog.setUrl(request.getRequestURI());
			sysLog.setParams(JSON.toJSONString(request.getParameterMap()));
			sysLogService.insert(sysLog);
			log.debug("记录日志:"+sysLog.toString());
		}
	}
}
