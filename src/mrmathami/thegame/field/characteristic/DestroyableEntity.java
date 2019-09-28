package mrmathami.thegame.field.characteristic;

import mrmathami.thegame.field.GameEntity;

/**
 * Marker interface.
 * Mark objects that can be removed from the field one created.
 */
public interface DestroyableEntity extends GameEntity {
	void doDestroy();

	boolean isDestroyed();
}

