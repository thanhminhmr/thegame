package mrmathami.thegame.field;

/**
 * Abstract class for game entity.
 */
public abstract class AbstractEntity implements GameEntity {
	private final int createdTick;
	private float posX;
	private float posY;
	private float width;
	private float height;

	protected AbstractEntity(int createdTick, float posX, float posY, float width, float height) {
		this.createdTick = createdTick;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}

	@Override
	public final int getCreatedTick() {
		return createdTick;
	}

	@Override
	public final float getPosX() {
		return posX;
	}

	/**
	 * Set entity field pos x. Should only be called in doUpdate of UpdatableEntity
	 * @param posX field pos x
	 */
	protected final void setPosX(float posX) {
		this.posX = posX;
	}

	@Override
	public final float getPosY() {
		return posY;
	}

	/**
	 * Set entity field pos y. Should only be called in doUpdate of UpdatableEntity
	 * @param posY field pos y
	 */
	protected final void setPosY(float posY) {
		this.posY = posY;
	}

	@Override
	public final float getWidth() {
		return width;
	}

	/**
	 * Set entity field width. Should only be called in doUpdate of UpdatableEntity
	 * @param width field width
	 */
	protected final void setWidth(float width) {
		this.width = width;
	}

	@Override
	public final float getHeight() {
		return height;
	}

	/**
	 * Set entity field height. Should only be called in doUpdate of UpdatableEntity
	 * @param height field height
	 */
	protected final void setHeight(float height) {
		this.height = height;
	}

	@Override
	public final boolean isBeingContained(float posX, float posY, float width, float height) {
		return posX <= this.posX
				&& posY <= this.posY
				&& posX + width >= this.posX + this.width
				&& posY + height >= this.posY + this.height;
	}

	@Override
	public final boolean isBeingOverlapped(float posX, float posY, float width, float height) {
		return posX < this.posX + this.width
				&& posY < this.posY + this.height
				&& posX + width > this.posX
				&& posY + height > this.posY;
	}
}
