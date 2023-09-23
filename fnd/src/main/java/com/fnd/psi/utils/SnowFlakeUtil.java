package com.fnd.psi.utils;

import cn.hutool.core.lang.Snowflake;
import lombok.experimental.UtilityClass;

/**
 * @date 2021/11/24 13:47
 * @description
 */
@UtilityClass
public class SnowFlakeUtil {
	public static long getNextId(){
		Snowflake snowflake = SpringContextHolder.getBean(Snowflake.class);
		return snowflake.nextId();
	}
}
