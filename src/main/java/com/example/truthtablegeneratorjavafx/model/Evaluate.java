package com.example.truthtablegeneratorjavafx.model;

public class Evaluate {

    public static boolean and(boolean p, boolean q) {
        return (p && q);
    }

    public static boolean or(boolean p, boolean q) {
        return (p || q);
    }

    public static boolean xor(boolean p, boolean q) {
        return (p ^ q);
    }

    public static boolean implies(boolean p, boolean q) {
        return (!p || q); // !p \/ q is logically equivalent to p implies q
    }

    public static boolean iff(boolean p, boolean q) {
        return (((p && q) || (!p && !q)));
    }

    public static boolean not(boolean p) {
        return (!p);
    }
}
