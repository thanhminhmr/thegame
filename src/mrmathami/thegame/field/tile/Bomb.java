package mrmathami.thegame.field.tile;

import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.characteristic.CollidableEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;
import mrmathami.thegame.field.listener.DestroyListener;

import javax.annotation.Nonnull;
import java.util.Set;

public final class Bomb extends AbstractEntity implements UpdatableEntity, CollidableEntity, LivingEntity, DestroyListener {
	private static final Set<Class<?>> COLLISION_SET = Set.of(Wall.class);

	private final float strength;
	private final int length;
	private int tickDown;
	private float health;

	public Bomb(int createdTick, float posX, float posY, float strength, int length, int tickDown, float health) {
		super(createdTick, posX, posY, 1.0f, 1.0f);
		this.strength = strength;
		this.length = length;
		this.tickDown = tickDown;
		this.health = health;
	}

	@Override
	public void doUpdate(@Nonnull GameField field, int tickCount) {
		this.tickDown -= 1;
	}

	@Override
	public <E extends CollidableEntity> boolean canCollide(Class<E> entityClass) {
		return COLLISION_SET.contains(entityClass);
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
	public boolean isDestroyed() {
		return Float.isNaN(health) || health <= 0.0f || tickDown <= 0;
	}

	@Override
	public void doDestroy() {
		this.health = Float.NaN;
		this.tickDown = 0; // redundant
	}

	@Override
	public void onDestroy(@Nonnull GameField field, int tickCount) {
		field.doSpawn(new Flare(tickCount, getPosX(), getPosY(), strength, length, Flare.DIRECTION_ALL));
	}
}
