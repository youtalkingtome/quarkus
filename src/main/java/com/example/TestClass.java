package com.example;

public class TestClass {
    public static void main(String[] args) {
        String largeString="large String";

        String smallSubstring = new String(largeString.substring(5, 9));
    }
}
