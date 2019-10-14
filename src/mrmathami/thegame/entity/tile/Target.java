package mrmathami.thegame.entity.tile;

import mrmathami.thegame.entity.LivingEntity;

public final class Target extends AbstractTile implements LivingEntity {
	private long health;

	public Target(long createdTick, long posX, long posY, long width, long height, long health) {
		super(createdTick, posX, posY, width, height);
		this.health = health;
	}

	@Override
	public long getHealth() {
		return health;
	}

	@Override
	public void doEffect(long value) {
		if (health != Long.MIN_VALUE) this.health += value;
	}

	@Override
	public void doDestroy() {
		this.health = Long.MIN_VALUE;
	}

	@Override
	public boolean isDestroyed() {
		return health <= 0L;
	}
}
