package mrmathami.thegame;

import javafx.scene.media.AudioClip;

import java.io.File;

public class LoadedAudio {
    private static AudioClip load(String path, double volume, int count) {
        File file = new File(LoadedAudio.class.getResource(path).getFile());
        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(volume);
        audioClip.setCycleCount(count);
        return audioClip;
    }

    public static AudioClip sniper() {
        return load("/audio/bulletSound.mp3", 0.02, 1);
    }

    public static AudioClip normal() {
        return load("/audio/normalTower.mp3", 0.03, 1);
    }

    public static AudioClip machineGun() {
        return load("/audio/normalTower.mp3" , 0.02, 1);
    }

    public static AudioClip enemyDestroy() {
        return load("/audio/enemyDestroy.mp3", 0.03, 1);
    }

    static AudioClip BACKGROUND_MUSIC = backgroundMusic();
    private static AudioClip backgroundMusic() {
        return load("/audio/swordland.mp3", 0.02, AudioClip.INDEFINITE);
    }

}
