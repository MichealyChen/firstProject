package com.chenyongxiu.firstproject;

import com.chenyongxiu.firstproject.entity.Book;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassloaderTest {
    @Test
   public void test() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
       Class<?> aClass = Class.forName("com.chenyongxiu.firstproject.entity.LoanReviewListParam");
        Object param = aClass.newInstance();
        Field current = aClass.getDeclaredField("current");
        current.setAccessible(true);
        Object o = current.get(param);
        System.out.println(current);
        System.out.println(o);
   }

    public static void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName("com.android.peter.reflectdemo.Book");
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("Android进阶之光");
            book.setAuthor("刘望舒");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有的构造方法
    @Test
    public  void reflectPrivateConstructor() {
        try {
            Class<?> classBook = Class.forName("com.chenyongxiu.firstproject.entity.Book");
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class,String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("Android开发艺术探索","任玉刚");
            Book book = (Book) objectBook;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有属性
    @Test
    public  void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName("com.chenyongxiu.firstproject.entity.Book");
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("name");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            System.out.println(tag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    @Test
    public  void reflectPrivateMethod() {
        try {
            Class<?> classBook = Class.forName("com.chenyongxiu.firstproject.entity.Book");
            Method methodBook = classBook.getDeclaredMethod("declaredMethod",int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook,0);
            System.out.println(string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    @Test
    public  void reflectPrivateMethod2() {
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.chenyongxiu.firstproject.entity.Book");
//            Class<?> classBook = Class.forName("com.chenyongxiu.firstproject.entity.Book");
            Method methodBook = aClass.getDeclaredMethod("declaredMethod",int.class);
            methodBook.setAccessible(true);
            Object objectBook = aClass.newInstance();
            String string = (String) methodBook.invoke(objectBook,0);
            System.out.println(string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
