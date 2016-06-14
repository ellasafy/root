package io.neo.cat.bootstrap;

import io.neo.cat.channel.*;
import io.neo.cat.util.AttributeKey;
import io.neo.cat.util.concurrent.GlobalEventExecutor;
import io.neo.cat.util.internal.StringUtil;

import java.net.InetSocketAddress;
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

    public B group(EventLoopGroup group) {
        if (group == null) {
            throw new NullPointerException("group");
        }
        if (this.group != null) {
            throw new IllegalArgumentException("group set already ");
        }
        this.group = group;
        return (B) this;
    }

    public B channel(Class<? extends C> clazz) {
        if (clazz == null) {
            throw new NullPointerException("channel");
        }
        return channelFactory(new BootstrapChannelFactory<C>(clazz));
    }
    public B channelFactory(ChannelFactory<? extends C> channelFactory) {
        if (channelFactory == null) {
            throw new NullPointerException("channelFactory");
        }
        if (this.channelFactory != null) {
            throw new IllegalArgumentException("channelFactory set already");
        }
        this.channelFactory = channelFactory;
        return (B)this;
    }

    public B localAddress(SocketAddress localAddress) {
        if (localAddress == null) {
            throw new NullPointerException("localAddress");
        }
        this.loacalAddress = localAddress;
        return (B) this;
    }

    public B localAddress(int inetPort) {
        return localAddress(new InetSocketAddress(inetPort));
    }
    public B localAddress(String inetHost, int inetPort) {
        return localAddress(new InetSocketAddress(inetHost, inetPort));
    }

    public <T> B option(ChannelOption<T> option, T value) {
        if (option == null) {
            throw new NullPointerException("option");
        }

        if (value == null) {
            synchronized (options) {
                options.remove(option);
            }
        } else {
            synchronized (options) {
                options.put(option, value);
            }
        }
        return (B)this;
    }

    public <T> B attr(AttributeKey<T> key, T value) {
        if (key == null) {
            throw new NullPointerException("attr");
        }
        if (value == null) {
            synchronized (attrs) {
                attrs.remove(key);
            }
        }else {
            synchronized (attrs) {
                attrs.put(key, value);
            }
        }
        return (B) this;
    }

    public B validate() {
        if (group == null) {
            throw new NullPointerException("group not set");
        }
        if (channelFactory == null) {
            throw new NullPointerException("channelFactory not set");
        }
        return (B)this;
    }

    final ChannelFactory<? extends C> channelFactory() {
        return channelFactory;
    }

    public abstract B clone();

    final ChannelFuture initAndRegister() {
        final  Channel channel = channelFactory().newChannel();
        try {
              init(channel);
        } catch (Throwable t) {
            channel.unsafe().closeForcibly();
            return new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE).setFailure(t);
        }

        return null;
    }

    abstract void init(Channel channel) throws Exception;

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
