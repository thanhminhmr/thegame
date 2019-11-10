package mrmathami.thegame.entity.tile.spawner;

import mrmathami.thegame.Config;
import mrmathami.thegame.entity.enemy.BossEnemy;

import javax.annotation.Nonnull;

public final class BossSpawner extends AbstractSpawner<BossEnemy> {
	public BossSpawner(long createdTick, long posX, long posY, long width, long height, long spawnInterval, long initialDelay, long numOfSpawn) {
		super(createdTick, posX, posY, width, height, Config.BOSS_ENEMY_SIZE, BossEnemy.class, spawnInterval, initialDelay, numOfSpawn);
	}

	@Nonnull
	@Override
	protected final BossEnemy doSpawn(long createdTick, double posX, double posY) {
		return new BossEnemy(createdTick, posX, posY);
	}
}
