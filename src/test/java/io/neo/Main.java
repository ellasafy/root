package io.neo;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by lunjianchang on 5/26/16.
 */
public class Main {

    public static void main(String[] args) {

        SThread t = new SThread();
        t.start();

        try {
            t.interrupt();
        } catch (Exception e) {

        }

    }


    static class SThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
                System.out.println(e.getClass().getCanonicalName());
                e.printStackTrace();
            }
        }
    }
}
