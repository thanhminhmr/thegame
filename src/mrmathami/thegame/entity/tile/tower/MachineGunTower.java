package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedAudio;
import mrmathami.thegame.entity.bullet.MachineGunBullet;

import javax.annotation.Nonnull;

public final class MachineGunTower extends AbstractTower<MachineGunBullet> {
	public MachineGunTower(long createdTick, long posX, long posY) {
		super(createdTick, posX, posY, Config.MACHINE_GUN_TOWER_RANGE, Config.MACHINE_GUN_TOWER_SPEED);
//		audioClip.setRate(3.0 * Config.GAME_TPS / Config.MACHINE_GUN_TOWER_SPEED);
//		audioClip.setVolume(0.025);
	}
//	private AudioClip audioClip = LoadedAudio.getBulletSound();

	@Nonnull
	@Override
	protected final MachineGunBullet doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY) {
		if (Config.sfx) LoadedAudio.machineGun().play();
		return new MachineGunBullet(createdTick, posX, posY, deltaX, deltaY);
	}
}
