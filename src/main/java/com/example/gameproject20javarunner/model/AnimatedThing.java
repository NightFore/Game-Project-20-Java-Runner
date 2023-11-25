// AnimatedThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

/**
 * A class representing an animated element in the game.
 * Extends the abstract class StaticThing.
 */
public abstract class AnimatedThing extends StaticThing {
    // Frame Attributes
    protected double frameWidth;
    protected double frameHeight;
    protected double frameOffsetX;
    protected double frameOffsetY;

    // Animation Attributes
    private int index = 0;
    private int frameDuration = 0;
    private final int attitude;
    private final int maxIndex;
    private final int duration;

    /**
     * Constructs an AnimatedThing with the specified initial position, frame dimensions, frame offsets, attitude, max index, duration, and image file.
     *
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
    public AnimatedThing(Camera camera, Pane root, double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        // Call to the constructor of the parent class StaticThing
        super(camera, root, x, y, frameWidth, frameHeight, fileName);

        // Set frame attributes
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameOffsetX = frameOffsetX;
        this.frameOffsetY = frameOffsetY;

        // Set animation attributes
        this.attitude = attitude;
        this.maxIndex = maxIndex;
        this.duration = duration;

        // Set the initial viewport of the ImageView to the first frame
        updateViewport();
    }

    /**
     * Method to update the viewport based on the current index.
     */
    private void updateViewport() {
        double frameX = index * frameWidth + frameOffsetX;
        double frameY = attitude * frameHeight + frameOffsetY;
        Rectangle2D viewport = new Rectangle2D(frameX, frameY, frameWidth, frameHeight);
        imageView.setViewport(viewport);
    }

    /**
     * Update method to handle animation logic.
     */
    public void update() {
        // Increment the frame duration
        frameDuration++;

        // Check if it's time to change frames
        if (frameDuration >= duration) {
            // Reset the duration
            frameDuration = 0;

            // Move to the next frame
            index++;

            // Check if we reached the maximum index
            if (index > maxIndex) {
                // Reset to the first frame
                index = 0;
            }

            // Update the viewport based on the new index
            updateViewport();
        }
    }
}
