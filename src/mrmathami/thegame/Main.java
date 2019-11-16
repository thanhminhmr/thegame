package mrmathami.thegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		StackPane pane = new StackPane();
		pane.setPrefWidth(Config.SCREEN_WIDTH + 60);
		pane.setPrefHeight(Config.SCREEN_HEIGHT);
		pane.setBackground(LoadedImage.BACKGROUND);

		LoadedAudio.BACKGROUND_MUSIC.play();

		Button newGame = new Button("New Game");
		newGame.setOnAction(e -> newGame(primaryStage));
		Button lastGame = new Button("Last Game");
		lastGame.setOnAction(e -> reload(primaryStage));

		VBox vBox = new VBox(newGame, lastGame);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);

		pane.getChildren().add(vBox);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
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
		HBox moneyLine = new HBox(LoadedImage.imageView(LoadedImage.$$$, 30, 30), gameController.getCredit());
		moneyLine.setAlignment(Pos.CENTER_LEFT);

		var normalTowerLine = towerLine(LoadedImage.NORMAL_TOWER, Config.KEY_STATUS.NORMAL_TOWER, Config.NORMAL_TOWER_PRICE, gameController);
		var sniperTowerLine = towerLine(LoadedImage.SNIPER_TOWER, Config.KEY_STATUS.SNIPER_TOWER, Config.SNIPER_TOWER_PRICE, gameController);
		var machineGunTowerLine = towerLine(LoadedImage.MACHINE_GUN_TOWER, Config.KEY_STATUS.MACHINE_GUN_TOWER, Config.MACHINE_GUN_TOWER_PRICE, gameController);
		Button sell = new Button("Sell");
		sell.setOnAction(e -> gameController.setKey(Config.KEY_STATUS.SELL, new ImageCursor(LoadedImage.$$$)));
		VBox shop = new VBox(normalTowerLine, sniperTowerLine, machineGunTowerLine, sell);
		shop.setAlignment(Pos.TOP_CENTER);
		shop.setSpacing(10);
//		shop.setBackground(LoadedImage.focusBackground);

		Button pause = new Button("Pause");
		pause.setOnAction(e -> {
			switch (gameController.getStatus()) {
				case NONE:
					gameController.setStatus(Config.GAME_STATUS.PAUSE);
					pause.setText("Continue");
					break;
				case PAUSE:
					gameController.setStatus(Config.GAME_STATUS.NONE);
					pause.setText("Pause");
					break;
				default: break;
			}
		});

		Button sfx = new Button(String.format("SFX: %s", (Config.sfx) ? "On" : "Off"));
		sfx.setOnAction(e -> {
			Config.sfx = !Config.sfx;
			sfx.setText(String.format("SFX: %s", (Config.sfx) ? "On" : "Off"));
		});

		Button autoPlay = new Button(String.format("AP: %s", (Config.autoPlay) ? "On" : "Off"));
		autoPlay.setOnAction(e -> {
			Config.autoPlay = !Config.autoPlay;
			autoPlay.setText(String.format("AP: %s", (Config.autoPlay) ? "On" : "Off"));
		});

		Button music = new Button(String.format("Ms: %s", (Config.music) ? "On" : "Off"));
		music.setOnAction(event -> {
			Config.music = !Config.music;
			music.setText(String.format("Ms: %s", (Config.music) ? "On" : "Off"));
			if (Config.music) LoadedAudio.BACKGROUND_MUSIC.play();
			else LoadedAudio.BACKGROUND_MUSIC.stop();
		});

		Button restart = new Button("Restart");
		restart.setOnAction(e -> {
			gameController.stop();
			newGame(stage);
		});

		pause.setPrefWidth(65);
		sfx.setPrefWidth(65);
		music.setPrefWidth(65);
		autoPlay.setPrefWidth(65);
		restart.setPrefWidth(65);
		VBox option = new VBox(pause, sfx, music, autoPlay, restart);
		option.setAlignment(Pos.BOTTOM_LEFT);
		option.setSpacing(10);

		VBox infoBox = new VBox(moneyLine, shop, option);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.setSpacing(50);
		infoBox.setFillWidth(true);
		HBox hBox = new HBox(canvas, infoBox);
		hBox.setBackground(LoadedImage.focusBackground);
		gameController.start();
		stage.setOnCloseRequest(gameController::closeRequestHandler);
		stage.setScene(new Scene(hBox));
	}

	private static VBox towerLine(Image towerImage, Config.KEY_STATUS keyStatus, long towerPrice, GameController controller){
		ImageView tower = LoadedImage.imageView(towerImage, 35, 35);
		tower.setOnMouseClicked(e -> controller.setKey(keyStatus, new ImageCursor(towerImage)));
		final Text price = new Text(String.valueOf(towerPrice));
		price.setFont(Font.font(15));
		VBox vBox = new VBox(tower, price);
		vBox.setAlignment(Pos.CENTER);
		return vBox;
	}
}