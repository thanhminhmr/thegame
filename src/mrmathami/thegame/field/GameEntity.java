package mrmathami.thegame.field;

/**
 * A game entity
 */
public interface GameEntity {
	/**
	 * @return entity created tick count
	 */
	int getCreatedTick();

	/**
	 * @return field pos x
	 */
	float getPosX();

	/**
	 * @return field pos y
	 */
	float getPosY();

	/**
	 * @return field width
	 */
	float getWidth();

	/**
	 * @return field height
	 */
	float getHeight();

	/**
	 * @return if current entity is being contained in a rectangle region
	 */
	boolean isBeingContained(float posX, float posY, float width, float height);

	/**
	 * @return if current entity is being overlapped in a rectangle region
	 */
	boolean isBeingOverlapped(float posX, float posY, float width, float height);
}
