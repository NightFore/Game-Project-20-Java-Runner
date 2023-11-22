package com.example.gameproject20javarunner.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class Thing {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
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

    // Getter for the width
    public double getWidth() {
        return width;
    }

    // Getter for the height
    public double getHeight() {
        return height;
    }

    // Getter for the ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Public method to set the x position
    public void setX(double x) {
        this.x = x;
    }

    // Public method to set the y position
    public void setY(double y) {
        this.y = y;
    }
}
