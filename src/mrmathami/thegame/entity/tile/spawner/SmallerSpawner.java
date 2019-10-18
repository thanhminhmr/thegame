package mrmathami.thegame.entity.tile.spawner;

import mrmathami.thegame.Config;
import mrmathami.thegame.entity.enemy.SmallerEnemy;

import javax.annotation.Nonnull;

public class SmallerSpawner extends AbstractSpawner<SmallerEnemy> {

    public SmallerSpawner(long createdTick, long posX, long posY, long width, long height, long spawnInterval, long initialDelay, long numOfSpawn) {
        super(createdTick, posX, posY, width, height, Config.SMALLER_ENEMY_SIZE, SmallerEnemy.class, spawnInterval, initialDelay, numOfSpawn);
    }

    @Nonnull
    @Override
    protected SmallerEnemy doSpawn(long createdTick, double posX, double posY) {
        return new SmallerEnemy(createdTick, posX, posY);
    }
}
