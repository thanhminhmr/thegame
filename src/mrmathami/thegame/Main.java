package mrmathami.thegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Main class. Entry point of the game.
 */
public final class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		LoadedAudio.BACKGROUND_MUSIC.play();
		ImageView gameTitle = LoadedImage.imageView(LoadedImage.GAME_TITLE, 800, 150, false);
		ImageView newGame = LoadedImage.imageView(LoadedImage.NEW_GAME, 120, 40, true);
		newGame.setOnMouseClicked(e -> newGame(primaryStage));
		ImageView lastGame = LoadedImage.imageView(LoadedImage.LAST_GAME, 120, 40, true);
		lastGame.setOnMouseClicked(e -> reload(primaryStage));

		VBox vBox = new VBox(gameTitle, newGame, lastGame);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPrefWidth(Config.SCREEN_WIDTH + 50);
		vBox.setPrefHeight(Config.SCREEN_HEIGHT);
		vBox.setBackground(LoadedImage.BACKGROUND);

		Scene scene = new Scene(vBox);
		primaryStage.setScene(scene);
		primaryStage.setTitle(Config.GAME_NAME);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void newGame(Stage stage) {
		renderGameUI(stage, null);
	}

	private void reload(Stage stage) {
		GameField loadedField = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(Config.logPath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			loadedField = (GameField) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception ignore) {}
		renderGameUI(stage, loadedField);
	}

	private void renderGameUI(Stage stage, GameField field) {
		Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		GameController gameController = (field == null) ? new GameController(graphicsContext) : new GameController(graphicsContext, field);
		canvas.setOnMouseClicked(gameController::mouseClickHandler);

		var moneyLine = new HBox(LoadedImage.imageView(LoadedImage.$$$, 15, 30, false), gameController.getCredit());
		moneyLine.setAlignment(Pos.CENTER_LEFT);
		var normalTowerLine = towerLine(LoadedImage.NORMAL_TOWER, Config.KEY_STATUS.NORMAL_TOWER, Config.NORMAL_TOWER_PRICE, gameController);
		var sniperTowerLine = towerLine(LoadedImage.SNIPER_TOWER, Config.KEY_STATUS.SNIPER_TOWER, Config.SNIPER_TOWER_PRICE, gameController);
		var machineGunTowerLine = towerLine(LoadedImage.MACHINE_GUN_TOWER, Config.KEY_STATUS.MACHINE_GUN_TOWER, Config.MACHINE_GUN_TOWER_PRICE, gameController);
		var sell = LoadedImage.imageView(LoadedImage.SELL, 40, 30, true);
		sell.setOnMouseClicked(e -> gameController.setKey(Config.KEY_STATUS.SELL, new ImageCursor(LoadedImage.$$$)));
		ImageView shopTitle = LoadedImage.imageView(LoadedImage.SHOP, 50, 15, false);
		VBox shop = new VBox(moneyLine, shopTitle,normalTowerLine, sniperTowerLine, machineGunTowerLine, sell);
		shop.setAlignment(Pos.TOP_CENTER);
		shop.setSpacing(3);

		ImageView pause = LoadedImage.imageView(LoadedImage.PAUSE, 40, 40, true);
		pause.setOnMouseClicked(e -> {
			switch (gameController.getStatus()) {
				case NONE:
					gameController.setStatus(Config.GAME_STATUS.PAUSE);
					pause.setImage(LoadedImage.CONTINUE);
					break;
				case PAUSE:
					gameController.setStatus(Config.GAME_STATUS.NONE);
					pause.setImage(LoadedImage.PAUSE);
					break;
				default: break;
			}
		});

		ImageView sfx = LoadedImage.imageView(Config.sfx ? LoadedImage.AUDIO_ON : LoadedImage.AUDIO_OFF, 40, 40, true);
		sfx.setOnMouseClicked(e -> {
			Config.sfx = !Config.sfx;
			sfx.setImage(Config.sfx ? LoadedImage.AUDIO_ON : LoadedImage.AUDIO_OFF);
		});

		ImageView autoPlay = LoadedImage.imageView(Config.autoPlay ? LoadedImage.AUTO_ON : LoadedImage.AUTO_OFF, 40, 40, true);
		autoPlay.setOnMouseClicked(e -> {
			Config.autoPlay = !Config.autoPlay;
			autoPlay.setImage(Config.autoPlay ? LoadedImage.AUTO_ON : LoadedImage.AUTO_OFF);
		});

		ImageView music = LoadedImage.imageView((Config.music)? LoadedImage.MUSIC_ON : LoadedImage.MUSIC_OFF, 40, 35, true);
		music.setOnMouseClicked(event -> {
			Config.music = !Config.music;
			music.setImage((Config.music)? LoadedImage.MUSIC_ON : LoadedImage.MUSIC_OFF);
			if (Config.music) LoadedAudio.BACKGROUND_MUSIC.play();
			else LoadedAudio.BACKGROUND_MUSIC.stop();
		});

		ImageView restart = LoadedImage.imageView(LoadedImage.RESTART, 40, 40, true);
		restart.setOnMouseClicked(e -> {
			gameController.stop();
			newGame(stage);
		});

		ImageView settingTitle = LoadedImage.imageView(LoadedImage.SETTING, 50, 15, false);

		VBox option = new VBox(settingTitle, autoPlay, pause, sfx, music, restart);
		option.setAlignment(Pos.CENTER);
		option.setSpacing(5);

		VBox infoBox = new VBox(shop, option);
		infoBox.setAlignment(Pos.TOP_CENTER);
		infoBox.setSpacing(30);
		infoBox.setFillWidth(true);
		HBox hBox = new HBox(canvas, infoBox);
		hBox.setBackground(LoadedImage.BACKGROUND);
		gameController.start();
		stage.setOnCloseRequest(gameController::closeRequestHandler);
		stage.setScene(new Scene(hBox));
	}

	private static VBox towerLine(Image towerImage, Config.KEY_STATUS keyStatus, long towerPrice, GameController controller){
		ImageView tower = LoadedImage.imageView(towerImage, 40, 40, true);
		tower.setOnMouseClicked(e -> controller.setKey(keyStatus, new ImageCursor(towerImage)));
		final Text price = new Text(String.valueOf(towerPrice));
		price.setFont(Font.font(13));
		price.setFill(Color.YELLOWGREEN);
		VBox vBox = new VBox(tower, price);
		vBox.setAlignment(Pos.CENTER);
		return vBox;
	}
}