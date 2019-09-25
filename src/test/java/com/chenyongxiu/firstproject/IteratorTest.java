package com.chenyongxiu.firstproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {

        List<String> lst = new ArrayList<String>();
        lst.add("aaa");
        lst.add("bbb");
        lst.add("ccc");
        lst.add("ddd");
        lst.add("eee");
        lst.add("fff");
        Iterator<String> iterator = lst.iterator();
        //iterator.hasNext()如果存在元素的话返回true

        iterator.forEachRemaining(System.out::println);

    }

}
