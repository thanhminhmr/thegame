package mrmathami.thegame.field;

public interface GameEntity {
	int getCreatedTick();

	float getPosX();

	float getPosY();

	float getWidth();

	float getHeight();

	boolean isBeingContained(float posX, float posY, float width, float height);

	boolean isBeingOverlapped(float posX, float posY, float width, float height);
}
