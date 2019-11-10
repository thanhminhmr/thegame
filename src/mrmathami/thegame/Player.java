package mrmathami.thegame;

import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

class Player {
    private final List<Node> children;

    Player(StackPane root) {
        children = root.getChildren();
    }

    void newGame(Object event) {
        children.clear();
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        GameController gameController = new GameController(graphicsContext);
        canvas.setOnMousePressed(gameController::mouseDownHandler);

        Text money = new Text();
        gameController.setDoPerTick(x -> {
            money.setText(String.format("$$$: %d", x.getCredit()));
        });

        Button normalTower = new Button("Normal Tower");
        normalTower.setCursor(new ImageCursor(Config.NORMAL_TOWER_IMAGE));
        normalTower.setOnAction(e -> {
            gameController.setKeyStatus(Config.STATUS.NORMAL_TOWER);
            canvas.getParent().setCursor(new ImageCursor(Config.NORMAL_TOWER_IMAGE));
        });

        Button sniperTower = new Button("Sniper Tower");
        sniperTower.setOnAction(e -> {
            gameController.setKeyStatus(Config.STATUS.SNIPER_TOWER);
        });

        Button machineGunTower = new Button("Machine Gun Tower");
        machineGunTower.setOnAction(e -> {
            gameController.setKeyStatus(Config.STATUS.MACHINE_GUN_TOWER);
        });

        TitledPane titledPane = new TitledPane("Shop", new VBox(normalTower, sniperTower, machineGunTower));
        VBox vBox = new VBox(money, titledPane);
        vBox.setFocusTraversable(true);

        SplitPane splitPane = new SplitPane(canvas, vBox);

        children.add(splitPane);
        gameController.start();
    }
}
