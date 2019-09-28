package mrmathami.thegame.field.characteristic;

import mrmathami.thegame.field.GameEntity;

/**
 * Marker interface.
 * Mark objects that can collide with other collidable objects.
 */
public interface CollidableEntity extends GameEntity {
	/**
	 * @param entityClass
	 * @param <E>
	 * @return
	 */
	<E extends CollidableEntity> boolean canCollide(Class<E> entityClass);
}
