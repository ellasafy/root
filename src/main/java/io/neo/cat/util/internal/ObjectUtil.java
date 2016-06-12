package io.neo.cat.util.internal;

/**
 * Created by echo on 6/12/2016.
 */
public final class ObjectUtil {
    private ObjectUtil(){}

    public static <T> T checkNotNull(T arg, String text) {
           if (arg == null) {
              throw new NullPointerException(text);
           }
        return arg;
    }
}
