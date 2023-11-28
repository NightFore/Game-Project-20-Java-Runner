// AudioManager.java

package com.example.gameproject20javarunner.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manages audio resources, including background music and sound effects.
 */
public class AudioManager {
    private final Map<String, MediaPlayer> resources; // Map to store audio resources
    private String currentMusicName;
    private double musicVolume;
    private int loop;

    /**
     * Constructor for the AudioManager class.
     * Initializes data structures and default settings.
     */
    public AudioManager() {
        this.resources = new HashMap<>();
        this.currentMusicName = null;
        this.musicVolume = 1.0;
        this.loop = -1;
    }

    /**
     * Sets the volume level for background music.
     *
     * @param volume The volume level (between 0.0 and 1.0).
     */
    public void setMusicVolume(double volume) {
        musicVolume = Math.min(1.0, Math.max(0.0, volume));
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(musicVolume);
        }
    }

    /**
     * Increments the volume level for background music.
     *
     * @param increment The amount by which to increment the volume.
     */
    public void incrementMusicVolume(double increment) {
        setMusicVolume(musicVolume + increment);
    }

    /**
     * Sets the number of loops for background music.
     *
     * @param loop The number of loops (-1 for indefinite looping).
     */
    public void setMusicLoop(int loop) {
        this.loop = loop;
    }

    /**
     * Plays the specified background music.
     *
     * @param audioName The name of the background music resource.
     */
    public void playMusic(String audioName) {
        validateAudioResource(audioName);
        if (!audioName.equals(currentMusicName)) {
            stopMusic();
            currentMusicName = audioName;
            MediaPlayer mediaPlayer = resources.get(audioName);
            playMedia(mediaPlayer, loop);
        }
    }

    /**
     * Plays the specified sound effect.
     *
     * @param audioName The name of the sound effect resource.
     */
    public void playSound(String audioName) {
        validateAudioResource(audioName);
        MediaPlayer mediaPlayer = resources.get(audioName);
        playMedia(mediaPlayer, 1);
    }

    /**
     * Stops the currently playing background music.
     */
    public void stopMusic() {
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        stopMedia(mediaPlayer);
    }

    /**
     * Pauses the currently playing background music.
     */
    public void pauseMusic() {
        pauseMedia(currentMusicName);
    }

    /**
     * Unpauses the currently paused background music.
     */
    public void unpauseMusic() {
        playMedia(resources.get(currentMusicName), loop);
    }

    /**
     * Toggles between playing and pausing the background music.
     */
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

    /**
     * Validates the existence of the specified audio resource.
     *
     * @param audioName The name of the audio resource to validate.
     * @throws IllegalArgumentException if the audio resource does not exist.
     */
    private void validateAudioResource(String audioName) {
        if (!resources.containsKey(audioName)) {
            throw new IllegalArgumentException("Audio resource '" + audioName + "' does not exist.");
        }
    }


    /**
     * Loads an audio resource into the AudioManager.
     *
     * @param audioName The name to associate with the audio resource.
     * @param filePath  The file path to the audio resource.
     * @throws IllegalArgumentException if the audio file is not found.
     */
    public void loadAudio(String audioName, String filePath) {
        try {
            // Convert the input stream URL to URI
            URI uri = Objects.requireNonNull(getClass().getResource(filePath)).toURI();

            // Use Media and MediaPlayer to load and store the audio file
            Media media = new Media(uri.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            resources.put(audioName, mediaPlayer);
        } catch (URISyntaxException | NullPointerException e) {
            throw new IllegalArgumentException("Error loading audio file: " + filePath, e);
        }
    }

    /**
     * Plays the specified media with the given loop count.
     *
     * @param mediaPlayer The MediaPlayer to play.
     * @param loopCount   The number of loops for playback.
     */
    private void playMedia(MediaPlayer mediaPlayer, int loopCount) {
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(loopCount);
            mediaPlayer.play();
        }
    }

    /**
     * Stops the specified media playback.
     *
     * @param mediaPlayer The MediaPlayer to stop.
     */
    private void stopMedia(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /**
     * Pauses the specified media playback.
     *
     * @param audioName The name of the audio resource to pause.
     */
    private void pauseMedia(String audioName) {
        MediaPlayer mediaPlayer = resources.get(audioName);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}
