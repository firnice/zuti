package com.firnice.zuti.service.task;


import com.firnice.zuti.service.TestCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateCacheTask {

    @Autowired
    private TestCache testCache;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledSelection() {
        log.info("=====>>>>>定时任务  {}", System.currentTimeMillis());
        try {
            testCache.cacheFlush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
