package io.neo.cat.util.internal;

/**
 * Created by echo on 6/12/2016.
 */
public class StringUtil {
    private static final char PACKAGE_SEPARATOR_CHAR = '.';

    public static String SimpleClassName(Class<?> clazz) {
         String className = ObjectUtil.checkNotNull(clazz, "clazz").getName();
         final int lastDotInx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
         if (lastDotInx > -1) {
             return className.substring(lastDotInx + 1);
         }
        return className;
    }

}
