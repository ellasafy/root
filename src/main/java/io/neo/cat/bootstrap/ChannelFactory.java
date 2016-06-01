package io.neo.cat.bootstrap;

import io.neo.cat.channel.Channel;

/**
 * Created by lunjianchang on 6/1/16.
 */
public interface ChannelFactory<T extends Channel> {
    T newChannel();
}
