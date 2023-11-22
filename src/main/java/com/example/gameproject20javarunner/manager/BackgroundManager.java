// BackgroundManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Background;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundManager {
    private final Background backgroundLeft;
    private final Background backgroundRight;
    private static final String IMAGE_PATH = "/img/desert.png";
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;

    public BackgroundManager(Pane root) {
        backgroundLeft = new Background(WIDTH, HEIGHT, IMAGE_PATH);
        backgroundRight = new Background(WIDTH, HEIGHT, IMAGE_PATH);
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
