package io.neo.algorithm;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lunjianchang on 11/21/16.
 */
public class TicketLock {

    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();

    private int lock() {
        int num = ticketNum.incrementAndGet();
        while(serviceNum.get() != num) {

        }
        return serviceNum.get();
    }

    private void unlock() {
        int mytick = serviceNum.get();
        int num = mytick + 1;

        serviceNum.compareAndSet(mytick, num);
    }

    @Test
    public void test() {
        TicketLock lock = new TicketLock();
        System.out.println(lock.serviceNum.get());
    }
}
