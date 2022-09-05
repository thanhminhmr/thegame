package mrmathami.thegame.entity.tile.spawner;

import mrmathami.thegame.Config;
import mrmathami.thegame.entity.enemy.NormalEnemy;

import javax.annotation.Nonnull;

public final class NormalSpawner extends AbstractSpawner<NormalEnemy> {
	public NormalSpawner(long createdTick, long posX, long posY, long width, long height, long spawnInterval, long initialDelay, long numOfSpawn) {
		super(createdTick, posX, posY, width, height, Config.NORMAL_ENEMY_SIZE, NormalEnemy.class, spawnInterval, initialDelay, numOfSpawn);
	}

	@Nonnull
	@Override
	protected final NormalEnemy doSpawn(long createdTick, double posX, double posY) {
		return new NormalEnemy(createdTick, posX, posY);
	}
}
