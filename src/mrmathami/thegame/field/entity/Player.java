package mrmathami.thegame.field.entity;

import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.CollidableEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;

import javax.annotation.Nonnull;

public final class Player extends AbstractEntity implements UpdatableEntity, CollidableEntity, LivingEntity {
	private float health;
	private int direction;

	protected Player(int createdTick, float posX, float posY, float width, float height) {
		super(createdTick, posX, posY, width, height);
	}

	@Override
	public void doUpdate(@Nonnull GameField field, int tickCount) {

	}

	@Override
	public <E extends CollidableEntity> boolean canCollide(Class<E> entityClass) {
		return false;
	}

	@Override
	public float getHealth() {
		return health;
	}

	@Override
	public void doEffect(float value) {
		this.health += value;
	}

	@Override
	public void doDestroy() {
		this.health = Float.NaN;
	}

	@Override
	public boolean isDestroyed() {
		return Float.isNaN(health) || health <= 0.0f;
	}

}
