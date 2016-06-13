package io.neo.cat.channel;

import io.neo.cat.util.concurrent.Promise;

/**
 * Created by lunjianchang on 6/1/16.
 */
public interface ChannelPromise extends ChannelFuture,Promise<Void> {

    ChannelPromise setFailure(Throwable cause);
}
