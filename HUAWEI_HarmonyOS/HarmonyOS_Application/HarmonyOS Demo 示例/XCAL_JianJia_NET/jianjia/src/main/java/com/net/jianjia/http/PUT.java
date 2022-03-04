package com.net.jianjia.http;

import java.lang.annotation.*;

/**
 * put请求，声明在方法上的注解，在运行时有效
 *
 * @author 裴云飞
 * @date 2021 /1/18
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PUT {

    /**
     * PUT注解一般必须添加相对路径或绝对路径或者全路径，如果不想在PUT注解后添加请求路径，
     * 则可以在方法的第一个参数中用{@link com.net.jianjia.http.Url @Url}注解添加请求路径
     *
     * @return string
     */
    String value() default "";
}
