package com.chenyongxiu.firstproject;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

public class DateTest {
    @Test
   public void test1(){
        BigDecimal bigDecimal = new BigDecimal("2.22");
        BigDecimal pow = bigDecimal.pow(4,new MathContext(5));
        System.out.println(pow);
   }
}
