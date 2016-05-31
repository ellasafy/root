package io.neo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by lunjianchang on 5/26/16.
 */
public class Main {

    public static void main(String[] args) {

//        SThread t = new SThread();
//        t.start();
//
//        try {
//            t.interrupt();
//        } catch (Exception e) {
//
//        }
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            Field id = Main.class.getDeclaredField("id");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            System.out.println(unsafe.objectFieldOffset(id));
            Field tt = Main.class.getDeclaredField("tt");
            System.out.println(unsafe.objectFieldOffset(tt));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Integer id = 3;
    private String tt = "ccc";


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
