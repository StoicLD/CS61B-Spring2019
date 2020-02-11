package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;

public class Hint {
    public static void main(String[] args) {
        System.out.println("The powers of 256 in Java are:");
        int x = 1;
        for (int i = 0; i < 10; i += 1) {
            System.out.println(i + "th power: " + x);
            x = x * 256 + StdRandom.uniform(255);
        }
    }
} 
