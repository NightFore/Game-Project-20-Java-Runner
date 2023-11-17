// Hero.java

package com.example.gameproject20javarunner;

public class Hero extends AnimatedThing {
    private double speed;

    public Hero(double x, double y) {
        super(x, y, 84, 100, 0, 0, 5, 8, 5, 0, "/img/heros.png");
        this.speed = 5; // Set the default speed
    }

    // Method for movement with direction
    public void move(double direction) {
        setX(getX() + direction * speed);
    }

    // Draw method to update the position of the hero based on the camera
    public void draw(double cameraX) {
        getImageView().setX(getX() - cameraX);
    }

    // Setter for speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Getter for speed
    public double getSpeed() {
        return speed;
    }
}
