// FoeManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Camera;
import com.example.gameproject20javarunner.entity.Foe;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoeManager {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final List<Foe> foes;

    // Constants (Foe)
    private static final int MIN_FOES = 10;
    private static final int MAX_FOES = 25;
    private static final float FOE_MIN_X = 800;
    private static final float FOE_MAX_X = 1600;
    private static final float FOE_MIN_Y = 350;
    private static final float FOE_MAX_Y = 450;

    public FoeManager(Camera camera, Pane root) {
        this.camera = camera;
        this.root = root;
        foes = new ArrayList<>();
        addRandomFoes();
    }

    // Add a random number of Foes to the list
    public void addRandomFoes() {
        Random random = new Random();
        int numberOfFoes = random.nextInt(MAX_FOES - MIN_FOES + 1) + MIN_FOES;

        for (int i = 0; i < numberOfFoes; i++) {
            double x = random.nextDouble() * (FOE_MAX_X - FOE_MIN_X) + FOE_MIN_X;
            double y = random.nextDouble() * (FOE_MAX_Y - FOE_MIN_Y) + FOE_MIN_Y;
            addSingleFoe(x, y);
        }
    }

    public void addSingleFoe(double x, double y) {
        Foe foe = new Foe(camera, root, x, y);
        foes.add(foe);
    }

    public List<Foe> getFoes() {
        return foes;
    }

    public void update(double deltaTime) {
        for (Foe foe : foes) {
            foe.update(deltaTime);
            foe.move(deltaTime);
        }
    }

    public void draw() {
        for (Foe foe : foes) {
            foe.draw();
        }
    }
}
