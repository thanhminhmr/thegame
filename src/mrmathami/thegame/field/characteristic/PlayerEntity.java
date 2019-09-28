package mrmathami.thegame.field.characteristic;

import mrmathami.thegame.field.GameEntity;

public interface PlayerEntity extends GameEntity {
	void setKey(int key, boolean isDown);
}
