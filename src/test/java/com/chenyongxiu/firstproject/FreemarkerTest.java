package com.chenyongxiu.firstproject;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.springframework.core.io.support.SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FreemarkerTest {

    @Test
    public void dateFermatTest() throws InterruptedException, IOException, TemplateException {

        Reader reader = null;
        try {
            reader = new FileReader(new File("D:/ht.ftl"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Template template = new Template("test", reader, null, "utf-8");

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("userName", "hello world");
        data.put("list", Arrays.asList("entity1", "entity2"));
        data.put("PrintTime", new Date());
        Writer writer = new PrintWriter(System.out);

        template.process(data, writer);

        writer.flush();
        writer.close();
        reader.close();
    }


}
