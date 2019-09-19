package com.chenyongxiu.firstproject;

/**
 * New thread #97827
 * New thread #97828
 * New thread #97829
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 	at java.lang.Thread.start0(Native Method)
 * 	at java.lang.Thread.start(Thread.java:717)
 */
public class ThreadTest {

    private static Object s = new Object();

    private static int count = 0;

    public static void main(String[] argv) {

        for (; ; ) {
            new Thread
                    (() -> {
                        synchronized
                                (s) {
                            count += 1;
                            System.err.println("New thread #" + count);

                        }
                        for (; ; ) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                System.err.println(e);
                            }
                        }
                    }).start();
            if(count>1000){
                break;
            }
        }
    }
}
