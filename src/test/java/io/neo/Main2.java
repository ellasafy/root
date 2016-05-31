package io.neo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by lunjianchang on 5/30/16.
 */
public class Main2 {


    @org.junit.Test
    public void test() {
        TreeMap<Integer, String> t = new TreeMap<>();
        t.put(2,"ccc");
        t.put(5,"5");
        t.put(29,"29");
        t.put(18,"18");
        t.put(19,"19");
        System.out.println(t);
    }
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * key表示服务器的hash值，value表示服务器的名称
     */
    private static SortedMap<Integer, String> sortedMap =
            new TreeMap<Integer, String>();

    /**
     * 程序初始化，将所有的服务器放入sortedMap中
     */
    static
    {
        for (int i = 0; i < servers.length; i++)
        {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println(sortedMap);
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static int getHash(String str)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node)
    {
        // 得到带路由的结点的Hash值
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        System.out.println(sortedMap);
        SortedMap<Integer, String> subMap =
                sortedMap.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的服务器名称
        return subMap.get(i);
    }

    public static void main(String[] args)
    {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++)
            System.out.println("[" + nodes[i] + "]的hash值为" +
                    getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }
    public class Shard<S> { // S类封装了机器节点的信息 ，如name、password、ip、port等

        private TreeMap<Long, S> nodes; // 虚拟节点
        private List<S> shards; // 真实机器节点
        private final int NODE_NUM = 100; // 每个机器节点关联的虚拟节点个数

        public Shard(List<S> shards) {
            super();
            this.shards = shards;
            init();
        }

        private void init() { // 初始化一致性hash环
            nodes = new TreeMap<Long, S>();
            for (int i = 0; i != shards.size(); ++i) { // 每个真实机器节点都需要关联虚拟节点
                final S shardInfo = shards.get(i);

                for (int n = 0; n < NODE_NUM; n++)
                    // 一个真实机器节点关联NODE_NUM个虚拟节点
                    nodes.put(hash("SHARD-" + i + "-NODE-" + n), shardInfo);

            }
        }

        public S getShardInfo(String key) {
            SortedMap<Long, S> tail = nodes.tailMap(hash(key)); // 沿环的顺时针找到一个虚拟节点
            if (tail.size() == 0) {
                return nodes.get(nodes.firstKey());
            }
            return tail.get(tail.firstKey()); // 返回该虚拟节点对应的真实机器节点的信息
        }

        /**
         *  MurMurHash算法，是非加密HASH算法，性能很高，
         *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
         *  等HASH算法要快很多，而且据说这个算法的碰撞率很低.
         *  http://murmurhash.googlepages.com/
         */
        private Long hash(String key) {

            ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
            int seed = 0x1234ABCD;

            ByteOrder byteOrder = buf.order();
            buf.order(ByteOrder.LITTLE_ENDIAN);

            long m = 0xc6a4a7935bd1e995L;
            int r = 47;

            long h = seed ^ (buf.remaining() * m);

            long k;
            while (buf.remaining() >= 8) {
                k = buf.getLong();

                k *= m;
                k ^= k >>> r;
                k *= m;

                h ^= k;
                h *= m;
            }

            if (buf.remaining() > 0) {
                ByteBuffer finish = ByteBuffer.allocate(8).order(
                        ByteOrder.LITTLE_ENDIAN);
                // for big-endian version, do this first:
                // finish.position(8-buf.remaining());
                finish.put(buf).rewind();
                h ^= finish.getLong();
                h *= m;
            }

            h ^= h >>> r;
            h *= m;
            h ^= h >>> r;

            buf.order(byteOrder);
            return h;
        }

    }

}
