package io.neo.structrue.binary;

import io.neo.structure.tree.BTreePrinter;
import io.neo.structure.tree.BinaryTree;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by lunjianchang on 5/26/16.
 */
public class BinaryTreeTest {

    public static BinaryTree build() {
        BinaryTree t = new BinaryTree(20);
        BinaryTree left1 = new BinaryTree(10);
        left1.setParent(t);
        BinaryTree right1 = new BinaryTree(30);
        right1.setParent(t);
        t.setLeft(left1);
        t.setRight(right1);

        BinaryTree left11 = new BinaryTree(8);
        left11.setParent(left1);
        BinaryTree left12 = new BinaryTree(13);
        left12.setParent(left1);
        left1.setLeft(left11);
        left1.setRight(left12);

        BinaryTree right11 = new BinaryTree(22);
        right11.setParent(right1);
        BinaryTree right12 = new BinaryTree(33);
        right12.setParent(right1);
        right1.setLeft(right11);
        right1.setRight(right12);

        BinaryTree right21 = new BinaryTree(44);
        right21.setParent(right12);
        right12.setRight(right21);



        return t;
    }

    @Test
    public void testprint() {


        List<BinaryTree> t = Collections.singletonList(build());
        for (BinaryTree tt : t) {
            System.out.println(tt.value);
        }
        BTreePrinter.printNode(build());
    }

    public void binarySearchTreeDelete() {
        BinaryTree t = build();

    }
}
