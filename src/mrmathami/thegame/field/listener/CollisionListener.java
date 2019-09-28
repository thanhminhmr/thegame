package mrmathami.thegame.field.listener;

import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.CollidableEntity;

import javax.annotation.Nonnull;

public interface CollisionListener extends CollidableEntity {
	void onCollision(@Nonnull GameField field, int tickCount, CollidableEntity collidableEntity);
}
