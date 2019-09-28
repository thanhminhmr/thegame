module towerdefense {
//	opens mrmathami.towerdefense;
//	exports mrmathami.towerdefense;
	opens mrmathami.thegame;
	exports mrmathami.thegame;
	exports mrmathami.thegame.field;
	exports mrmathami.thegame.field.tile;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires jsr305;
}