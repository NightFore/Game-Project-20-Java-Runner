// AnimatedThing.java

package com.example.gameproject20javarunner;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class AnimatedThing {
    private double x;
    private double y;
    private final ImageView imageView;
    private final int attitude;
    private int index;
    private final int duration;
    private int frameDuration;
    private final int maxIndex;
    private final double windowSize;
    private final double frameOffsetX;
    private final double frameOffsetY;

    public AnimatedThing(double x, double y, int attitude, int index, int duration, int maxIndex, double windowSize, double frameOffsetX, double frameOffsetY, String fileName) {
        this.x = x;
        this.y = y;
        this.attitude = attitude;
        this.index = index;
        this.duration = duration;
        this.frameDuration = duration;
        this.maxIndex = maxIndex;
        this.windowSize = windowSize;
        this.frameOffsetX = frameOffsetX;
        this.frameOffsetY = frameOffsetY;

        // Load sprite sheet image from resources
        Image spriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));

        // Create an ImageView with the sprite sheet
        imageView = new ImageView(spriteSheet);

        // Set the initial viewport of the ImageView to the first frame
        updateViewport();

        // Set the position of the AnimatedThing
        imageView.setX(x);
        imageView.setY(y);
    }

    // Getter for the ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Getter for the x position
    public double getX() {
        return x;
    }

    // Getter for the y position
    public double getY() {
        return y;
    }

    // Public method to set the x position
    public void setX(double x) {
        this.x = x;
        imageView.setX(x);
    }

    // Public method to set the y position
    public void setY(double y) {
        this.y = y;
        imageView.setY(y);
    }

    // Method to update the viewport based on the current index
    private void updateViewport() {
        double frameX = index * frameOffsetX;
        double frameY = attitude * (windowSize + frameOffsetY);
        Rectangle2D viewport = new Rectangle2D(frameX, frameY, windowSize, windowSize);
        imageView.setViewport(viewport);
    }

    // Rendering method to handle animation logic
    public void render() {
        // Update the duration
        frameDuration--;

        // Check if it's time to change frames
        if (frameDuration <= 0) {
            // Reset the duration
            frameDuration = duration;

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
