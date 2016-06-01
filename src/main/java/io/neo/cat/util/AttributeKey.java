package io.neo.cat.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lunjianchang on 5/26/16.
 */
public final class AttributeKey<T> extends UniqueName {

    private static final ConcurrentHashMap<String,Boolean> names = new ConcurrentHashMap<String, Boolean>();


    public static <T> AttributeKey<T> valueof(String name) {
        return new AttributeKey<T>(name);
    }
    public AttributeKey(String name) {
        super(names,name);
    }
}
