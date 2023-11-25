package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.SpriteThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class Heart extends SpriteThing {
    private static final double DISPLAY_WIDTH = 32.0;
    private static final double DISPLAY_HEIGHT = 32.0;
    private static final double SPRITE_WIDTH = 32.0;
    private static final double SPRITE_HEIGHT = 32.0;
    private static final double SPRITE_OFFSET_X = 0.0;
    private static final double SPRITE_OFFSET_Y = 0.0;
    private static final String SPRITE_SHEET_PATH = "/img/NicoleMarieT_Heart_Sprite_Sheet_32x32.png";

    public Heart(Camera camera, Pane root, double x, double y, int initialState) {
        super(camera, root, x, y, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, SPRITE_SHEET_PATH);

        // Set the initial heart state
        setHeartState(initialState);
    }

    public void setHeartState(int state) {
        updateViewport(state, 0); // Assuming the states are arranged horizontally in the sprite sheet
    }
}
