// AnimatedThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// A class representing an animated element in the game
public abstract class AnimatedThing extends Thing {
    private int index; // Index of the current image in the animation
    private int frameDuration; // Duration of the current frame
    private final int attitude; // The attitude of the element (e.g., running)
    private final int maxIndex; // Maximum index of frames in the animation
    private final int duration; // Total duration of the animation

    // Constructor to initialize properties of the animated element
    public AnimatedThing(double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        // Call to the constructor of the parent class Thing
        super(x, y, fileName);

        // Initialization of properties specific to AnimatedThing
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameOffsetX = frameOffsetX;
        this.frameOffsetY = frameOffsetY;
        this.attitude = attitude;
        this.maxIndex = maxIndex;
        this.duration = duration;

        // Default values
        this.displayWidth = frameWidth;
        this.displayHeight = frameHeight;
        this.index = 0;
        this.frameDuration = 0;

        // Set the initial viewport of the ImageView to the first frame
        updateViewport();
    }

    // Method to update the viewport based on the current index
    private void updateViewport() {
        double frameX = index * frameWidth + frameOffsetX;
        double frameY = attitude * frameHeight + frameOffsetY;
        Rectangle2D viewport = new Rectangle2D(frameX, frameY, frameWidth, frameHeight);
        imageView.setViewport(viewport);
    }

    // Update method to handle animation logic
    public void update() {
        // Update the duration
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

    public void draw(Camera camera) {
        super.draw(camera);
    }
}
