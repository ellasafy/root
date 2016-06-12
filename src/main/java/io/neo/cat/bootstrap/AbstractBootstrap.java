package io.neo.cat.bootstrap;

import io.neo.cat.channel.*;
import io.neo.cat.util.AttributeKey;
import io.neo.cat.util.internal.StringUtil;

import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by echo on 6/12/2016.
 */
public abstract class  AbstractBootstrap<B extends AbstractBootstrap<B,C>, C extends Channel> implements Cloneable {
    volatile EventLoopGroup group;
    private volatile ChannelFactory<? extends C> channelFactory;
    private volatile SocketAddress loacalAddress;
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap<>();
    private final Map<AttributeKey<?>,Object> attrs = new LinkedHashMap<>();
    private volatile ChannelHandler handler;

    AbstractBootstrap() {

    }
    AbstractBootstrap(AbstractBootstrap<B,C> bootstrap){
      group = bootstrap.group;
        channelFactory = bootstrap.channelFactory;
        loacalAddress = bootstrap.loacalAddress;
        synchronized (bootstrap.options) {
            this.options.putAll(bootstrap.options);
        }
        synchronized (bootstrap.attrs) {
            this.attrs.putAll(bootstrap.attrs);
        }
        handler = bootstrap.handler;

    }


    private static final class BootstrapChannelFactory<T extends Channel> implements ChannelFactory<T> {
         private final Class<? extends T> clazz;
         BootstrapChannelFactory(Class<? extends T> clazz) {
             this.clazz = clazz;
         }

        @Override
        public T newChannel() {
            try {
                return this.clazz.newInstance();
            } catch (Throwable t) {
                 throw new ChannelException("Unable create class from " + this.clazz, t);
            }
        }

        @Override
        public String toString() {
            return StringUtil.SimpleClassName(clazz)  + ".class";
        }
    }
}
