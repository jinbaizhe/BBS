package com.parker.bbs.async;

import com.parker.bbs.async.event.EventModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("eventProducter")
public class EventProducter {
    private static Logger logger = LogManager.getLogger(EventProducter.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("#{configProperties['redisEventListName']}")
    private String redisEventListName;

    public boolean sendEvent(EventModel event){
        boolean isSuccess = true;
        try {
            //发送事件到队列
            logger.info("生产者接收到事件："+event.getFromUserId()+","+event.getType().toString());
            redisTemplate.opsForList().rightPush(redisEventListName, event);
        }catch (Exception e){
            logger.error(e.getMessage());
            isSuccess = false;
        }
        return isSuccess;
    }
}
