//package mrmathami.thegame.game.entity;
//
//import mrmathami.thegame.game.GameField;
//
//import javax.annotation.Nonnull;
//import java.util.List;
//
//public class Bullet extends Entity implements Tickable, Healthy, Destroyable {
//	private final float deltaX;
//	private final float deltaY;
//
//	public Bullet(int createdTick, int posX, int posY, float deltaX, float deltaY) {
//		super(createdTick, posX, posY, 1.0f, 1.0f);
//		this.deltaX = deltaX;
//		this.deltaY = deltaY;
//	}
//
//	@Override
//	public void onTick(@Nonnull GameField field, int tickCount) {
//		final float newX = getPosX() + deltaX;
//		final float newY = getPosY() + deltaY;
//		final List<Entity> selectedEntities = field.getSelectedEntities(newX, newY, getWidth(), getHeight());
//		selectedEntities.sort(Destroyable::destroyableEntityComparator);
//		for (final Entity entity : selectedEntities) {
//			if (entity instanceof Destroyable) {
//				((Destroyable) entity).doHurt(getHealth());
//				this.doKill();
//				break;
//			}
//		}
//	}
//}
