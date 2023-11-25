package com.example.gameproject20javarunner.view;

import com.example.gameproject20javarunner.model.StaticThing;
import javafx.scene.layout.Pane;

public class Background extends StaticThing {
    public Background(Camera camera, Pane root, double x, double y, double displayWidth, double displayHeight, String fileName) {
        super(camera, root, x, y, displayWidth, displayHeight, fileName);
    }
}
