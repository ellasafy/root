package io.neo.cat.channel;

import io.neo.cat.util.UniqueName;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lunjianchang on 6/1/16.
 */
public class ChannelOption<T> extends UniqueName {
    private static final ConcurrentHashMap<String,Boolean> names = new ConcurrentHashMap<>();
    @Deprecated
    protected ChannelOption(String name) {
        super(names, name);
    }
}
