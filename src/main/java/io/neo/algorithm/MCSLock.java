package io.neo.algorithm;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by lunjianchang on 11/21/16.
 */
public class MCSLock {

    private AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class,MCSLock.class,"queue");

    private MCSLock queue;

    public MCSLock() {
        MCSLock next;
        boolean isBlock = true;
    }

    public void lock() {

    }


}
