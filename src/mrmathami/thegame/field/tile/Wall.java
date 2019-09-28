package mrmathami.thegame.field.tile;

import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.characteristic.CollidableEntity;

public final class Wall extends AbstractEntity implements CollidableEntity {
	public Wall(int createdTick, float posX, float posY, float width, float height) {
		super(createdTick, posX, posY, width, height);
	}

	@Override
	public <E extends CollidableEntity> boolean canCollide(Class<E> entityClass) {
		return true; // can collide with everything
	}
}
