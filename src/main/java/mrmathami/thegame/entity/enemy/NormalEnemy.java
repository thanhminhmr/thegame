package mrmathami.thegame.entity.enemy;

import mrmathami.thegame.Config;

public final class NormalEnemy extends AbstractEnemy {
	public NormalEnemy(long createdTick, double posX, double posY) {
		super(createdTick, posX, posY,  Config.NORMAL_ENEMY_SIZE, Config.NORMAL_ENEMY_HEALTH, Config.NORMAL_ENEMY_ARMOR, Config.NORMAL_ENEMY_SPEED, Config.NORMAL_ENEMY_REWARD);
	}
}
