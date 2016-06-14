package io.neo.cat.channel;

import io.neo.cat.util.concurrent.Future;

/**
 * Created by lunjianchang on 6/1/16.
 */
public interface ChannelFuture extends Future<Void> {
    Channel channel();
}
