package com.mabo.cache;

import com.mabo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @Description : 定义自定义注解的切面
 * @Author : mabo
 */
@Slf4j
@Component
@Aspect
public class CacheAspect {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * @Description : 使用Around可以修改方法的参数，返回值，
     * 甚至不执行原来的方法,但是原来的方法不执行会导致before和after注解的内容不执行
     * 通过around给原方法赋给参数
     */
    @Around("@annotation(cacheConfig)")
    public Object addEventListener(ProceedingJoinPoint joinPoint, CacheConfig cacheConfig) throws Throwable {
        log.info("进入cacheConfig");
        Object[] args = joinPoint.getArgs();
        StringBuffer buffer=new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            buffer=buffer.append(args[i]);
        }
        String encrypt = StringUtils.encrypt(buffer.toString());
//            String decrypt = StringUtils.decrypt(encrypt);
        String key = cacheConfig.value()+encrypt;
        log.info("开始读取缓存,key:"+key);
        //返回值
        Object proceed =null;
        Object o = redisTemplate.opsForValue().get(key);
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        if (o!=null){
            log.info(declaringTypeName+"方法获取缓存成功,key"+key);
            return o;
        }
        else {
            try {
                proceed = joinPoint.proceed(args);
                log.info(declaringTypeName+"缓存读取失败，执行原方法,key"+key);
                redisTemplate.opsForValue().set(key,proceed,cacheConfig.timeOut(),cacheConfig.TimeUnit());
            } catch (Throwable throwable) {
                log.error(declaringTypeName+"方法执行异常"+throwable);
            }
            return proceed;
        }
    }
}
