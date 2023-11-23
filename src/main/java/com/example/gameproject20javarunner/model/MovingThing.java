// MovingThing.java

package com.example.gameproject20javarunner.model;

// A class representing a moving animated element in the game
public class MovingThing extends AnimatedThing {
    private double speedX = 0; // Speed in the x-direction
    private double speedY = 0; // Speed in the y-direction
    private double directionX = 0; // Direction in the x-direction (-1.0 for left, 1.0 for right)
    private double directionY = 0; // Direction in the y-direction (-1.0 for up, 1.0 for down)

    // Constructor to initialize properties of the moving animated element
    public MovingThing(double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        // Call to the constructor of the parent class AnimatedThing
        super(x, y, frameWidth, frameHeight, frameOffsetX, frameOffsetY, attitude, maxIndex, duration, fileName);
    }

    // Setter for the speed in the x-direction
    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    // Setter for the speed in the y-direction
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    // Setter for both speed components
    public void setSpeed(double speedX, double speedY) {
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    // Setter for the direction in the x-direction
    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    // Setter for the direction in the y-direction
    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    // Setter for both direction components
    public void setDirection(double directionX, double directionY) {
        setDirectionX(directionX);
        setDirectionY(directionY);
    }

    // Method for movement with speed and direction
    private void move(double deltaTime) {
        // Calculate new position based on speed, direction, and time
        double newX = getX() + speedX * directionX * deltaTime;
        double newY = getY() + speedY * directionY * deltaTime;

        // Set the new position
        setPosition(newX, newY);
    }

    // Update method to handle animation logic and movement
    public void update(double deltaTime) {
        // Call the update method of the parent class AnimatedThing
        super.update();

        // Move the element based on speed and direction
        move(deltaTime);
    }
}
