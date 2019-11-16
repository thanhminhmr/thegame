package mrmathami.thegame;

import javafx.geometry.Insets;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoadedImage {
	private static Image load(String path) {
		return new Image(LoadedImage.class.getResourceAsStream(path));
	}
	public static final Image NORMAL_TOWER = load("/graphic/normalTower.png");
	public static final Image MACHINE_GUN_TOWER = load("/graphic/machineGunTower.png");
	public static final Image SNIPER_TOWER = load("/graphic/sniperTower.png");
	static final Image $$$ = load("/graphic/$.png");
	public static final Image NORMAL_ENEMY = load("/graphic/normalEnemy.png");
	public static final Image SMALLER_ENEMY = load("/graphic/smallerEnemy.png");
	public static final Image TANKER_ENEMY = load("/graphic/tankerEnemy.png");
	public static final Image BOSS_ENEMY = load("/graphic/bossEnemy.png");
	static final Image WIN = load("/graphic/winMes.png");
	static final Image LOSE = load("/graphic/bglose.jpg");
	//public static final Image BACKGROUND = load("/graphic/bg.jfif");
	private static BackgroundImage loadBGI(String path) {
		return new BackgroundImage(load(path), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	}
	static final Background BACKGROUND = new Background(loadBGI("/graphic/bg.jfif"));
	public static final Image TARGET = load("/graphic/target.png");

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

	static ImageView imageView(Image image, double width, double height, boolean effect) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		if (effect){
			imageView.setOnMouseMoved(e -> {
				imageView.setFitWidth(width * 1.1);
				imageView.setEffect(new Glow(0.3));
			});
			imageView.setOnMouseExited(e -> {
				imageView.setFitWidth(width);
				imageView.setEffect(null);
			});
		}
		return imageView;
	}

	static final Background focusBackground = new Background( new BackgroundFill( Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY ) );
	static final Background unFocusBackground = new Background( new BackgroundFill( Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY ) );
	public static final Image[] OBSTACLES = {
			load("/graphic/tree.png"),
			load("/graphic/tree_2.png"),
			load("/graphic/tree_3.png")
	};

	public static final Image[] MOUNTAINS = {
			load("/graphic/grass.png"),
			load("/graphic/grass_1.png"),
			load("/graphic/grass_2.png"),
			load("/graphic/grass_3.png")
	};

	static final Image AUDIO_ON = load("/graphic/audio_on.png");
	static final Image AUDIO_OFF = load("/graphic/audio_mute.png");
	static final Image PAUSE = load("/graphic/pause.png");
	static final Image CONTINUE = load("/graphic/continue.png");
	static final Image AUTO_ON = load("/graphic/icons8-bot-96.png");
	static final Image AUTO_OFF = load("/graphic/bot_off.png");
	static final Image SELL = load("/graphic/icons8-money-128.png");
	static final Image MUSIC_ON = load("/graphic/music_on.png");
	static final Image MUSIC_OFF = load("/graphic/music_mute.png");
	static final Image SHOP = load("/graphic/shop-icon.png");
	static final Image SETTING = load("/graphic/setting.png");
	static final Image RESTART = load("/graphic/restart.png");
	static final Image NEW_GAME = load("/graphic/newGame.png");
	static final Image LAST_GAME = load("/graphic/lastGame.png");
	static final Image GAME_TITLE = load("/graphic/gameTitle.png");
	public static Image[] EXPLOSIONS = {
			load("/graphic/effect_4.png"),
			load("/graphic/effect_3.png"),
			load("/graphic/effect_2.png"),
			load("/graphic/effect_1.png")
	};
//	public static final Image SPAWN = load("/graphic/spawn.png");
}
