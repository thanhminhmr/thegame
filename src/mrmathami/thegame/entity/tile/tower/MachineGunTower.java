package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.Config;
import mrmathami.thegame.entity.bullet.MachineGunBullet;

import javax.annotation.Nonnull;

public class MachineGunTower extends AbstractTower<MachineGunBullet> {

    protected MachineGunTower(long createdTick, long posX, long posY) {
        super(createdTick, posX, posY, Config.MACHINE_GUN_TOWER_RANGE, Config.MACHINE_GUN_TOWER_SPEED);
    }

    @Nonnull
    @Override
    protected final MachineGunBullet doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY) {
        return new MachineGunBullet(createdTick, posX, posY, deltaX, deltaY);
    }
}
