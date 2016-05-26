package io.neo.tree.binary;

import java.util.LinkedList;

/**
 * Created by lunjianchang on 5/26/16.
 */
public class BinaryTree {

    private BinaryTree parent;
    private int value;
    private BinaryTree left;
    private BinaryTree right;


    public BinaryTree() {

    }

    public BinaryTree(int value) {
        this.value = value;
    }
    public BinaryTree getParent() {
        return parent;
    }

    public void setParent(BinaryTree parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public static void printTree(BinaryTree b) {


        if (b == null) {
            System.out.println("null");
        }
        int h = depth(b);
        /**
         *
         * ******5****
         * **4*****4**(t-h)*4+1
         * *3*5***5*4*
         *
         *
         *
         *
         */

        LinkedList<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(b);
        LinkedList<BinaryTree> queue2 = new LinkedList<BinaryTree>();
        int height = h;
        while (!queue.isEmpty()) {
            BinaryTree tmp = queue.removeFirst();

            System.out.print(getStrt(height --) + tmp.getValue());
            if (tmp.getLeft() != null) {
                queue2.add(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue2.add(tmp.getRight());
            }

            if (queue.isEmpty()) {
                while (!queue2.isEmpty()) {
                    queue.add(queue2.removeFirst());
                }
                System.out.println("");
            }
        }
    }
    public static String getStrt(int height) {
        if (height <0) {
            return " ";
        }
        StringBuilder builder = new StringBuilder();
        for (int i= 0;i<height;i++) {
            builder.append(" ");
        }

        return  builder.toString();
    }

    public static int depth(BinaryTree b) {
        if (b == null) {
            return 0;
        }

        int left = depth(b.left) + 1;
        int right = depth(b.right) +1;
        if (left > right) {
            return left;
        }else {
            return right;
        }
    }
}
