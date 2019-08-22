package com.chenyongxiu.firstproject.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class TimerTaskUtil {
    private static final long FIRST_DELAY_TIME = 1000L;
    private static final long DELAY_FOR_A_PERIOD = 10000L;
    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
    private long flushDelayOffsetInterval = 2000;

    private final ConcurrentMap<Integer /* level */, Long/* delay timeMillis */> delayLevelTable =
            new ConcurrentHashMap<Integer, Long>(32);

    private final AtomicBoolean started = new AtomicBoolean(false);
    private Timer timer;
    private int maxDelayLevel;


    public void start() {
        parseDelayLevel();
        if (started.compareAndSet(false, true)) {
            this.timer = new Timer(Thread.currentThread().getName());
            for (Map.Entry<Integer, Long> entry : this.delayLevelTable.entrySet()) {
                Integer level = entry.getKey();
                Long timeDelay = entry.getValue();
                if (timeDelay != null) {
                    this.timer.schedule(new DeliverDelayedMessageTimerTask(), timeDelay);
                }
            }

        }
    }

    public void shutdown() {
        if (this.started.compareAndSet(true, false)) {
            if (null != this.timer)
                this.timer.cancel();
        }
    }

    public boolean isStarted() {
        return started.get();
    }


    public boolean parseDelayLevel() {
        HashMap<String, Long> timeUnitTable = new HashMap<String, Long>();
        timeUnitTable.put("s", 1000L);
        timeUnitTable.put("m", 1000L * 60);
        timeUnitTable.put("h", 1000L * 60 * 60);
        timeUnitTable.put("d", 1000L * 60 * 60 * 24);

        String levelString = messageDelayLevel;
        try {
            String[] levelArray = levelString.split(" ");
            for (int i = 0; i < levelArray.length; i++) {
                String value = levelArray[i];
                String ch = value.substring(value.length() - 1);
                Long tu = timeUnitTable.get(ch);

                int level = i + 1;
                if (level > this.maxDelayLevel) {
                    this.maxDelayLevel = level;
                }
                long num = Long.parseLong(value.substring(0, value.length() - 1));
                long delayTimeMillis = tu * num;
                this.delayLevelTable.put(level, delayTimeMillis);
            }
        } catch (Exception e) {
            log.error("parseDelayLevel exception", e);
            log.info("levelString String = {}", levelString);
            return false;
        }

        return true;
    }

    class DeliverDelayedMessageTimerTask extends TimerTask {

        @Override
        public void run() {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
            System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));

        }

    }
}
