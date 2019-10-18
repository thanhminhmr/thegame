package mrmathami.thegame.entity.tile.spawner;

import mrmathami.thegame.Config;
import mrmathami.thegame.entity.enemy.TankerEnemy;

import javax.annotation.Nonnull;

public class TankerSpawner extends AbstractSpawner<TankerEnemy> {

    public TankerSpawner(long createdTick, long posX, long posY, long width, long height, long spawnInterval, long initialDelay, long numOfSpawn) {
        super(createdTick, posX, posY, width, height, Config.TANKER_ENEMY_SIZE, TankerEnemy.class, spawnInterval, initialDelay, numOfSpawn);
    }

    @Nonnull
    @Override
    protected TankerEnemy doSpawn(long createdTick, double posX, double posY) {
        return new TankerEnemy(createdTick, posX, posY);
    }
}
