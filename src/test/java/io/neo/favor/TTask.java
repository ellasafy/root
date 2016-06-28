package io.neo.favor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by lunjianchang on 6/28/16.
 */
public class TTask extends RecursiveTask<Integer> {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<20;i++) {
            list.add(i);
        }
        ForkJoinPool pool = new ForkJoinPool(9);
        TTask t = new TTask(list);
        pool.invoke(t);
    }

    private List<Integer> list;
    public TTask(List<Integer> list) {
        this.list = list;
    }
    @Override
    protected Integer compute() {
        if (list.size() > 3) {
            List<Integer> t1 = list.subList(0, list.size()/2);
            List<Integer> t2 = list.subList((list.size()/2)+1, list.size());
            TTask t11 = new TTask(t1);
            TTask t22 = new TTask(t2);
            t11.fork();
            t22.fork();
            return t11.join() + t22.join();
        }else {
            System.out.println(Thread.currentThread().getName());
            int sum = 0;
            for (Integer i:list) {
                 sum = i + sum;
            }
            return sum;
        }
    }

}
