package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.SpriteThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class Heart extends SpriteThing {
    public Heart(Camera camera, Pane root, double initialX, double initialY, double displayWidth, double displayHeight, double spriteWidth, double spriteHeight, double spriteOffsetX, double spriteOffsetY, String fileName) {
        super(camera, root, initialX, initialY, displayWidth, displayHeight, spriteWidth, spriteHeight, spriteOffsetX, spriteOffsetY, fileName);

        // Set the initial heart state
        setHeartState(0);
    }

    public void setHeartState(int state) {
        updateViewport(state, 0);
    }
}
