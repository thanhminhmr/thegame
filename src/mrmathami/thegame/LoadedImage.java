package mrmathami.thegame;

import javafx.scene.image.Image;

public class LoadedImage {
	private static Image load(String path) {
		return new Image(LoadedImage.class.getResourceAsStream(path));
	}
	public static final Image MOUNTAIN = load("/graphic/mountain.png");
	public static final Image ROAD = load("/graphic/road.png");
	public static final Image NORMAL_TOWER = load("/graphic/normalTower.png");
	public static final Image MACHINE_GUN_TOWER = load("/graphic/machineGunTower.png");
	public static final Image SNIPER_TOWER = load("/graphic/sniperTower.png");
	public static final Image $$$ = load("/graphic/$.png");
}
