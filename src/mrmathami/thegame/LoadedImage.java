package mrmathami.thegame;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class LoadedImage {
	private static Image load(String path) {
		return new Image(LoadedImage.class.getResourceAsStream(path));
	}
	public static final Image MOUNTAIN = load("/graphic/mountain.png");
	public static final Image ROAD = load("/graphic/road.png");
	public static final Image NORMAL_TOWER = load("/graphic/normalTower.png");
	public static final Image MACHINE_GUN_TOWER = load("/graphic/machineGunTower.png");
	public static final Image SNIPER_TOWER = load("/graphic/sniperTower.png");
	static final Image $$$ = load("/graphic/$.png");
	public static final Image NORMAL_ENEMY = load("/graphic/normalEnemy.png");
	public static final Image SMALLER_ENEMY = load("/graphic/smallerEnemy.png");
	public static final Image TANKER_ENEMY = load("/graphic/tankerEnemy.png");
	public static final Image BOSS_ENEMY = load("/graphic/bossEnemy.png");
	static final Image WIN = load("/graphic/winMes.jpg");
	//public static final Image BACKGROUND = load("/graphic/bg.jfif");
	private static BackgroundImage loadBGI(String path) {
		return new BackgroundImage(load(path), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	}
	static final Background BACKGROUND = new Background(loadBGI("/graphic/bg.jfif"));
}
