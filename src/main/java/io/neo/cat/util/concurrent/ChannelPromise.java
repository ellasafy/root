package io.neo.cat.util.concurrent;

import io.neo.cat.channel.ChannelFuture;

/**
 * Created by echo on 6/13/2016.
 */
public interface ChannelPromise extends ChannelFuture, Promise<Void> {
}
