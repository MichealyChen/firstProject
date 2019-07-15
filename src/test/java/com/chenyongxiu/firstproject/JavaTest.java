package com.chenyongxiu.firstproject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ClassUtils;
import org.springframework.util.SerializationUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JavaTest {

    @Autowired
     private ThreadPoolTaskExecutor taskExecutor;

//    @Resource
//    private ScheduledExecutorService ScheduledExecutorService;

    @Test
    public void dateFermatTest() throws InterruptedException {

        for (int i=0;i<100;i++) {
            int finalI = i;
            Thread.sleep(3L);
            taskExecutor.execute(() -> {
                System.out.println("5555  "+ finalI);
            });
        }


    }


    @Test
    public void dateFermatTest3(){
        //System Default TimeZone : Asia/Shanghai
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //2018-01-04T16:00:00Z
        //时间戳
        Instant instant = Instant.ofEpochMilli(775670400000L);

        //2018-01-05
        LocalDate now = LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        LocalDate parse = dateTimeFormatter.parse(format,LocalDate::from);
        System.out.println(parse);

    }

    @Test
    public void localDateTimeTest(){


        SimpleDateFormat format = new SimpleDateFormat("yyyy年年MM月dd日 HH:mm:ss");
        Date date = new Date();
        date.setTime(1562122381264L);
        System.out.println(format.format(date));
        date.setTime(1562122381447L);
        System.out.println(format.format(date));

    }
    private long getDiff(LocalDateTime start, LocalDateTime end) {
        int daysNum = Period.between(start.toLocalDate(), end.toLocalDate()).getDays();
        int monthNum = Period.between(start.toLocalDate(), end.toLocalDate()).getMonths();
        return monthNum;
    }

    @Test
    public void localDateTimeTest2(){

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);

        long seconds = Duration.between(start, end).getSeconds();
        System.out.println(seconds);

    }

    @Test
    public void localDateTimeTest3(){
        Instant now = Instant.now();
        LocalDateTime start = LocalDateTime.now();
        Duration between = Duration.between(Instant.now(), now);
        System.out.println(between.toNanos());
        System.out.println(between.toMillis());

    }

    @Test
    public void classLoaderTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = junit.framework.ComparisonFailure.class.getClassLoader();
        System.out.println(classLoader);
//        Class  cla = ReceiptRequestTest.class;
//        Class<?> aClass1 = cla.forName("vo.ReceiptRequestTest");
//        Class<?> aClass2 = cla.forName("vo.ReceiptRequestTest",true,classLoader);
        Class<?> aClass = ClassUtils.forName("vo.ReceiptRequestTest", classLoader);
//        Object instance = aClass.newInstance();
//        System.out.println(instance);
//        System.out.println(classLoader);
//        Class<?> aClass = classLoader.loadClass("vo.ReceiptRequestTest");
//        Field[] declaredFields = aClass.getDeclaredFields();
//        Arrays.stream(declaredFields).forEach(x->{
//            System.out.println(Arrays.asList(x.getDeclaredAnnotations()));
//        });
//        Annotation[] declaredAnnotations = aClass.getAnnotations();
//        System.out.println(aClass.newInstance());
//        System.out.println(Arrays.asList(declaredFields));
//        System.out.println(Arrays.asList(declaredAnnotations));

//        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
//
//        new TreeMap<>().
//        map.putAll();

    }



    @Test
    public void scheduledExecutorTest() throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 5; i++) {

            executorService.submit(() -> {
                outYest();
            });
            countDownLatch.countDown();
        }
//        System.out.println("countDownLatch");
        countDownLatch.await();
    }

    private void outYest(){
        System.out.println("submit");
    }

}
