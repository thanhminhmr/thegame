package mrmathami.thegame.entity.tile;

import mrmathami.thegame.entity.AbstractEntity;

public class Obstacle extends AbstractEntity {
    public final int index;
    public Obstacle(long createdTick, double posX, double posY, int index) {
        super(createdTick, posX, posY,1L, 1L);
        this.index = index;
    }
}
