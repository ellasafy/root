package io.neo.favor;

import org.junit.Test;

import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created by lunjianchang on 8/2/16.
 */
public class ByteT {
    @Test
    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.wrap("abc".getBytes());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.toString());
        System.out.println(byteBuffer.array());
        byte[] bytes = "我".getBytes();
        String st = "我";
        System.out.println(st.getBytes().length);
        char tt = '我';
        System.out.println("char " + Integer.toBinaryString((int)tt));
        System.out.println("len " + bytes.length);
        for (int i=0;i<bytes.length;i++) {
            System.out.println(Integer.toBinaryString((int)bytes[i]));
        }
        System.out.println(byteBuffer.position());

        File file = new File("file", ("log." +
                Long.toHexString(23434L)));
        System.out.println(file.getAbsolutePath());
    }
}
