package mrmathami.thegame.field.tile;

import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.GameEntity;

public final class Wall extends AbstractEntity implements GameEntity {
	public Wall(int createdTick, int posX, int posY, int width, int height) {
		super(createdTick, posX, posY, width, height);
	}
}
