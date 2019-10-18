package mrmathami.thegame.entity.bullet;

import mrmathami.thegame.Config;

public class MachineGunBullet extends AbstractBullet {

    public MachineGunBullet(long createdTick, double posX, double posY, double deltaX, double deltaY) {
        super(createdTick, posX, posY, deltaX, deltaY, Config.MACHINE_GUN_BULLET_SPEED, Config.MACHINE_GUN_BULLET_STRENGTH, Config.MACHINE_GUN_BULLET_TTL);
    }
}
