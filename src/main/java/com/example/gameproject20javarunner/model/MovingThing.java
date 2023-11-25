package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

/**
 * A class representing a moving animated element in the game.
 * Extends the abstract class AnimatedThing.
 */
public abstract class MovingThing extends AnimatedThing {
    // Movement Attributes
    private double speedX = 0;
    private double speedY = 0;
    private double directionX = 0;
    private double directionY = 0;

    /**
     * Constructs a MovingThing with the specified camera, root, initial position, frame dimensions, frame offsets, attitude, max index, duration, and image file.
     *
     * @param camera        The camera used for positioning.
     * @param root          The root pane where the elements are added.
     * @param x              The initial x position.
     * @param y              The initial y position.
     * @param frameWidth     The width of each frame in the animation.
     * @param frameHeight    The height of each frame in the animation.
     * @param frameOffsetX   The x offset of the first frame in the animation.
     * @param frameOffsetY   The y offset of the first frame in the animation.
     * @param attitude       The attitude of the element (e.g., running).
     * @param maxIndex       The maximum index of frames in the animation.
     * @param duration       The total duration of the animation.
     * @param fileName       The file name of the image resource.
     */
    public MovingThing(Camera camera, Pane root, double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        // Call to the constructor of the parent class AnimatedThing
        super(camera, root, x, y, frameWidth, frameHeight, frameOffsetX, frameOffsetY, attitude, maxIndex, duration, fileName);
    }

    /**
     * Setter for the speed in the x-direction.
     *
     * @param speedX The new speed in the x-direction.
     */
    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    /**
     * Setter for the speed in the y-direction.
     *
     * @param speedY The new speed in the y-direction.
     */
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    /**
     * Setter for both speed components.
     *
     * @param speedX The new speed in the x-direction.
     * @param speedY The new speed in the y-direction.
     */
    public void setSpeed(double speedX, double speedY) {
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    /**
     * Setter for the direction in the x-direction.
     *
     * @param directionX The new direction in the x-direction.
     */
    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    /**
     * Setter for the direction in the y-direction.
     *
     * @param directionY The new direction in the y-direction.
     */
    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    /**
     * Setter for both direction components.
     *
     * @param directionX The new direction in the x-direction.
     * @param directionY The new direction in the y-direction.
     */
    public void setDirection(double directionX, double directionY) {
        setDirectionX(directionX);
        setDirectionY(directionY);
    }

    /**
     * Method for movement with speed and direction.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void move(double deltaTime) {
        // Calculate new position based on speed, direction, and time
        double newX = getX() + speedX * directionX * deltaTime;
        double newY = getY() + speedY * directionY * deltaTime;

        // Set the new position
        setPosition(newX, newY);
    }

    /**
     * Update method to handle animation logic and movement.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    public void update(double deltaTime) {
        // Call the update method of the parent class AnimatedThing
        super.update();

        // Move the element based on speed and direction
        move(deltaTime);
    }
}
