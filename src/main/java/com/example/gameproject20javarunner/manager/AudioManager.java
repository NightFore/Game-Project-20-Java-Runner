// AudioManager.java

package com.example.gameproject20javarunner.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manages audio resources, including background music and sound effects.
 */
public class AudioManager {
    private final Map<String, MediaPlayer> resources;
    private String currentMusicName;
    private String currentSoundName;
    private double musicVolume;
    private double soundVolume;
    private int loop;

    /**
     * Constructor for the AudioManager class.
     * Initializes data structures and default settings.
     */
    public AudioManager() {
        this.resources = new HashMap<>();
        this.currentMusicName = null;
        this.currentSoundName = null;
        this.musicVolume = 1.0;
        this.soundVolume = 1.0;
        this.loop = -1;
    }



    // -------------------- Audio Resource Handling -------------------- //
    /**
     * Loads an audio resource into the AudioManager.
     *
     * @param audioName The name to associate with the audio resource.
     * @param filePath  The file path to the audio resource.
     * @throws IllegalArgumentException If an error occurs while loading the audio file.
     */
    public void loadAudio(String audioName, String filePath) {
        try {
            // Get the resource URL for the given file path
            URL resourceUrl = Objects.requireNonNull(getClass().getResource(filePath));

            // Convert the resource URL to URI
            URI uri = resourceUrl.toURI();

            // Create Media from the URI
            Media media = new Media(uri.toString());

            // Create MediaPlayer from the Media
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Store the MediaPlayer in the resources map
            resources.put(audioName, mediaPlayer);

        } catch (URISyntaxException | NullPointerException e) {
            // Handle URI syntax or null pointer exceptions
            String errorMessage = "Error loading audio file: " + filePath;
            throw new IllegalArgumentException(errorMessage, e);
        }
    }



    // -------------------- Volume Control -------------------- //
    /**
     * Sets the volume level for background music.
     *
     * @param volume The volume level (between 0.0 and 1.0).
     */
    public void setMusicVolume(double volume) {
        musicVolume = Math.min(1.0, Math.max(0.0, volume));
        updateAudioVolume(currentMusicName, musicVolume);
    }

    /**
     * Sets the volume level for sound effects.
     *
     * @param volume The volume level (between 0.0 and 1.0).
     */
    public void setSoundVolume(double volume) {
        soundVolume = Math.min(1.0, Math.max(0.0, volume));
        updateAudioVolume(currentSoundName, soundVolume);
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
     * Increments the volume level for sound effects.
     *
     * @param increment The amount by which to increment the volume.
     */
    public void incrementSoundVolume(double increment) {
        setSoundVolume(soundVolume + increment);
    }

    /**
     * Updates the volume of a loaded audio resource in the AudioManager.
     *
     * @param audioName The name of the audio resource to update.
     * @param volume    The new volume level (0.0 to 1.0).
     */
    private void updateAudioVolume(String audioName, double volume) {
        MediaPlayer mediaPlayer = resources.get(audioName);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }



    // -------------------- Music Playback -------------------- //
    /**
     * Plays the specified background music.
     *
     * @param audioName The name of the background music resource.
     */
    public void playMusic(String audioName) {
        if (!audioName.equals(currentMusicName)) {
            validateAudioResource(audioName);
            stopMusic();
            currentMusicName = audioName;
            MediaPlayer mediaPlayer = resources.get(audioName);
            playMedia(mediaPlayer, loop, musicVolume);
        }
    }

    /**
     * Plays the specified sound effect.
     *
     * @param audioName The name of the sound effect resource.
     */
    public void playSound(String audioName) {
        validateAudioResource(audioName);
        currentSoundName = audioName;
        MediaPlayer mediaPlayer = resources.get(audioName);
        mediaPlayer.seek(Duration.seconds(0));
        playMedia(mediaPlayer, 1, soundVolume);
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
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        pauseMedia(mediaPlayer);
    }

    /**
     * Unpauses the currently paused background music.
     */
    public void unpauseMusic() {
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        playMedia(mediaPlayer, loop, musicVolume);
    }

    /**
     * Toggles between playing and pausing the background music.
     */
    public void toggleMusic() {
        MediaPlayer mediaPlayer = resources.get(currentMusicName);
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                // If music is currently playing, pause it
                pauseMedia(mediaPlayer);
            } else {
                // If music is paused or stopped, play it
                playMedia(mediaPlayer, loop, musicVolume);
            }
        }
    }



    // -------------------- Media Playback Helpers -------------------- //
    /**
     * Sets the volume and plays the specified media with the given loop count.
     *
     * @param mediaPlayer The MediaPlayer to play.
     * @param loopCount   The number of loops for playback.
     * @param volume      The volume level (between 0.0 and 1.0).
     */
    private void playMedia(MediaPlayer mediaPlayer, int loopCount, double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(loopCount);
            mediaPlayer.setVolume(volume);
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
     * @param mediaPlayer The MediaPlayer to pause.
     */
    private void pauseMedia(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
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
}
