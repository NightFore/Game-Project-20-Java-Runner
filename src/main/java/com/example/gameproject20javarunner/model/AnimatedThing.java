// AnimatedThing.java

package com.example.gameproject20javarunner;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class AnimatedThing {
    private double x;
    private double y;
    private final double width;
    private final double height;
    private double finalWidth;
    private double finalHeight;
    private final ImageView imageView;
    private final int attitude;
    private int index;
    private final int maxIndex;
    private final int duration;
    private int frameDuration;
    private final double frameOffsetX;
    private final double frameOffsetY;

    public AnimatedThing(double x, double y, double width, double height, int attitude, int index, int maxIndex, int duration, double frameOffsetX, double frameOffsetY, String fileName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.attitude = attitude;
        this.index = index;
        this.maxIndex = maxIndex;
        this.duration = duration;
        this.frameDuration = duration;
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

        // Default final size to the original size
        this.finalWidth = width;
        this.finalHeight = height;
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

    // Getter for the final width
    public double getFinalWidth() {
        return finalWidth;
    }

    // Getter for the final height
    public double getFinalHeight() {
        return finalHeight;
    }

    // Method to get the hitbox of the animated thing
    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, finalWidth, finalHeight);
    }

    // Public method to set the x position
    public void setX(double x) {
        this.x = x;
    }

    // Public method to set the y position
    public void setY(double y) {
        this.y = y;
    }

    // Function to set the final size of the image and adjust the view
    public void setFinalSizeAndAdjustView(double finalWidth, double finalHeight) {
        if (finalWidth > 0 && finalHeight > 0) {
            this.finalWidth = finalWidth;
            this.finalHeight = finalHeight;
            imageView.setFitWidth(finalWidth);
            imageView.setFitHeight(finalHeight);
        }
    }

    // Method to update the viewport based on the current index
    private void updateViewport() {
        double frameX = index * width + frameOffsetX;
        double frameY = attitude * height + frameOffsetY;
        Rectangle2D viewport = new Rectangle2D(frameX, frameY, width, height);
        imageView.setViewport(viewport);
    }

    // Draw method to update the position based on the camera
    public void draw(Camera camera) {
        getImageView().setX(getX() - camera.getX());
        getImageView().setY(getY() - camera.getY());
    }

    // Rendering method to handle animation logic
    public void render(double deltaTime) {
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