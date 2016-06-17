package io.neo.favor;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
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
}
