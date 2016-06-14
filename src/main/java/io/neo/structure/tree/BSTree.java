package io.neo.structure.tree;

import java.util.TreeSet;

/**
 * Created by lunjianchang on 5/31/16.
 */
public class BSTree {


    public static void insert(BinaryTree root, BinaryTree t) {
        if (root == null) {
            root = t;
        }
        BinaryTree x = root;

        while (x != null) {
            if (x.value > t.value) {
                if (x.left == null) {
                    x.left = t;
                    break;
                } else {
                    x = x.left;
                }
            } else {
                if (x.right == null) {
                    x.right = t;
                    break;
                } else {
                    x = x.right;
                }
            }
        }

    }

    public static void insert2(BinaryTree root, BinaryTree t) {

        if (root.value > t.value) {
            if (root.left == null) {
                root.left = t;
            } else {
                insert2(root.left,t);
            }
        } else {
            if (root.right == null) {
                root.right = t;
            } else {
                insert2(root.right,t);
            }
        }

    }

    public static void delete(BinaryTree root, int v) {
        if (root == null) {
            return;
        }

        BinaryTree b = findValue(root,v);
        if (b == null) {
            return;
        }
        BinaryTree left = b.left;
        BinaryTree right = b.right;
        BinaryTree parent = b.parent;

        if (left == null && right == null) {
            if (parent.left == b) {
                parent.left = null;
            }else {
                parent.right = null;
            }
        }else if (left == null && right != null) {
            parent.right = right;
        } else if (right == null && left != null) {
            parent.left = left;
        } else {
            BinaryTree min = findMin(right);
            if (b.parent.left == b) {
                b.parent.left = min;
                min.left = b.left;
                min.right = b.right;
                delete(right, min.value);
            } else {
                b.parent.right = min;
                min.left =  b.left;
                min.right = b.right;
                delete(left,min.value);
            }
        }



    }

    public static BinaryTree findValue(BinaryTree root, int v) {
        if (root == null) {
            return null;
        }
        if (root.value == v) {
            return root;
        }else if (root.value > v) {
            return findValue(root.left,v);
        } else {
            return findValue(root.right,v);
        }
    }

    public static BinaryTree findMin(BinaryTree root) {
        BinaryTree b = root;
        while (b.left != null) {
            b = b.left;
        }

        return b;
    }
}
