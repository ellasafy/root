package io.neo.structure.hash;

import java.util.List;

/**
 * Created by lunjianchang on 5/31/16.
 */
public interface MoveHandler<K, V> {
    public void moved(K from, K to, List<V> values);

    public void rebuild(K key, List<V> allVals);
}