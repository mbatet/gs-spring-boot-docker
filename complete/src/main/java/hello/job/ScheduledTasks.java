package hello.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //If we want to support parallel behavior in scheduled tasks, we need to add the @Async annotation:
    //@Async
    @Scheduled(fixedRate = 10000, initialDelay = 1000)
    public void scheduleTaskUsingFixedRate() {
        log.info("[m:scheduleTaskUsingFixedRate] The time is now {}", dateFormat.format(new Date()));
    }


    //cada minut al segon 00
    @Scheduled(cron = "00 * * * * ?")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        log.info("[m:scheduleTaskUsingCronExpression] ==============> schedule tasks using cron jobs - " + now);
    }
}