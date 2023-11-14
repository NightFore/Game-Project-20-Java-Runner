package com.example.gameproject20javarunner;

public class Camera {
    private int x;
    private int y;

    // Constructor with two Integer arguments
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Override toString method to display x and y comma separated
    @Override
    public String toString() {
        return x + "," + y;
    }
}
