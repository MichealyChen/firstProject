package com.chenyongxiu.firstproject.common.utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoArgsConstructor
public class TimerTaskUtil {
    private static final long FIRST_DELAY_TIME = 1000L;
    private static final long DELAY_FOR_A_PERIOD = 10000L;
    //    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
    private String messageDelayLevel = "5s 10s 20s 30s 1m 2m 3m 4m 5m";

    private Timer timer;

    private AtomicInteger n = new AtomicInteger();

    public TimerTaskUtil(Timer timer){
        this.timer = timer;
    }
    public void start() {
            Map<String, Long> timeUnitTable = new HashMap<String, Long>();
            timeUnitTable.put("s", 1000L);
            timeUnitTable.put("m", 1000L * 60);
            timeUnitTable.put("h", 1000L * 60 * 60);
            timeUnitTable.put("d", 1000L * 60 * 60 * 24);
            String[] delayLevel = messageDelayLevel.split(" ");
            Arrays.stream(delayLevel).forEach(x -> {
                String ch = x.substring(x.length() - 1);
                Long tu = timeUnitTable.get(ch);
                long num = Long.parseLong(x.substring(0, x.length() - 1));
                long delayTimeMillis = tu * num;
                timer.schedule(new DeliverDelayedMessageTimerTask(), delayTimeMillis);
            });
    }

    public void shutdown() {
            if (null != timer)
                timer.cancel();
    }

    class DeliverDelayedMessageTimerTask extends TimerTask {

        @Override
        public void run() {
            int i = n.incrementAndGet();
            if (i > 4) {
                shutdown();
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
            System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));
        }
    }
}
