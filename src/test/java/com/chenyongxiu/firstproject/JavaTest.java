package com.chenyongxiu.firstproject;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.chenyongxiu.firstproject.entity.TestVO;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ClassUtils;
import org.springframework.util.SerializationUtils;
import sun.misc.Unsafe;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.core.io.support.SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JavaTest {

    @Autowired
     private ThreadPoolTaskExecutor taskExecutor;

//    @Resource
//    private ScheduledExecutorService ScheduledExecutorService;

    final int i = 0;

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

    @Test
    public void outYest(){
        Map<String, Object> param =new HashMap<>();

        param.put("qq",555);
        param.put("q1q","ww");
        param.put("qqq",null);
        param.put("q2q",null);
        Map<String, Object> collect = param.entrySet().stream()
                .filter(x -> x.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(i);


    }

    private Enumeration geturl(){
        Enumeration<URL> resources = null;
        try {
            resources = Thread.currentThread().getContextClassLoader().getResources(FACTORIES_RESOURCE_LOCATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    @Test
    public void testConstruct(){
        Properties p=System.getProperties();//获取当前的系统属性
        p.list(System.out);//将属性列表输出
        System.out.print("CPU个数:");//Runtime.getRuntime()获取当前运行时的实例
        System.out.println(Runtime.getRuntime().availableProcessors());//availableProcessors()获取当前电脑CPU数量
        System.out.print("虚拟机内存总量:");
        System.out.println(Runtime.getRuntime().totalMemory());//totalMemory()获取java虚拟机中的内存总量
        System.out.print("虚拟机空闲内存量:");
        System.out.println(Runtime.getRuntime().freeMemory());//freeMemory()获取java虚拟机中的空闲内存量
        System.out.print("虚拟机使用最大内存量:");
        System.out.println(Runtime.getRuntime().maxMemory());//maxMemory()获取java虚拟机试图使用的最大内存量
    }

    @Test
    public void testInetAddress() throws UnknownHostException, DecoderException {
        InetAddress byName = Inet4Address.getByName("devnuwa.yicartrip.com");
        String hostAddress = byName.getHostAddress();
        System.out.println(hostAddress);

        String bytes = Hex.encodeHexString(hostAddress.getBytes());
        System.out.println(bytes);

    }

    @Test
    public void testBigDecimal() throws UnknownHostException {
        String annualRateStr1 = "0.0996";
        String annualRateStr = annualRateStr1 != null ? annualRateStr1 : "1";
        BigDecimal annualRate = new BigDecimal(annualRateStr);
        BigDecimal multiply = annualRate.multiply(new BigDecimal(100));

        String s = multiply.stripTrailingZeros().toPlainString();

        String applyAmount="10000";
        BigDecimal sss = new BigDecimal(applyAmount).multiply(new BigDecimal("0.06"));
        System.out.println(sss.stripTrailingZeros().toPlainString());


    }
    private final AtomicInteger connectingCount       = new AtomicInteger();
    @Test
    public void testListBigDecimal() throws UnknownHostException {


//        IntStream.range(0,6).forEach(x->{
//             getIn();
//        });
        System.out.println(getIn());
    }

    private int getIn(){
//        int i = connectingCount.incrementAndGet();
        int i3 = connectingCount.getAndSet(6);
        int i4 = connectingCount.getAndSet(2);
        System.out.println(i3);
        System.out.println(i4);
        return 8;
    }

}
