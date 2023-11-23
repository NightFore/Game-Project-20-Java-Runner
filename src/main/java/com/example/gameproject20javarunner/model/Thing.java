package com.example.gameproject20javarunner.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class Thing {
    protected double x;
    protected double y;
    protected double frameWidth;
    protected double frameHeight;
    protected double frameOffsetX;
    protected double frameOffsetY;
    protected double displayWidth;
    protected double displayHeight;
    protected final ImageView imageView;

    // Constructor taking initial position
    public Thing(String fileName) {
        // Load the image from resources
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));

        // Create an ImageView with the loaded image
        imageView = new ImageView(image);
    }

    // Getter for the x position
    public double getX() {
        return x;
    }

    // Getter for the y position
    public double getY() {
        return y;
    }

    // Getter for the display width
    public double getDisplayWidth() {
        return displayWidth;
    }

    // Getter for the display height
    public double getDisplayHeight() {
        return displayHeight;
    }

    // Getter for the frame width
    public double getFrameWidth() {
        return frameWidth;
    }

    // Getter for the frame height
    public double getFrameHeight() {
        return frameHeight;
    }

    // Getter for the ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Method to get the hitbox of the animated thing
    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, displayWidth, displayHeight);
    }

    // Set the x position
    public void setX(double x) {
        this.x = x;
    }

    // Set the y position
    public void setY(double y) {
        this.y = y;
    }

    // Set the display width
    public void setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
    }

    // Set the display height
    public void setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
    }
}
