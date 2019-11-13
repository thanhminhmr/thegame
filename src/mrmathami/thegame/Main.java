package mrmathami.thegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
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
		pane.setPrefWidth(Config.SCREEN_WIDTH + 100);
		pane.setPrefHeight(Config.SCREEN_HEIGHT);
		AudioClip audioClip = LoadedAudio.BACKGROUND_MUSIC;
		audioClip.setVolume(0.05);
		audioClip.setCycleCount(AudioClip.INDEFINITE);
		audioClip.play();
		ImageView gameTitle = new ImageView();
		Button newGame = new Button("New Game");
		newGame.setOnMouseClicked(e -> newGame(primaryStage));
		Button lastGame = new Button("Last Game");
		lastGame.setOnAction(e -> reload(primaryStage));
		VBox vBox = new VBox(gameTitle, newGame, lastGame);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);

		pane.getChildren().add(vBox);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}

	private void newGame(Stage stage) {
		StackPane pane = new StackPane();
		Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		GameController gameController = new GameController(graphicsContext);
		canvas.setOnMouseClicked(gameController::mouseClickHandler);
		HBox moneyLine = new HBox(gameController.getCredit(), new ImageView(LoadedImage.$$$));
		moneyLine.setAlignment(Pos.CENTER);

		ImageView normalTower = new ImageView(LoadedImage.NORMAL_TOWER);
		normalTower.setFitWidth(40);
		normalTower.setFitHeight(40);
		normalTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.NORMAL_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.NORMAL_TOWER);
		});
		final Text normalTowerPrice = new Text(String.valueOf(Config.NORMAL_TOWER_PRICE));
		normalTowerPrice.setFont(Config.TEXT_FONT);
		HBox normalTowerLine = new HBox(normalTower, normalTowerPrice);
		normalTowerLine.setAlignment(Pos.CENTER);

		ImageView sniperTower = new ImageView(LoadedImage.SNIPER_TOWER);
		sniperTower.setFitWidth(40);
		sniperTower.setFitHeight(40);
		sniperTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.SNIPER_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.SNIPER_TOWER);
		});

		ImageView machineGunTower = new ImageView(LoadedImage.MACHINE_GUN_TOWER);
		machineGunTower.setFitWidth(40);
		machineGunTower.setFitHeight(40);
		machineGunTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.MACHINE_GUN_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.MACHINE_GUN_TOWER);
		});

		Button sell = new Button("Sell");
		sell.setOnAction(e -> gameController.setKeyStatus(Config.KEY_STATUS.SELL));
		Button pause = new Button("Pause");
		pause.setOnAction(e -> {
			if (pause.getText().equals("Pause")) {
				gameController.setStatus(Config.GAME_STATUS.PAUSE);
				pause.setText("Continue");
			} else {
				gameController.setStatus(Config.GAME_STATUS.NONE);
				pause.setText("Pause");
			}
		});
		VBox infoBox = new VBox(moneyLine, normalTowerLine, machineGunTower, sniperTower, sell, pause);
		infoBox.setAlignment(Pos.CENTER);
		SplitPane splitPane = new SplitPane(canvas, infoBox);
		splitPane.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		pane.getChildren().add(splitPane);
		gameController.start();
		stage.setOnCloseRequest(gameController::closeRequestHandler);
		stage.setScene(new Scene(pane));
	}

	private void reload(Stage stage) {
		StackPane pane = new StackPane();
		Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		GameField loadedField = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(Config.logPath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			loadedField = (GameField) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception ignore) {}
		GameController gameController = (loadedField == null) ? new GameController(graphicsContext) : new GameController(graphicsContext, loadedField);

		canvas.setOnMouseClicked(gameController::mouseClickHandler);
		HBox moneyLine = new HBox(gameController.getCredit(), new ImageView(LoadedImage.$$$));
		moneyLine.setAlignment(Pos.CENTER);

		ImageView normalTower = new ImageView(LoadedImage.NORMAL_TOWER);
		normalTower.setFitWidth(40);
		normalTower.setFitHeight(40);
		normalTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.NORMAL_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.NORMAL_TOWER);
		});
		final Text normalTowerPrice = new Text(String.valueOf(Config.NORMAL_TOWER_PRICE));
		normalTowerPrice.setFont(Config.TEXT_FONT);
		HBox normalTowerLine = new HBox(normalTower, normalTowerPrice);
		normalTowerLine.setAlignment(Pos.CENTER);

		ImageView sniperTower = new ImageView(LoadedImage.SNIPER_TOWER);
		sniperTower.setFitWidth(40);
		sniperTower.setFitHeight(40);
		sniperTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.SNIPER_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.SNIPER_TOWER);
		});

		ImageView machineGunTower = new ImageView(LoadedImage.MACHINE_GUN_TOWER);
		machineGunTower.setFitWidth(40);
		machineGunTower.setFitHeight(40);
		machineGunTower.setOnMouseClicked(e -> {
			pane.setCursor(new ImageCursor(LoadedImage.MACHINE_GUN_TOWER));
			gameController.setKeyStatus(Config.KEY_STATUS.MACHINE_GUN_TOWER);
		});

		Button sell = new Button("Sell");
		sell.setOnAction(e -> gameController.setKeyStatus(Config.KEY_STATUS.SELL));
		Button pause = new Button("Pause");
		pause.setOnAction(e -> {
			if (pause.getText().equals("Pause")) {
				gameController.setStatus(Config.GAME_STATUS.PAUSE);
				pause.setText("Continue");
			} else {
				gameController.setStatus(Config.GAME_STATUS.NONE);
				pause.setText("Pause");
			}
		});
		VBox infoBox = new VBox(moneyLine, normalTowerLine, machineGunTower, sniperTower, sell, pause);
		infoBox.setAlignment(Pos.CENTER);
		SplitPane splitPane = new SplitPane(canvas, infoBox);
		splitPane.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		pane.getChildren().add(splitPane);
		gameController.start();
		stage.setOnCloseRequest(gameController::closeRequestHandler);
		stage.setScene(new Scene(pane));
	}
}