// FoeManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Camera;
import com.example.gameproject20javarunner.entity.Foe;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoeManager {
    private final List<Foe> foes;

    // Constants (Foe)
    private static final int MIN_FOES = 2;
    private static final int MAX_FOES = 10;
    private static final float FOE_MIN_X = 500;
    private static final float FOE_MAX_X = 1000;
    private static final float FOE_MIN_Y = 400;
    private static final float FOE_MAX_Y = 450;

    public FoeManager(Pane root) {
        foes = new ArrayList<>();
        initializeFoes(root);
    }

    private void initializeFoes(Pane root) {
        // Add a single Foe
        Random random = new Random();
        double x = random.nextDouble() * (FOE_MAX_X - FOE_MIN_X) + FOE_MIN_X;
        double y = random.nextDouble() * (FOE_MAX_Y - FOE_MIN_Y) + FOE_MIN_Y;
        foes.add(new Foe(x, y));

        // Add the ImageViews of Foes to the main container
        for (Foe foe : foes) {
            root.getChildren().add(foe.getImageView());
        }
    }

    // Add a random number of Foes to the list
    public void addRandomFoes(Pane root) {
        Random random = new Random();
        int numberOfFoes = random.nextInt(MAX_FOES - MIN_FOES + 1) + MIN_FOES;

        for (int i = 0; i < numberOfFoes; i++) {
            double x = random.nextDouble() * (FOE_MAX_X - FOE_MIN_X) + FOE_MIN_X;
            double y = random.nextDouble() * (FOE_MAX_Y - FOE_MIN_Y) + FOE_MIN_Y;

            Foe foe = new Foe(x, y);
            foes.add(foe);
            root.getChildren().add(foe.getImageView());
        }
    }

    public void update(double deltaTime) {
        for (Foe foe : foes) {
            foe.update(deltaTime);
        }
    }

    public void draw(Camera camera) {
        for (Foe foe : foes) {
            foe.draw(camera);
        }
    }
}
