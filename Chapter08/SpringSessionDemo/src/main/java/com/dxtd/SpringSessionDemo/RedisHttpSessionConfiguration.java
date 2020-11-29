package com.dxtd.SpringSessionDemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds 默认是 1800 秒过期，这里测试修改为 120 秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=120)
public class RedisHttpSessionConfiguration {

}
