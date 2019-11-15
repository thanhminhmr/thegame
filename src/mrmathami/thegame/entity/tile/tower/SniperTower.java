package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedAudio;
import mrmathami.thegame.entity.bullet.SniperBullet;

import javax.annotation.Nonnull;

public final class SniperTower extends AbstractTower<SniperBullet> {
	public SniperTower(long createdTick, long posX, long posY) {
		super(createdTick, posX, posY, Config.SNIPER_TOWER_RANGE, Config.SNIPER_TOWER_SPEED);
	}
//	private AudioClip audioClip = LoadedAudio.getBulletSound();
	@Nonnull
	@Override
	protected final SniperBullet doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY) {
		if (Config.sfx) LoadedAudio.sniper().play();
		return new SniperBullet(createdTick, posX, posY, deltaX, deltaY);
	}
}
