module towerdefense {
//	opens mrmathami.towerdefense;
//	exports mrmathami.towerdefense;
	opens mrmathami.thegame;
	exports mrmathami.thegame;
	exports mrmathami.thegame.drawer;
	exports mrmathami.thegame.drawer.tile;
	exports mrmathami.thegame.field;
	exports mrmathami.thegame.field.characteristic;
	exports mrmathami.thegame.field.entity;
	exports mrmathami.thegame.field.tile;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires jsr305;
}