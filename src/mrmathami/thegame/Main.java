package mrmathami.thegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;

public final class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		final Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		final GameController gameController = new GameController(graphicsContext);

		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(gameController::keyPressHandler);
		canvas.setOnMouseClicked(gameController::mouseClickHandler);
		graphicsContext.setFontSmoothingType(FontSmoothingType.LCD);

		primaryStage.setResizable(false);
		primaryStage.setTitle(Config.GAME_NAME);
		primaryStage.setOnCloseRequest(gameController::closeRequestHandler);
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.show();

		gameController.start();
	}
}