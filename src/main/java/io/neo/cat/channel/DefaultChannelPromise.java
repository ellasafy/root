package io.neo.cat.channel;

import io.neo.cat.util.concurrent.*;

/**
 * Created by echo on 6/13/2016.
 */
public class DefaultChannelPromise extends DefaultPromise<Void> implements ChannelPromise, ChannelFlushPromiseNotifier.FlushCheckpoint {
    private final Channel channel;

    public DefaultChannelPromise(Channel channel, EventExecutor executor) {
        super(executor);
        this.channel = channel;
    }

    @Override
    public io.neo.cat.util.concurrent.ChannelPromise promise() {
        return null;
    }

    @Override
    public ChannelPromise setFailure(Throwable cause) {
        return null;
    }
}
