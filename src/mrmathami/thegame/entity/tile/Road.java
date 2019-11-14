package mrmathami.thegame.entity.tile;

public final class Road extends AbstractTile {
	private double distance = Double.NaN;
	private String type = "0";

	public Road(long createdTick, long posX, long posY) {
		super(createdTick, posX, posY, 1L, 1L);
	}
	public Road(long createdTick, long posX, long posY, String type) {
		super(createdTick, posX, posY, 1L, 1L);
		this.type = type;
	}

	public final double getDistance() {
		return distance;
	}

	public final void setDistance(double distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}
}
