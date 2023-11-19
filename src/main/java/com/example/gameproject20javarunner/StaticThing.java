// StaticThing.java

package com.example.gameproject20javarunner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class StaticThing {
    private final ImageView imageView;

    // Constructor taking the size and the file name
    public StaticThing(double sizeX, double sizeY, String fileName) {
        // Load the image from resources
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
