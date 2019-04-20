package com.lz.toy.common.anno;



import com.lz.toy.common.enums.Action;

import java.lang.annotation.*;


/**
 * 记录业务日志
 * @author liuzhao
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

	/**
	 * 执行动作
	 * {@link Action}
	 */
	Action action() default Action.Normal;
	
}