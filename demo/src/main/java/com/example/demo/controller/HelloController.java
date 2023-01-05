package com.example.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.example.demo.annotation.OpLog;
import com.example.demo.entites.OpType;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/a")
    @OpLog(opType = OpType.QUERY,opItem = "hello",opItemIdExpression = "#id")
    public Integer hello(@RequestParam("id") String id){
        return 123;
    }


    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder()
                .withHost("114.132.125.96")
                .withPort(6379)
                // .withPassword(new char[]{'a', 'b', 'c', '1', '2', '3'})
                .withTimeout(Duration.ofSeconds(10))
                .build();

        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();
        String result = commands.set("hello", JSON.toJSONString("world"));
        result = commands.get("aaa");
        System.out.println(result);
        connection.close();
        client.shutdown();

    }
}
