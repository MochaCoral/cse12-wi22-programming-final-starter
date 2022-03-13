/**
 * Name: Morales, Kyle
 * ID: A16162998
 * Email: kmmorale@ucsd.edu
 * File description: Tester for methods implemented within the CSE12NaryTree Class
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * Class containing JUnit testers for testing methods implemented within the CSE12NaryTree Class
 */
public class CSE12NaryTreeTester {
    /**
     * Add to a binary tree
     * test cases
     * 1. perfect binary tree
     * 2. complete binary tree (not perfect)
     * 3. complete binary tree (not full)
     * 4. empty tree
     */
    @Test
    public void testAdd(){
        CSE12NaryTree<Integer> testAdd = new CSE12NaryTree<>(2);
        testAdd.add(5);
        assertEquals("root should be 5", 5, testAdd.root.getData());
        testAdd.add(5);
        assertEquals("root left child should be 5", 5, 
            testAdd.root.getChildren().get(0).getData());
        testAdd.add(5);
        assertEquals("root right child should be 5", 5,
            testAdd.root.getChildren().get(1).getData());
        testAdd.add(5);
        assertEquals("left child of root left child should be 5", 5,
            testAdd.root.getChildren().get(0).getChildren().get(0).getData());
    }

    /**
     * Search for an internal node of a ternary Tree
     */
    @Test
    public void testContains(){
        CSE12NaryTree<Integer> testContains = new CSE12NaryTree<>(3);
        for(int i = 0; i < 5; i++) {
            testContains.add(5);
        }
        testContains.add(4);
        for(int i = 0; i < 4; i++) {
            testContains.add(5);
        }
        assertTrue("contains() should return true", testContains.contains(4));
    }

    /**
     * Sort the contents of an Onary Tree(singly linked list) using the sortTree() method
     */
    @Test
    public void testSortTree(){
        CSE12NaryTree<Integer> testSortTree = new CSE12NaryTree<>(1);
        for(int i = 0; i < 10; i++) {
            testSortTree.add(10 - i);
        }
        ArrayList<Integer> postSort = testSortTree.sortTree();
        for(int i = 1; i < 10; i++) {
            assertEquals("Should be " + Integer.toString(i), i, postSort.get(i - 1));
        }
    }

    /**
     * Test if NullPointer Exception is thrown for pertinent methods
     */
    @Test
    public void testCustom(){
        boolean exceptionThrown = false;
        CSE12NaryTree<Integer> testCustom = new CSE12NaryTree<>(4);
        
        try {
            testCustom.add(null);
        }
        catch(NullPointerException e) {
            exceptionThrown = true;
        }
        assertTrue("Null pointer exception should have been thrown", 
            exceptionThrown);
        
        try {
            testCustom.contains(null);
        }
        catch(NullPointerException e) {
            exceptionThrown = true;
        }
        assertTrue("Null Pointer exception should have been thrown(contains)",
            exceptionThrown);
    }
}
