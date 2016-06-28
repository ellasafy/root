package io.neo.favor;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by lunjianchang on 6/17/16.
 */
public class MemoryAddress {

    public void getObjectAddress() {
        try {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe =(Unsafe)field.get(null);
        ByteBuffer b = ByteBuffer.allocateDirect(3);
        b.put((byte)3333333);

        Field  address = Buffer.class
                .getDeclaredField("address");
        address.setAccessible(true);
        long t = unsafe.objectFieldOffset(address);
        System.out.println(t);
        System.out.println(unsafe.getLong(b,t));

    } catch (Exception e) {
        e.printStackTrace();
    }


    }

    public void regular() {
        System.out.println("restaurant/_wx4gdu7dz_sms".matches("^/restaurant/_[a-zA-Z0-9_]+$"));
    }

    public void maxDirectMemorySize() {
        try {
            // Try to get from sun.misc.VM.maxDirectMemory() which should be most accurate.
            Class<?> vmClass = Class.forName("sun.misc.VM", true, ClassLoader.getSystemClassLoader());
            Method m = vmClass.getDeclaredMethod("maxDirectMemory");
            long  maxDirectMemory = ((Number) m.invoke(null)).longValue();
            System.out.println(maxDirectMemory);
            System.out.println(maxDirectMemory/1024/1024);

            ///two
            maxDirectMemory = Runtime.getRuntime().maxMemory();
        } catch (Throwable t) {
            // Ignore
            t.printStackTrace();
        }
    }
}
