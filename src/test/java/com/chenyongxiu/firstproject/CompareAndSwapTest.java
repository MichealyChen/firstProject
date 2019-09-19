package com.chenyongxiu.firstproject;

import lombok.Data;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class CompareAndSwapTest {

    private static Unsafe unsafe;
    private  Integer ss=2;

    static{
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws NoSuchFieldException {
        System.out.println(ss);
        Class<? extends Integer> aClass = ss.getClass();
        aClass.getDeclaredField("value");
        long l = unsafe.objectFieldOffset(aClass.getDeclaredField("value"));
        boolean b = unsafe.compareAndSwapObject(this, l,  2,3);
        System.out.println(b);
        System.out.println(ss);
    }

    public static void main(String[] args) throws Exception {

        Class clazz = Target.class;
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("fieldNameeeee:fieldOffset");
        for(Field f : fields){
            //获取属性偏移量，可以通过这个偏移量给属性设值
            System.out.println(f.getName()+" : "+unsafe.objectFieldOffset(f));
        }
        System.out.println("--------------------------------");
        Target target = new Target();
        Field intParam = clazz.getDeclaredField("intParam");
        int a = (Integer) intParam.get(target);
        System.out.println("intParam原始值："+a);
        long intParamOffset = unsafe.objectFieldOffset(intParam);
        System.out.println("intParam实例变量偏移量："+intParamOffset);
        //intParam实例变量偏移量是offset 原始值是3，我们要改成10
        System.out.println(unsafe.compareAndSwapInt(target, intParamOffset, 3, 10));
        System.out.println("intParam改变之后的值："+target.intParam);

        System.out.println("--------------------------------");
        //这个时候已经改为10了,所以会返回false
        System.out.println(unsafe.compareAndSwapInt(target, intParamOffset, 3, 10));

        System.out.println("--------------------------------");
        Field strParam = clazz.getDeclaredField("strParam");
        String str = (String) strParam.get(target);
        System.out.println("strParam原始值："+str);
        long strParamOffset = unsafe.objectFieldOffset(strParam);
        System.out.println("strParam实例变量的偏移量是："+strParamOffset);
        System.out.println(unsafe.compareAndSwapObject(target, strParamOffset, null, "5"));
        System.out.println("strParam改变之后的值："+target.strParam);


    }

    static class Target{


        int intParam = 3;

        long longParam;

        String strParam;
    }

}
