package com.chenyongxiu.firstproject;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListTest {

    @Test
    public void forTest(){
        List<Integer> l= new ArrayList();
        for (Integer i:l){
            System.out.println(i);
        }
    }

    @Test
    public void forTest2(){
        Integer i=6;
        IntStream.range(1,i).forEach(x->{
            System.out.println(x);
        });
    }

    @Test
    public void bigdiesimal(){
        BigDecimal bigDecimal = new BigDecimal("22.0");

        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(2.55)).setScale(2,RoundingMode.FLOOR);

        System.out.println(multiply);
    }

    /**
     * 售后回租B 应收款日期
     * @param term
     * @return
     */
    private String getPlanBReceiptDate(int term) {
        LocalDate now = LocalDate.now().plusDays(2);
        if (now.getDayOfMonth()>28) {
            LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 28);
            return date.plusMonths(term).toString();
        }
        return now.plusMonths(term).toString();
    }

    @Test
    public void localDateTest() {

        Integer totalTerm = 5;
        IntStream.range(1, totalTerm).forEach(x -> {
            String planBReceiptDate = getPlanBReceiptDate(x);
            System.out.println(planBReceiptDate);
        });
    }
}
