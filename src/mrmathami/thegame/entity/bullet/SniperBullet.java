package mrmathami.thegame.entity.bullet;

import mrmathami.thegame.Config;

public final class SniperBullet extends AbstractBullet {

    public SniperBullet(long createdTick, double posX, double posY, double deltaX, double deltaY) {
        super(createdTick, posX, posY, deltaX, deltaY, Config.SNIPER_BULLET_SPEED, Config.SNIPER_BULLET_STRENGTH, Config.SNIPER_BULLET_TTL);
    }
}
