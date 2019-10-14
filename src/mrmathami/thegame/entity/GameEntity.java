package mrmathami.thegame.entity;

/**
 * A game entity
 */
public interface GameEntity {
	/**
	 * @return entity created tick count
	 */
	long getCreatedTick();

	/**
	 * @return field pos x
	 */
	double getPosX();

	/**
	 * @return field pos y
	 */
	double getPosY();

	/**
	 * @return field width
	 */
	double getWidth();

	/**
	 * @return field height
	 */
	double getHeight();

	/**
	 * @return if current entity is containing this rectangle region
	 */
	boolean isContaining(double posX, double posY, double width, double height);

	/**
	 * @return if current entity is being contained in a rectangle region
	 */
	boolean isBeingContained(double posX, double posY, double width, double height);

	/**
	 * @return if current entity is being overlapped in a rectangle region
	 */
	boolean isBeingOverlapped(double posX, double posY, double width, double height);
}
