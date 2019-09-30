package mrmathami.thegame.field.characteristic;

import mrmathami.thegame.field.GameEntity;

public interface PlayerEntity extends GameEntity {
	void onKeyDown(int key);

	void onKeyUp(int key);
}
