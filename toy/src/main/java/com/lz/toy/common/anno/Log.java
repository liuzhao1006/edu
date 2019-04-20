package com.lz.toy.common.anno;

import java.lang.annotation.*;

/**
 * 记录业务日志
 * @author liuzhao
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	 String value() ;
}
