package mrmathami.thegame.field.entity;

import mrmathami.thegame.Config;
import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.GameEntities;
import mrmathami.thegame.field.GameEntity;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.PlayerEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;
import mrmathami.thegame.field.tile.Bomb;

import javax.annotation.Nonnull;

public final class Player extends AbstractEntity implements PlayerEntity, UpdatableEntity, LivingEntity, GameEntity {
	public static final int KEY_UP = 1;
	public static final int KEY_DOWN = 1 << 1;
	public static final int KEY_LEFT = 1 << 2;
	public static final int KEY_RIGHT = 1 << 3;
	public static final int KEY_A = 1 << 4;
	public static final int KEY_B = 1 << 5;
	public static final int KEY_X = 1 << 6;
	public static final int KEY_Y = 1 << 7;

	private float health;
	private volatile int keyPressing;

	public Player(int createdTick, float posX, float posY, float width, float height, float health) {
		super(createdTick, posX, posY, width, height);
		this.health = health;
	}

	@Override
	public synchronized void onKeyDown(int key) {
		this.keyPressing |= key;
	}

	@Override
	public synchronized void onKeyUp(int key) {
		this.keyPressing &= ~key;
	}

	private boolean checkCollisionSafe(@Nonnull GameField field, float posX, float posY, float width, float height) {
		for (final GameEntity entity : GameEntities.getOverlappedEntities(field.getEntities(), posX, posY, width, height)) {
			if (GameEntities.canCollide(Player.class, entity.getClass())) return false;
		}
		return true;
	}

	private boolean checkBombSpawnSafe(@Nonnull GameField field, int posX, int posY) {
		for (final GameEntity entity : GameEntities.getOverlappedEntities(field.getEntities(), posX, posY, 1.0f, 1.0f)) {
			if (GameEntities.canCollide(Bomb.class, entity.getClass())) return false;
		}
		return true;
	}

	@Override
	public void doUpdate(@Nonnull GameField field, int tickCount) {
		final int keyPressing = this.keyPressing;

		final int deltaX = (((keyPressing & KEY_RIGHT) != 0) ? 1 : 0) - (((keyPressing & KEY_LEFT) != 0) ? 1 : 0);
		final int deltaY = (((keyPressing & KEY_DOWN) != 0) ? 1 : 0) - (((keyPressing & KEY_UP) != 0) ? 1 : 0);
		if (deltaX != 0 || deltaY != 0) {
			final float posX = getPosX() + deltaX * Config.PLAYER_SPEED;
			final float posY = getPosY() + deltaY * Config.PLAYER_SPEED;
			if (deltaX != 0 && deltaY != 0 && checkCollisionSafe(field, posX, posY, getWidth(), getHeight())) {
				setPosX(posX);
				setPosY(posY);
			}
			if (deltaX != 0 && checkCollisionSafe(field, posX, getPosY(), getWidth(), getHeight())) {
				setPosX(posX);
			}
			if (deltaY != 0 && checkCollisionSafe(field, getPosX(), posY, getWidth(), getHeight())) {
				setPosY(posY);
			}
		}

		if ((keyPressing & (KEY_A | KEY_B | KEY_X | KEY_Y)) != 0) {
			final int posX = Math.round(getPosX());
			final int posY = Math.round(getPosY());
			if (checkBombSpawnSafe(field, posX, posY)) {
				field.doSpawn(new Bomb(tickCount, posX, posY, 1.0f, 5, Config.GAME_TPS * 3, 1.0f));
			}
		}
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
