package io.neo.cat.util.internal;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by echo on 6/21/2016.
 */
public class SystemPropertyUtil {

    public static boolean getBoolean(String key, boolean def) {
        String value = getKey(key);
        if (value == null) {
            return def;
        }
        value = value.trim().toLowerCase();
        if (value.isEmpty()) {
            return true;
        }
        return def;
    }

    public static String getKey(String key) {
        return get(key, null);
    }

    public static String get(final String key, String def) {
        if (key == null) {
            throw new NullPointerException("key");
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("key is empty");
        }

        String value = null;
        try {
            if (System.getSecurityManager() == null) {
                value = System.getProperty(key);
            }else {
                value = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    @Override
                    public String run() {
                        return System.getProperty(key);
                    }
                });
            }
        } catch (Exception e) {

        }
        if (value == null) {
            return def;
        }
        return value;
    }
}
