package mrmathami.thegame.field.tile;

import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;

import javax.annotation.Nonnull;

public final class Bomb extends AbstractTile implements UpdatableEntity, LivingEntity, DestroyableEntity.DestroyListener {
	private final float strength;
	private final int length;
	private int tickDown;
	private float health;

	public Bomb(int createdTick, int posX, int posY, float strength, int length, int tickDown, float health) {
		super(createdTick, posX, posY, 1, 1);
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
		field.doSpawn(new Flare(tickCount, Math.round(getPosX()), Math.round(getPosY()), strength, length, Flare.DIRECTION_ALL));
	}
}
