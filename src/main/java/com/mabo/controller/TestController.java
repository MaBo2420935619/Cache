package com.mabo.controller;


import com.mabo.utils.RedisGeneratorCode;
import com.mabo.cache.CacheConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    // http://localhost:8081/test?id=1
    @RequestMapping("test")
    @CacheConfig(value = "mabo.test.cache",timeOut = 5)
    public  String test(@RequestParam("id") String id){
        return "test";
    }
    //  http://localhost:8081/test2
    @Autowired
    private RedisGeneratorCode redisGeneratorCode;
    // http://localhost:8081/test?id=1
    @RequestMapping("test2")
    public  String test2(){
        String worksheet_id = redisGeneratorCode.generateCode("worksheet_id", "", true, 4);
        return worksheet_id;
    }

}
