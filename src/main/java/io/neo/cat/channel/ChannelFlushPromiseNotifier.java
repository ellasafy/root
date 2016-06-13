package io.neo.cat.channel;

import io.neo.cat.util.concurrent.*;

/**
 * Created by echo on 6/13/2016.
 */
public final class ChannelFlushPromiseNotifier {
    interface FlushCheckpoint{
        io.neo.cat.util.concurrent.ChannelPromise promise();
    }
}
