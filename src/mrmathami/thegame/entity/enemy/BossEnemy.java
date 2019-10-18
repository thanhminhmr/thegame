package mrmathami.thegame.entity.enemy;

import mrmathami.thegame.Config;

public class BossEnemy extends AbstractEnemy {
    public BossEnemy(long createdTick, double posX, double posY) {
        super(createdTick, posX, posY,  Config.BOSS_ENEMY_SIZE, Config.BOSS_ENEMY_HEALTH, Config.BOSS_ENEMY_ARMOR, Config.BOSS_ENEMY_SPEED, Config.BOSS_ENEMY_REWARD);
    }
}
