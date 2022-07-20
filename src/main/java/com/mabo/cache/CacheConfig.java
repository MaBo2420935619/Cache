package com.mabo.cache;
/**
 * @Description : 在当前修饰的方法前后执行其他的方法
 * @Author : mabo
*/
import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheConfig {
    //默认timeOut事件端内可以请求的次数为100次
    String value() ;
    //默认缓存事件为1s
    int timeOut() default 1;
    TimeUnit TimeUnit() default TimeUnit.SECONDS;
}
