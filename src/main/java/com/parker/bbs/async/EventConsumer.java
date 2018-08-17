package com.parker.bbs.async;

import com.parker.bbs.async.event.EventModel;
import com.parker.bbs.async.event.EventType;
import com.parker.bbs.async.handler.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

@Component("eventConsumer")
public class EventConsumer implements InitializingBean,ApplicationContextAware {
    private static Logger logger = LogManager.getLogger(EventConsumer.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("#{configProperties['redisEventListName']}")
    private String redisEventListName;
    private Map<EventType,List<EventHandler>> config;
    private ApplicationContext applicationContext;
    public void receieveEvent(){
        EventModel event = (EventModel) redisTemplate.opsForList().leftPop(redisEventListName, 10, TimeUnit.MINUTES);
        if (event != null){
            logger.info("comsumer接收到事件："+event.getFromUserId()+","+event.getType().toString());
            List<EventHandler> handlers = config.get(event.getType());
            logger.info("consumer找到"+handlers.size()+"个handlers："+handlers.toString());
            for (EventHandler handler:handlers){
                handler.doHandle(event);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        config = new HashMap<>(8);
        Map<String, EventHandler> handlersMap = applicationContext.getBeansOfType(EventHandler.class);
        for (Entry<String,EventHandler> entry:handlersMap.entrySet()){
            EventHandler handler = entry.getValue();
            List<EventType> supportEventTypes = handler.getSupportEventTypes();
            for (EventType eventType:supportEventTypes){
                if (!config.containsKey(eventType)){
                    config.put(eventType, new ArrayList<EventHandler>());
                }
                List<EventHandler> list = config.get(eventType);
                list.add(handler);
            }
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("consumer正在运行");
                while (true){
                    try {
                        receieveEvent();
                    }catch (Throwable e){
                        logger.error(e.getMessage());
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
