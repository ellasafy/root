package io.neo.cat.buffer;

import io.neo.cat.util.internal.SystemPropertyUtil;

/**
 * Created by echo on 6/21/2016.
 */
public abstract class AbstractByteBuf extends ByteBuf {
    private static final String PROP_MODE = "io.netty.buffer.bytebuf.checkAccessible";

    private final static boolean checkAccessible;
    static {
               checkAccessible = SystemPropertyUtil.getBoolean(PROP_MODE,true);
    }
}
