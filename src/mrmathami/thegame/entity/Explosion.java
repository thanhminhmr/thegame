package mrmathami.thegame.entity;

import mrmathami.thegame.Config;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.AbstractEntity;
import mrmathami.thegame.entity.DestroyableEntity;
import mrmathami.thegame.entity.UpdatableEntity;

import javax.annotation.Nonnull;

public class Explosion extends AbstractEntity implements DestroyableEntity, UpdatableEntity {
    public long tickDown = 2 * Config.GAME_TPS;
    public final Class<?> enemyClass;
    public Explosion(long createdTick, AbstractEntity e) {
        super(createdTick, e.getPosX() + e.getWidth() / 2, e.getPosY() + e.getWidth() / 2, e.getWidth(), e.getHeight());
        this.enemyClass = e.getClass();
    }

    @Override
    public void doDestroy() {
        tickDown = 0;
    }

    @Override
    public boolean isDestroyed() {
        return tickDown <= 0;
    }

    @Override
    public void onUpdate(@Nonnull GameField field) {
        tickDown--;
        double k = Math.pow(1.00084566, tickDown);
        this.setWidth(getWidth() * k);
        this.setHeight(getWidth() * k);
    }
}
