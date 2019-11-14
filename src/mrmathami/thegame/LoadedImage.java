package mrmathami.thegame;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class LoadedImage {
	private static Image load(String path) {
		return new Image(LoadedImage.class.getResourceAsStream(path));
	}
	public static final Image MOUNTAIN = load("/graphic/mountain.png");
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
	public static final Image TARGET = load("/graphic/target.png");
	public static final Image SPAWN = load("/graphic/bHome.png");

//	public static final Image ROAD = load("/graphic/road.png");
	public static final Image ROAD_0 = load("/graphic/road/road0.png");
	public static final Image ROAD_2 = load("/graphic/road/road2.png");
	public static final Image ROAD_3 = load("/graphic/road/road3.png");
	public static final Image ROAD_4 = load("/graphic/road/road4.png");
	public static final Image ROAD_5 = load("/graphic/road/road5.png");
	public static final Image ROAD_6 = load("/graphic/road/road6.png");
	public static final Image ROAD_7 = load("/graphic/road/road7.png");
	public static final Image ROAD_8 = load("/graphic/road/road8.png");
	public static final Image ROAD_9 = load("/graphic/road/road9.png");
	public static final Image ROAD_A = load("/graphic/road/roadA.png");
	public static final Image ROAD_B = load("/graphic/road/roadB.png");
	public static final Image ROAD_C = load("/graphic/road/roadC.png");
	public static final Image ROAD_D = load("/graphic/road/roadD.png");
}
