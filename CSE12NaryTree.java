/**
 * Name: Morales, Kyle 
 * ID: A16162998
 * Email: kmmorale@ucsd.edu
 * File description: 
 * implementation of the N-ary tree data structure and its node subclass
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Nary Tree class containing the constructor, add, find, and sort methods for 
 * the Nary Tree data structure
 * Also contains the Node subclass and its respective methods
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Method that adds element to Nary Tree according
     * to level order
     * 
     * @param element
     * @throws NullPointerException
     */
    public void add(E element) throws NullPointerException {
        if(element == null) {
            throw new NullPointerException();
        }
        else if(this.root == null) {
            this.root = new Node(element);
        }
        else {
            Queue<Node> frontQueue = new LinkedList<>();
            ArrayList<Node> discoArray = new ArrayList<>();
            Node currNode = this.root;

            frontQueue.add(currNode);
            discoArray.add(currNode);

            while(!frontQueue.isEmpty()) {
                currNode = frontQueue.remove();
                if(currNode.getNumChildren() < this.N) {
                    Node aChild = new Node(element);
                    currNode.addChild(aChild);
                    this.size++;
                    return;
                }
                for(int i = 0; i < currNode.getNumChildren() - 1; i++) {
                    if(!discoArray.contains(currNode.getChildren().get(i))) {
                        frontQueue.add(currNode.getChildren().get(i));
                        discoArray.add(currNode.getChildren().get(i));
                    }
                }
            }
        }
    }

    /**
     * Helper method that returns an arraylist of all 
     * nodes of a given Nary Tree
     * @param root
     * @return an array of all tree elements
     */
    private LinkedList<Node> chevyTraverse() { 
        Queue<Node> frontQueue = new LinkedList<>(); //frontierQueue
        LinkedList<Node> discoArray = new LinkedList<>(); //array of discovered elements
        Node currNode = this.root;

        frontQueue.add(currNode);
        discoArray.add(currNode);

        while(!frontQueue.isEmpty()) {
            currNode = frontQueue.remove();
            for(int i = 0; i < currNode.getNumChildren() - 1; i++) {
                if(!discoArray.contains(currNode.getChildren().get(i))) {
                    frontQueue.add(currNode.getChildren().get(i));
                    discoArray.add(currNode.getChildren().get(i));
                }
            }
        }
        return discoArray;
    }
    /**
     * Method that searches for element within a given
     * Nary Tree
     * @param element
     * @return boolean whether element is found
     */
    public boolean contains(E element) throws NullPointerException {
        if(element == null) {
            throw new NullPointerException();
        }
        else {
            Queue<Node> frontQueue = new LinkedList<Node>();
            ArrayList<Node> discoArray = new ArrayList<>();
            Node currNode;

            frontQueue.add(this.root);
            discoArray.add(this.root);

            while(!frontQueue.isEmpty()) {
                currNode = frontQueue.remove();
                if(currNode.getData().equals(element)) {
                    return true;
                }
                for(int i = 0; i < currNode.getNumChildren() - 1; i++) {
                    if(!discoArray.contains(currNode.getChildren().get(i))) {
                        frontQueue.add(currNode.getChildren().get(i));
                        discoArray.add(currNode.getChildren().get(i));
                    }
                }
            }
            return false;
        }
    }

    /**
     * TODO: Add method header
     */
    public ArrayList<E> sortTree(){
        LinkedList<Node> unsorted = this.chevyTraverse();
        ArrayList<E> sorted = new ArrayList<>();
        PriorityQueue<Node> heap = new PriorityQueue<>(unsorted);
        E temp;
        
        if(this.size == 0) {
            return sorted;
        }
        while(!heap.isEmpty()) {
            sorted.add(heap.remove().getData());
        }
        for(int i = 0; i < sorted.size() - 1; i++) {
            temp = sorted.get(i);
            sorted.set(i, sorted.get(sorted.size() - i));
            sorted.set(sorted.size() - i, temp);
        }
        return sorted;
    }
}
