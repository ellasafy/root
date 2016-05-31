package io.neo.structure.tree;

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
}
