// Camera.java

package com.example.gameproject20javarunner.view;

public class Camera {
    // Variables
    private double x;
    private double y;
    private double vx;
    private double vy;

    // Constants
    private final double m; // Mass constant
    private final double k; // Spring constant
    private final double f; // Damping constant

    // Constructor
    public Camera(double x, double y, double m, double k, double f) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.k = k;
        this.f = f;
        this.vx = 0;
        this.vy = 0;
    }

    // Getter for the x position
    public double getX() {
        return x;
    }

    // Getter for the y position
    public double getY() {
        return y;
    }

    private void followX(double deltaTime, double targetX) {
        double springForce = k * (targetX - x - 400);
        double dampingForce = f * vx;
        double totalForce = springForce - dampingForce;
        double ax = totalForce / m;
        vx += ax * deltaTime;
        x += vx * deltaTime;
    }

    private void followY(double deltaTime, double targetY) {
        double springForce = k * (targetY - y - 400);
        double dampingForce = f * vy;
        double totalForce = springForce - dampingForce;
        double ay = totalForce / m;
        vy += ay * deltaTime;
        y += vy * deltaTime;
    }

    // Update method to apply physics equations
    public void update(double deltaTime, double targetX, double targetY) {
        followX(deltaTime, targetX);
        followY(deltaTime, targetY);
    }
}
