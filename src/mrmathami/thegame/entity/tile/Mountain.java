package mrmathami.thegame.entity.tile;

import mrmathami.thegame.entity.AbstractEntity;

public final class Mountain extends AbstractEntity {
	public final int type;
	public Mountain(long createdTick, long posX, long posY, int type) {
		super(createdTick, posX, posY, 1L, 1L);
		this.type = type;
	}
}
