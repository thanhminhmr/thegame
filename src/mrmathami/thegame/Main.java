package mrmathami.thegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Main class. Entry point of the game.
 */
public final class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		StackPane pane = new StackPane();
		pane.setPrefWidth(Config.SCREEN_WIDTH + 200);
		pane.setPrefHeight(Config.SCREEN_HEIGHT);
		var bi = new BackgroundImage(Config.START_SCREEN_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(bi));

		Player player = new Player(pane);

		ImageView gameTitle = new ImageView(Config.GAME_TITLE);
		ImageView newGame = new ImageView(Config.NEW_GAME);
		newGame.setOnMouseClicked(player::newGame);
		ImageView continueLastPlay = new ImageView(Config.CONTINUE);
		VBox vBox = new VBox(gameTitle, newGame, continueLastPlay);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);

		pane.getChildren().add(vBox);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}
}