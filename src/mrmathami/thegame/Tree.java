package mrmathami.thegame;

import mrmathami.thegame.entity.AbstractEntity;

public class Tree extends AbstractEntity {
    protected Tree(long createdTick, double posX, double posY) {
        super(createdTick, posX, posY,1L, 1L);
    }
}
