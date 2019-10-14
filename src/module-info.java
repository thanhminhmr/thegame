module towerdefense {
//	opens mrmathami.towerdefense;
//	exports mrmathami.towerdefense;
	opens mrmathami.thegame;
	exports mrmathami.thegame;
	exports mrmathami.thegame.drawer;
	exports mrmathami.thegame.entity;
	exports mrmathami.thegame.entity.tile;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires jsr305;
}