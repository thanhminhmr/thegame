package mrmathami.thegame.field.tile;

import mrmathami.thegame.field.AbstractEntity;

public abstract class AbstractTile extends AbstractEntity implements GameTile {
	protected AbstractTile(int createdTick, int posX, int posY, int width, int height) {
		super(createdTick, posX, posY, width, height);
	}
}
