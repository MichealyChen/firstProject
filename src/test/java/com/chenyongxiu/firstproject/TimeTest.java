package com.chenyongxiu.firstproject;

import com.chenyongxiu.firstproject.common.utils.TimerTaskUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeTest {

    public static void main(String[] args) {

        AtomicInteger n= new AtomicInteger();

//        Timer ti=new Timer();
//        ti.scheduleAtFixedRate(new MyTask(),1000,3000);
//        AtomicInteger n= new AtomicInteger();
        new TimerTaskUtil(new Timer()).start();
//        ttt( n);
    }

    private static void ttt( AtomicInteger n) {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
            System.out.println(Thread.currentThread().getName() + " " + dateTimeFormatter.format(LocalDateTime.now()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int andIncrement = n.getAndIncrement();

            if (andIncrement >= 6) {
                executorService.shutdownNow();
            }

        }, 2, 2, TimeUnit.SECONDS);
    }
}


class MyTask extends TimerTask{

    int n=0;

    @Override
    public void run() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss");
        System.out.println(Thread.currentThread().getName()+" "+dateTimeFormatter.format(LocalDateTime.now()));
        n++;
        if(n>=5){
            cancel();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}