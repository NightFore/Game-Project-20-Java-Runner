// Thing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

// An abstract class representing a game element
public abstract class Thing {
    // Position
    protected double x;
    protected double y;

    // Display properties
    protected double displayWidth;
    protected double displayHeight;

    // Frame properties
    protected double frameWidth;
    protected double frameHeight;
    protected double frameOffsetX;
    protected double frameOffsetY;

    // ImageView for rendering the image
    protected final ImageView imageView;

    // Constructor taking initial position and image file
    public Thing(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

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

    // Getter for the ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Set the x position
    public void setX(double x) {
        this.x = x;
    }

    // Set the y position
    public void setY(double y) {
        this.y = y;
    }

    // Set both x and y positions
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    // Set the display width
    public void setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
        imageView.setFitWidth(displayWidth);
    }

    // Set the display height
    public void setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
        imageView.setFitHeight(displayHeight);
    }

    // Set the display size
    public void setDisplaySize(double displayWidth, double displayHeight) {
        setDisplayWidth(displayWidth);
        setDisplayHeight(displayHeight);
    }

    // Method to get the display box of the thing
    public Rectangle2D getDisplayBox() {
        return new Rectangle2D(x, y, displayWidth, displayHeight);
    }

    // Draw method to update the position based on the camera
    public void draw(Camera camera) {
        imageView.setX(getX() - camera.getX());
        imageView.setY(getY() - camera.getY());
    }
}
