// MovingThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

/**
 * A class representing a moving animated element in the game.
 * Extends the abstract class AnimatedThing.
 */
public class MovingThing extends AnimatedThing {
    // Movement Attributes
    private double speedX = 0;
    private double speedY = 0;
    private double directionX = 0;
    private double directionY = 0;

    /**
     * Constructs a MovingThing with the specified camera, root, initial position, display size, sprite dimensions, sprite offsets, attitude, max index, duration, and image file.
     *
     * @param camera         The camera used for positioning.
     * @param root           The root pane where the elements are added.
     * @param initialX       The initial x position.
     * @param initialY       The initial y position.
     * @param displayWidth   The width of the displayed image.
     * @param displayHeight  The height of the displayed image.
     * @param spriteWidth    The width of each sprite in the sprite sheet.
     * @param spriteHeight   The height of each sprite in the sprite sheet.
     * @param spriteOffsetX  The x offset of the first sprite in the sprite sheet.
     * @param spriteOffsetY  The y offset of the first sprite in the sprite sheet.
     * @param attitude       The attitude of the element (e.g., running).
     * @param maxIndex       The maximum index of sprites in the animation.
     * @param duration       The total duration of the animation.
     * @param fileName       The file name of the image resource.
     */
    public MovingThing(Camera camera, Pane root, double initialX, double initialY, double displayWidth, double displayHeight, double spriteWidth, double spriteHeight, double spriteOffsetX, double spriteOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        // Call to the constructor of the parent class AnimatedThing
        super(camera, root, initialX, initialY, displayWidth, displayHeight, spriteWidth, spriteHeight, spriteOffsetX, spriteOffsetY, maxIndex, attitude, duration, fileName);
    }

    /**
     * Getter for the speed in the x-direction.
     *
     * @return The speed in the x-direction.
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Getter for the speed in the y-direction.
     *
     * @return The speed in the y-direction.
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Getter for the direction in the x-direction.
     *
     * @return The direction in the x-direction.
     */
    public double getDirectionX() {
        return directionX;
    }

    /**
     * Getter for the direction in the y-direction.
     *
     * @return The direction in the y-direction.
     */
    public double getDirectionY() {
        return directionY;
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
    public void move(double deltaTime) {
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
    }
}
