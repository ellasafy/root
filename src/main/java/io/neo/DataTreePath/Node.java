package io.neo.DataTreePath;

import java.util.Set;

/**
 * Created by lunjianchang on 8/3/16.
 */
public class Node {
    private String parent;
    private Set<String> children;

    public Node(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Set<String> getChildren() {
        return children;
    }

    public void setChildren(Set<String> children) {
        this.children = children;
    }
}
