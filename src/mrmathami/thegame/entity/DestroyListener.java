package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;

import javax.annotation.Nonnull;

public interface DestroyListener extends DestroyableEntity {
	void onDestroy(@Nonnull GameField field);
}
