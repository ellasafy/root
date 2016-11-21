package io.neo.algorithm;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lunjianchang on 11/21/16.
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        while(!owner.compareAndSet(null, currentThread)) {

        }

    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();

        owner.compareAndSet(currentThread,null);
    }
}
