package com.example.gameproject20javarunner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class StaticThing {
    private double sizeX;
    private double sizeY;
    private ImageView imageView;

    // Constructor taking multiple parameters, including fileName for the background
    public StaticThing(double sizeX, double sizeY, String fileName) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        // Load the image from resources (adjust the file name if necessary)
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
        // Create an ImageView with the loaded image
        imageView = new ImageView(image);
        // Set the size of the ImageView
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
    }

    // Getter for the ImageView
    public ImageView getImageView() {
        return imageView;
    }
}
