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
            if (parent == null) {
                root = root.right;
            }else {
                parent.right = right;
            }
        } else if (right == null && left != null) {
            if (parent == null) {
                root = root.left;
            }else {
                parent.left = left;
            }
        } else {
            BinaryTree min = findMin(right);
        }



    }

    public static BinaryTree findValue(BinaryTree root, int v) {
        if (root.value == v) {
            return root;
        }else if (root.value > v) {
            return findMin(root.left);
        } else {
            return findMin(root.right);
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
