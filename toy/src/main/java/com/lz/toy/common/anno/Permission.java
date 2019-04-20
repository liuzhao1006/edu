package com.lz.toy.common.anno;

import java.lang.annotation.*;

/**
 * 权限拦截注解
 * @author 刘朝
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
	
	 String value() ;
	
}
