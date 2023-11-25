// Thing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

// An abstract class representing a game element
public abstract class Thing {
    // ImageView Attributes
    protected final ImageView imageView;
    protected double x;
    protected double y;
    protected double displayWidth;
    protected double displayHeight;

    // Rectangle Attributes
    private final Rectangle hitboxRectangle;
    private final Rectangle displayRectangle;
    private double hitboxWidth;
    private double hitboxHeight;

    // Constructor taking initial position and image file
    public Thing(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

        // Load the image from resources
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));

        // Create an ImageView with the loaded image
        imageView = new ImageView(image);

        // Initialize the display rectangle
        displayRectangle = new Rectangle();
        displayRectangle.setStroke(Color.CYAN);
        displayRectangle.setFill(Color.TRANSPARENT);
        setDisplaySize(image.getWidth(), image.getHeight());

        // Initialize the hitbox rectangle
        hitboxRectangle = new Rectangle();
        hitboxRectangle.setStroke(Color.RED);
        hitboxRectangle.setFill(Color.TRANSPARENT);
        setHitboxSize(0, 0);
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

    // Method to get the surface rectangle
    public Rectangle getDisplayRectangle() {
        return displayRectangle;
    }

    // Getter for the display width
    public double getDisplayWidth() {
        return displayWidth;
    }

    // Getter for the display height
    public double getDisplayHeight() {
        return displayHeight;
    }

    // Set the display width
    public void setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
        imageView.setFitWidth(displayWidth);
        updateDisplayRectangle();
    }

    // Set the display height
    public void setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
        imageView.setFitHeight(displayHeight);
        updateDisplayRectangle();
    }

    // Set the display size
    public void setDisplaySize(double displayWidth, double displayHeight) {
        setDisplayWidth(displayWidth);
        setDisplayHeight(displayHeight);
    }

    // Method to update the hitbox rectangle
    private void updateDisplayRectangle() {
        displayRectangle.setWidth(displayWidth);
        displayRectangle.setHeight(displayHeight);
    }

    // Method to get the hitbox rectangle
    public Rectangle getHitboxRectangle() {
        return hitboxRectangle;
    }

    // Getter for the display width
    public double getHitboxWidth() {
        return hitboxWidth;
    }

    // Getter for the display height
    public double getHitboxHeight() {
        return hitboxHeight;
    }

    // Set the hitbox width
    public void setHitboxWidth(double hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
        updateHitboxRectangle();
    }

    // Set the hitbox height
    public void setHitboxHeight(double hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
        updateHitboxRectangle();
    }

    // Set the hitbox size
    public void setHitboxSize(double hitboxWidth, double hitboxHeight) {
        setHitboxWidth(hitboxWidth);
        setHitboxHeight(hitboxHeight);
    }

    // Method to update the hitbox rectangle
    private void updateHitboxRectangle() {
        hitboxRectangle.setWidth(hitboxWidth);
        hitboxRectangle.setHeight(hitboxHeight);
        hitboxRectangle.setVisible(hitboxHeight != 0 & hitboxHeight != 0);
    }

    /* public void removeFromRoot() {
        if (root.getChildren().contains(getImageView())) {
            root.getChildren().remove(getImageView());
            root.getChildren().remove(getHitboxRectangle());
        }
    } */

    // Draw method to update the position based on the camera
    public void draw(Camera camera) {
        imageView.setX(x - camera.getX());
        imageView.setY(y - camera.getY());
        displayRectangle.setX(x - camera.getX());
        displayRectangle.setY(y - camera.getY());
        hitboxRectangle.setX(x - camera.getX() + (displayWidth - hitboxWidth) / 2);
        hitboxRectangle.setY(y - camera.getY() + (displayHeight - hitboxHeight) / 2);
    }
}
