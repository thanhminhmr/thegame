package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;

import javax.annotation.Nonnull;

public interface SpawnListener extends GameEntity {
	void onSpawn(@Nonnull GameField field);
}
