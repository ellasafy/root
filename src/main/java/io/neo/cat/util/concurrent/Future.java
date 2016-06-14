package io.neo.cat.util.concurrent;

/**
 * Created by lunjianchang on 6/1/16.
 */
public interface Future<V> extends java.util.concurrent.Future<V> {
    Throwable cause();
}
