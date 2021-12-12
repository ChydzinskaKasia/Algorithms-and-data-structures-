package pl.edu.pw.ee;

import java.io.Serializable;

public class Node implements Serializable {
    int freq;
    char ch;
    Node left;
    Node right;

    Node(int freq, char ch) {
        this.freq = freq;
        this.ch = ch;
        right = null;
        left = null;
    }

    public int getFreq() {
        return freq;
    }

    public char getKey() {
        return ch;
    }
}
