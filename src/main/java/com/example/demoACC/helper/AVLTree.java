package com.example.demoACC.helper;

import java.util.List;

import com.example.demoACC.model.AVLNode;;

public class AVLTree {
    private AVLNode root;

    public void insert(String key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, String key) {
        if (node == null) {
            return new AVLNode(key);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = insert(node.left, key);
        } else if (compareResult > 0) {
            node.right = insert(node.right, key);
        } else {
            node.frequency++;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void getTopSearches(List<AVLNode> topSearches) {
        getTopSearches(root, topSearches);
        topSearches.sort((a, b) -> b.frequency - a.frequency);
    }

    private void getTopSearches(AVLNode node, List<AVLNode> topSearches) {
        if (node != null) {
            topSearches.add(node);
            getTopSearches(node.left, topSearches);
            getTopSearches(node.right, topSearches);
        }
    }
}
