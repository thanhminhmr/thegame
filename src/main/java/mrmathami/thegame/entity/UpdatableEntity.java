package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;

import javax.annotation.Nonnull;

public interface UpdatableEntity extends GameEntity {
	void onUpdate(@Nonnull GameField field);
}
