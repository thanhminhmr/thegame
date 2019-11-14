package mrmathami.thegame;

import javafx.scene.media.AudioClip;

import java.io.File;

public class LoadedAudio {
    private static AudioClip load(String path) {
        File file = new File(LoadedAudio.class.getResource(path).getFile());
        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(0.05);
        return audioClip;
    }

    public static AudioClip SNIPER() {
        return load("/audio/bulletSound.mp3");
    }

    public static AudioClip NORMAL() {
        return load("/audio/normalTower.mp3");
    }

    public static AudioClip MACHINE_GUN() {
        AudioClip audioClip = load("/audio/normalTower.mp3");
        audioClip.setVolume(0.04);
        return audioClip;
    }

    public static AudioClip ENEMY_DESTROY() {
        return load("/audio/enemyDestroy.mp3");
    }

    public static AudioClip HAM_TARGET() {
        return load("/audio/hitTarget.mp3");
    }

    static final AudioClip BACKGROUND_MUSIC = load("/audio/swordland.mp3");
}
