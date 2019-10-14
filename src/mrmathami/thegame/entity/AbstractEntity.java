package mrmathami.thegame.entity;

/**
 * Abstract class for game entity.
 */
public abstract class AbstractEntity implements GameEntity {
	private final long createdTick;
	private double posX;
	private double posY;
	private double width;
	private double height;

	protected AbstractEntity(long createdTick, double posX, double posY, double width, double height) {
		this.createdTick = createdTick;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}

	@Override
	public final long getCreatedTick() {
		return createdTick;
	}

	@Override
	public final double getPosX() {
		return posX;
	}

	/**
	 * Set entity field pos x. Should only be called in doUpdate of UpdatableEntity
	 * @param posX field pos x
	 */
	protected final void setPosX(double posX) {
		this.posX = posX;
	}

	@Override
	public final double getPosY() {
		return posY;
	}

	/**
	 * Set entity field pos y. Should only be called in doUpdate of UpdatableEntity
	 * @param posY field pos y
	 */
	protected final void setPosY(double posY) {
		this.posY = posY;
	}

	@Override
	public final double getWidth() {
		return width;
	}

	/**
	 * Set entity field width. Should only be called in doUpdate of UpdatableEntity
	 * @param width field width
	 */
	protected final void setWidth(double width) {
		this.width = width;
	}

	@Override
	public final double getHeight() {
		return height;
	}

	/**
	 * Set entity field height. Should only be called in doUpdate of UpdatableEntity
	 * @param height field height
	 */
	protected final void setHeight(double height) {
		this.height = height;
	}

	@Override
	public final boolean isContaining(double posX, double posY, double width, double height) {
		return this.posX <= posX
				&& this.posY <= posY
				&& this.posX + this.width >= posX + width
				&& this.posY + this.height >= posY + height;
	}

	@Override
	public final boolean isBeingContained(double posX, double posY, double width, double height) {
		return posX <= this.posX
				&& posY <= this.posY
				&& posX + width >= this.posX + this.width
				&& posY + height >= this.posY + this.height;
	}

	@Override
	public final boolean isBeingOverlapped(double posX, double posY, double width, double height) {
		return posX < this.posX + this.width
				&& posY < this.posY + this.height
				&& posX + width > this.posX
				&& posY + height > this.posY;
	}
}
