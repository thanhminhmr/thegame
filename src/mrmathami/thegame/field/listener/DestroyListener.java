package mrmathami.thegame.field.listener;

import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.DestroyableEntity;

import javax.annotation.Nonnull;

public interface DestroyListener extends DestroyableEntity {
	void onDestroy(@Nonnull GameField field, int tickCount);
}
