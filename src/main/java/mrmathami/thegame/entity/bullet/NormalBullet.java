package mrmathami.thegame.entity.bullet;

import mrmathami.thegame.Config;

public final class NormalBullet extends AbstractBullet {
	public NormalBullet(long createdTick, double posX, double posY, double deltaX, double deltaY) {
		super(createdTick, posX, posY, deltaX, deltaY, Config.NORMAL_BULLET_SPEED, Config.NORMAL_BULLET_STRENGTH, Config.NORMAL_BULLET_TTL);
	}
}
