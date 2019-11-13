package mrmathami.thegame;

import javafx.scene.media.AudioClip;

import java.io.File;

public class LoadedAudio {
    private static AudioClip load(String path) {
        File file = new File(LoadedAudio.class.getResource(path).getFile());
        return new AudioClip(file.toURI().toString());
    }
    public static AudioClip getBulletSound() {
        return load("/audio/bulletSound.mp3");
    }
    static final AudioClip BACKGROUND_MUSIC = load("/audio/swordland.mp3");
}
