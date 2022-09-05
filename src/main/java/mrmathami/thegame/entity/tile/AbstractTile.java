package mrmathami.thegame.entity.tile;

import mrmathami.thegame.entity.AbstractEntity;

public abstract class AbstractTile extends AbstractEntity {
	protected AbstractTile(long createdTick, long posX, long posY, long width, long height) {
		super(createdTick, posX, posY, width, height);
	}
}
