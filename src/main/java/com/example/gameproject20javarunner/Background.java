package com.example.gameproject20javarunner;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Background {
    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;
    private static final String IMAGE_PATH = "/img/desert.png";
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;

    public Background(Pane root) {
        backgroundLeft = new StaticThing(WIDTH, HEIGHT, IMAGE_PATH);
        backgroundRight = new StaticThing(WIDTH, HEIGHT, IMAGE_PATH);
        root.getChildren().addAll(getBackgroundLeft(), getBackgroundRight());
    }

    public ImageView getBackgroundLeft() {
        return backgroundLeft.getImageView();
    }

    public ImageView getBackgroundRight() {
        return backgroundRight.getImageView();
    }

    public void update() {
    }

    public void draw(Camera camera) {
        double cameraX = camera.getX();
        double cameraY = camera.getY();

        backgroundLeft.getImageView().setX(-WIDTH / 2 - cameraX);
        backgroundLeft.getImageView().setY(cameraY);

        backgroundRight.getImageView().setX(WIDTH / 2 - cameraX);
        backgroundRight.getImageView().setY(cameraY);
    }
}
