package io.neo.cat.util;

/**
 * Created by echo on 6/22/2016.
 */
public class ResourceLeakDetector<T> {
    private static final String PROP_LEVEL_OLD = "io.netty.leakDetectionLevel";
    private static final String PROP_LEVEL = "io.netty.leakDetection.level";


    public enum Level{
        DISABLED,
        SIMPLE,
        ADVANCED,
        PARANOID
    }

    private static Level level;
}
