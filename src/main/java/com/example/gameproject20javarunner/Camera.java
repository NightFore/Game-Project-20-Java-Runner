// Camera.java

package com.example.gameproject20javarunner;

public class Camera {
    private double x;
    private double y;
    private double vx;
    private final double m; // mass constant
    private final double k; // spring constant
    private final double f; // damping constant

    // Constructor with two Integer arguments
    public Camera(double x, double y, double m, double k, double f) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.k = k;
        this.f = f;
        this.vx = 0;
    }

    // Getters for x and y
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Move method to update the position of the camera
    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    // Override toString method to display x and y comma separated
    @Override
    public String toString() {
        return x + "," + y;
    }

    // Update method to apply physics equations
    public void update(double deltaTime, double targetX) {
        double springForce = k * (targetX - x);
        double dampingForce = f * vx;
        double totalForce = springForce - dampingForce;
        double ax = totalForce / m;
        vx += ax * deltaTime;
        x += vx * deltaTime;
    }
}
