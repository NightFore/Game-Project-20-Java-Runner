// AudioManager.java

package com.example.gameproject20javarunner.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private Map<String, MediaPlayer> resources;
    private String currentMusicName;
    private double musicVolume;
    private int loop;

    public AudioManager() {
        this.resources = new HashMap<>();
        this.currentMusicName = null;
        this.musicVolume = 1.0;
        this.loop = -1;
    }

    // Settings

    public void setMusicVolume(double volume) {
        validateVolume(volume);
        this.musicVolume = volume;
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public void incrementMusicVolume(double increment) {
        validateIncrement(increment);
        setMusicVolume(Math.min(1.0, Math.max(0.0, musicVolume + increment)));
    }

    public void setMusicLoop(int loop) {
        validateMusicLoop(loop);
        this.loop = loop;
    }

    // Management

    public void playMusic(String audioName) {
        validateAudioResource(audioName);
        if (!audioName.equals(currentMusicName)) {
            stopMusic();
            currentMusicName = audioName;
            MediaPlayer mediaPlayer = resources.get(audioName);
            playMedia(mediaPlayer, loop);
        }
    }

    public void playSound(String audioName) {
        validateAudioResource(audioName);
        MediaPlayer mediaPlayer = resources.get(audioName);
        playMedia(mediaPlayer, 1);
    }

    public void stopMusic() {
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        stopMedia(mediaPlayer);
    }

    public void pauseMusic() {
        pauseMedia(currentMusicName);
    }

    public void unpauseMusic() {
        playMedia(resources.get(currentMusicName), loop);
    }

    public void toggleMusic() {
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                pauseMedia(currentMusicName);
            } else {
                playMedia(mediaPlayer, loop);
            }
        }
    }

    // Validation

    private void validateVolume(double volume) {
        if (volume < 0.0 || volume > 1.0) {
            throw new IllegalArgumentException("Volume must be between 0.0 and 1.0.");
        }
    }

    private void validateIncrement(double increment) {
        // Add validation for increment if necessary
    }

    private void validateMusicLoop(int loop) {
        // Add validation for loop if necessary
    }

    private void validateAudioResource(String audioName) {
        if (!resources.containsKey(audioName)) {
            throw new IllegalArgumentException("Audio resource '" + audioName + "' does not exist.");
        }
    }

    // Load audio resource
    public void loadAudio(String audioName, String filePath) {
        // Get an input stream from the audio file
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Audio file not found: " + filePath);
        }

        try {
            // Convert the input stream URL to URI
            URI uri = getClass().getResource(filePath).toURI();

            // Use Media and MediaPlayer to load and store the audio file
            Media media = new Media(uri.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            resources.put(audioName, mediaPlayer);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Helper methods

    private void playMedia(MediaPlayer mediaPlayer, int loopCount) {
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(loopCount);
            mediaPlayer.play();
        }
    }

    private void stopMedia(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void pauseMedia(String audioName) {
        MediaPlayer mediaPlayer = resources.get(audioName);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}
