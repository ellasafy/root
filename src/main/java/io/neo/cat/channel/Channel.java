package io.neo.cat.channel;


/**
 * Created by lunjianchang on 5/26/16.
 */
public interface Channel extends AttributeMap,Comparable<Channel> {

    Unsafe unsafe();

    boolean isRegistered();

    ChannelFuture close();

    interface Unsafe {
        void closeForcibly();
    }

}
