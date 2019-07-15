package com.chenyongxiu.firstproject;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.function.Supplier;


public class ProxyDemo {

    public void main(String[] args) throws IllegalArgumentException {
        InvocationHandler handler = new SampleInvocationHandler();
        SampleInterface proxy = (SampleInterface) Proxy.newProxyInstance(
                SampleInterface.class.getClassLoader(),
                new Class[]{SampleInterface.class}, handler);
        Class invocationHandler = Proxy.getInvocationHandler(proxy).getClass();
        System.out.println( invocationHandler.toString());

        System.out.println(invocationHandler.getName());
    }


    class SampleInvocationHandler implements InvocationHandler {

        @Override
        public String toString() {
            return "SampleInvocationHandler{}";
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("Welcome To Yiibai.com");
            return null;
        }
    }

    interface SampleInterface {
        void showMessage();
    }

    class SampleClass implements SampleInterface {

        public SampleClass() {
            System.out.println("new SampleClass");
        }

        public void showMessage() {
            System.out.println("Hello World");
        }

    }
    @Test
    public void tt() {

      HashMap<Object, Object> map = new HashMap<>();
        Supplier<SampleClass> aNew = SampleClass::new;
        for (int i = 0; i < 5; i++)
            System.out.println(aNew.get());
    }
}
