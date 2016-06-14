package io.neo.structrue.binary;

import io.neo.structure.tree.BSTree;
import io.neo.structure.tree.BTreePrinter;
import io.neo.structure.tree.BinaryTree;
import org.junit.Test;

/**
 * Created by lunjianchang on 5/31/16.
 */
public class BSTreeTest {

    @Test
    public void testInsert() {
        BinaryTreeTest treeTest = new BinaryTreeTest();

        BinaryTree t = treeTest.build();



        BinaryTree.printTree(t);

        BinaryTree b = new BinaryTree(23);
        BSTree.insert(t,b);
        BinaryTree.printTree(t);
    }
    @Test
    public void testInsert2() {
        BinaryTreeTest treeTest = new BinaryTreeTest();

        BinaryTree t = treeTest.build();

        BinaryTree.printTree(t);

        BinaryTree b = new BinaryTree(23);
        BSTree.insert2(t,b);
        BinaryTree.printTree(t);
    }

    @Test
    public void testDel() {
        BinaryTree t =   BinaryTreeTest.build();
        BTreePrinter.printNode(t);

        BSTree.delete(t, 30);
        BTreePrinter.printNode(t);
    }
}
