// AnimatedThing.java

package com.example.gameproject20javarunner.model;
import com.example.gameproject20javarunner.view.Camera;

import javafx.geometry.Rectangle2D;

public abstract class AnimatedThing extends Thing {
    private int index;
    private int frameDuration;
    private final int attitude;
    private final int maxIndex;
    private final int duration;

    public AnimatedThing(double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        super(fileName);
        this.x = x;
        this.y = y;
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

        // Set the position of the AnimatedThing
        imageView.setX(x);
        imageView.setY(y);
    }

    // Function to set the display size of the image and adjust the view
    public void setDisplaySizeAndAdjustView(double displayWidth, double displayHeight) {
        if (displayWidth > 0 && displayHeight > 0) {
            this.displayWidth = displayWidth;
            this.displayHeight = displayHeight;
            imageView.setFitWidth(displayWidth);
            imageView.setFitHeight(displayHeight);
        }
    }

    // Method to update the viewport based on the current index
    private void updateViewport() {
        double frameX = index * frameWidth + frameOffsetX;
        double frameY = attitude * frameHeight + frameOffsetY;
        Rectangle2D viewport = new Rectangle2D(frameX, frameY, frameWidth, frameHeight);
        imageView.setViewport(viewport);
    }

    // Draw method to update the position based on the camera
    public void draw(Camera camera) {
        getImageView().setX(getX() - camera.getX());
        getImageView().setY(getY() - camera.getY());
    }

    // Rendering method to handle animation logic
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
}
