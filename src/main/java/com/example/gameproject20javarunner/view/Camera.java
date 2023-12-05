// Camera.java

package com.example.gameproject20javarunner.view;

public class Camera {
    // Variables
    private double x;
    private double y;
    private double vx;

    // Constants
    private final double m; // Mass constant
    private final double k; // Spring constant
    private final double f; // Damping constant
    private static final double INITIAL_VELOCITY_X = 0;

    // Constructor
    public Camera(double x, double y, double m, double k, double f) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.k = k;
        this.f = f;
        this.vx = INITIAL_VELOCITY_X;
    }

    // Getter for the x position
    public double getX() {
        return x;
    }

    // Getter for the y position
    public double getY() {
        return y;
    }

    // Update method to apply physics equations
    public void update(double deltaTime, double targetX) {
        double springForce = k * (targetX - x - 100);
        double dampingForce = f * vx;
        double totalForce = springForce - dampingForce;
        double ax = totalForce / m;
        vx += ax * deltaTime;
        x += vx * deltaTime;
    }
}
