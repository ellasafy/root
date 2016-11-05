package io.neo.DataTreePath;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lunjianchang on 8/3/16.
 */
public class DataTree {
    private ConcurrentHashMap<String,Node> dataNodes = new ConcurrentHashMap<>();
    public void build() {
        Node root = new Node("");
        dataNodes.put("/",root);
        Node proc = new Node("/root");
    }

    @Test
    public void test() {
        dataNodes.put("",new Node("tt"));
        System.out.println(dataNodes.get("").getParent());
        System.out.println((1<<2)&(1<<4));

    }
}
