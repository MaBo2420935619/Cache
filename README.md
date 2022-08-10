# Cache

一个注解实现方法返回数据写入缓存（切面、Redis实现）





# 一、用法
## 如何使用
	

```java
@CacheConfig(value = "mabo.test",timeOut = 1, TimeUnit = TimeUnit.SECONDS)
    public  String test(String id){
            return id;
    }
```

## 参数说明
底层采用redis来进行存储,用有超时时间的set方法来存储
value  key值
timeOut		超时时间
TimeUnit	超时时间的单位

# 二、原理
采用切面的方式，对方法进行动态代理，一共分为两种情况
## 1、返回方法原本返回值
先去缓存中读取该方法的返回值，如果为空，则执行方法
## 2、返回缓存数据
不为空，直接返回缓存数据
## 源码

```java
@Around("@annotation(cacheConfig)")
    public Object addEventListener(ProceedingJoinPoint joinPoint, CacheConfig cacheConfig) throws Throwable {
        String key = cacheConfig.value();
        //返回值
        Object proceed =null;
        Object o = redisTemplate.opsForValue().get(key);
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        if (o!=null){
            log.info(declaringTypeName+"方法获取缓存成功"+o);
            return o;
        }
        else {
            Object[] args = joinPoint.getArgs();
            try {
                proceed = joinPoint.proceed(args);
                redisTemplate.opsForValue().set(key,proceed,cacheConfig.timeOut(),cacheConfig.TimeUnit());
            } catch (Throwable throwable) {
                log.error(declaringTypeName+"方法执行异常"+throwable);
            }
            return proceed;
        }
    }
```
## demo下载地址
github地址
[github地址](http://47.103.194.1:8081/download/demo?fileName=Cache.zip)
https://github.com/MaBo2420935619/Cache
国内下载地址
[点击跳转国内下载地址](http://47.103.194.1:8081/download/demo?fileName=Cache.zip)
http://47.103.194.1:8081/download/demo?fileName=Cache.zip
