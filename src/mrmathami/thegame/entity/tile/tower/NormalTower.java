package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedAudio;
import mrmathami.thegame.entity.bullet.NormalBullet;

import javax.annotation.Nonnull;

public final class NormalTower extends AbstractTower<NormalBullet> {
	public NormalTower(long createdTick, long posX, long posY) {
		super(createdTick, posX, posY, Config.NORMAL_TOWER_RANGE, Config.NORMAL_TOWER_SPEED);
//		double tmp = 2.0 * Config.GAME_TPS / Config.NORMAL_TOWER_SPEED;
//		audioClip.setVolume(0.05);
	}
//	private AudioClip audioClip = LoadedAudio.getBulletSound();
	@Nonnull
	@Override
	protected final NormalBullet doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY) {
		if (Config.sfx) LoadedAudio.normal().play();
		return new NormalBullet(createdTick, posX, posY, deltaX, deltaY);
	}
}
