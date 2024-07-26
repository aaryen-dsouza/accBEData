package com.example.demoACC.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CuckooHashTable {
    private static final int INITIAL_SIZE = 101;
    private String[] table1, table2;
    private int size;

    public CuckooHashTable() {
        this.size = INITIAL_SIZE;
        this.table1 = new String[size];
        this.table2 = new String[size];
    }

    private int hash1(String key) {
        return Math.abs(key.hashCode() % size);
    }

    private int hash2(String key) {
        return Math.abs((key.hashCode() / size) % size);
    }

    public void insert(String key) {
        for (int count = 0; count < size; count++) {
            int pos1 = hash1(key);
            if (table1[pos1] == null) {
                table1[pos1] = key;
                return;
            }
            String temp = table1[pos1];
            table1[pos1] = key;
            key = temp;

            int pos2 = hash2(key);
            if (table2[pos2] == null) {
                table2[pos2] = key;
                return;
            }
            temp = table2[pos2];
            table2[pos2] = key;
            key = temp;
        }

        rehash();
        insert(key);
    }

    private void rehash() {
        String[] oldTable1 = table1;
        String[] oldTable2 = table2;
        this.size *= 2;
        this.table1 = new String[size];
        this.table2 = new String[size];
        for (String key : oldTable1) {
            if (key != null) {
                insert(key);
            }
        }
        for (String key : oldTable2) {
            if (key != null) {
                insert(key);
            }
        }
    }

    public boolean search(String key) {
        int pos1 = hash1(key);
        if (key.equals(table1[pos1])) {
            return true;
        }
        int pos2 = hash2(key);
        return key.equals(table2[pos2]);
    }

    public static void main(String[] args) {
        CuckooHashTable hashTable = new CuckooHashTable();
        Set<String> vocabulary = new HashSet<>(Arrays.asList("apple", "banana", "orange")); // Example vocabulary
        for (String word : vocabulary) {
            hashTable.insert(word);
        }
        System.out.println(hashTable.search("apple"));  // Output: true
        System.out.println(hashTable.search("grape"));  // Output: false
    }
}
