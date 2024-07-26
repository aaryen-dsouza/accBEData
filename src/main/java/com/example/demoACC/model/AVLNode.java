package com.example.demoACC.model;

public class AVLNode {
    public String key;
    public int frequency;
    public int height;
    public AVLNode left;
    public AVLNode right;

    public AVLNode(String key) {
        this.key = key;
        this.frequency = 1;  // Initialize frequency to 1 when a new node is created
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
