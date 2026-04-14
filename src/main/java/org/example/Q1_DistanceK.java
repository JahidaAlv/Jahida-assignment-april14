package org.example;

// Q1_DistanceK.java

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Q1_DistanceK {

    // Recursive function to print nodes at distance k
    public static void printNodesAtK(Node root, int k) {
        // Base case: if node is null
        if (root == null) return;

        // If k becomes 0, print the node
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }

        // Recur for left and right subtree with k-1
        printNodesAtK(root.left, k - 1);
        printNodesAtK(root.right, k - 1);
    }

    public static void main(String[] args) {
        /*
               1
              / \
             2   3
            / \   \
           4   5   6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        int k = 2;

        System.out.print("Nodes at distance " + k + ": ");
        printNodesAtK(root, k);
    }
}

