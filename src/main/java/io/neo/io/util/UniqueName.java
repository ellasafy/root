package io.neo.io.util;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lunjianchang on 5/26/16.
 */
public class UniqueName implements Comparable<UniqueName> {

    private static final AtomicInteger nextId = new AtomicInteger();

    private String name;

    private int id;

    public UniqueName(ConcurrentMap<String,Boolean> map, String name, Object... args) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (name == null) {
            throw new NullPointerException("name");
        }
        if (args != null && args.length > 0) {
            validateArgs(args);
        }

        if (map.putIfAbsent(name, Boolean.TRUE) != null) {
             throw new IllegalArgumentException(String.format("'%s' is already exists",name ));
        }
        id = nextId.incrementAndGet();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
         return super.equals(o);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected void validateArgs(Object... args) {

    }

    public int compareTo(UniqueName o) {
          if (this == o) {
              return 0;
          }
        int t = this.name.compareTo(o.getName());

        if (t != 0) {
            return t;
        }
        return id - o.getId();
    }

    @Override
    public String toString() {
        return "UniqueName{" +
                "name='" + name + '\'' +
                '}';
    }
}
