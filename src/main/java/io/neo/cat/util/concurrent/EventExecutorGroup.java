package io.neo.cat.util.concurrent;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lunjianchang on 6/1/16.
 */
public interface EventExecutorGroup extends ScheduledExecutorService,Iterable<EventExecutor>{
}
