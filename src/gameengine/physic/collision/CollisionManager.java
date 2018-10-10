package gameengine.physic.collision;

import java.util.ArrayList;
import java.util.List;

import gameengine.entities.GameObject;
import gameengine.world.GameWorld;

/**
 * Class that handle collision.
 * @author Main
 *
 */
public class CollisionManager {
	private List<HandleCollision> lstHandleCollision = new ArrayList<>();
	private List<Collision> lstCollision = new ArrayList<>();
	private GameWorld gm;
	
	public CollisionManager(GameWorld gm) {
		this.gm = gm;
	}
	
	/**
	 * Manage all collisions (add, manage, delete).
	 * @param lstActualCollision A List<Collision>
	 */
	private void manageCollision(List<Collision> lstActualCollision) {
		for (Collision collision : lstCollision) {
			//handle existing collisions.
			if(lstActualCollision.contains(collision)) {
				for (HandleCollision handleCollision : lstHandleCollision) {
					if(confirmTypeGameObject(handleCollision, collision)) {
						handleCollision.onCollision(collision.getGameObject1(), collision.getGameObject2());
					}
				}
			} else {
				//handle collisions that don't existe anymore.
				for(HandleCollision hc : lstHandleCollision) {
					if(confirmTypeGameObject(hc, collision)) {
						hc.onCollisionEnd(collision.getGameObject1(), collision.getGameObject2());
					}
				}
			}
		}
		///handle new collisions.
		lstCollision.removeIf(c -> !lstActualCollision.contains(c));
		for (Collision collision : lstActualCollision) {
			if(!lstCollision.contains(collision)) {
				for(HandleCollision hc : lstHandleCollision) {
					if(confirmTypeGameObject(hc, collision)) {
						hc.onCollisionBegin(collision.getGameObject1(), collision.getGameObject2());
					}
				}
				lstCollision.add(collision);
			}
		}
	}
	/**
	 * Verifying if the two object that colide are the object in the HandleColission.
	 * @param gc The HAndleCollision.
	 * @param c The Collision.
	 * @return true if the object are what we expeted, false otherwise.
	 */
	public boolean confirmTypeGameObject(HandleCollision gc , Collision c) {
		if( c.getGameObject1().getType().equals(gc.getTypeGameObject1()) || c.getGameObject2().getType().equals(gc.getTypeGameObject1()) &&
			c.getGameObject2().getType().equals(gc.getTypeGameObject2()) || c.getGameObject2().getType().equals(gc.getTypeGameObject2())) {
			return true;
		}
		return false;
	}
	public void addHandleCollision(HandleCollision hc) {
		lstHandleCollision.add(hc);
	}
	public void update() {
		List<GameObject> lstGa = gm.getLevel().getLstGameObject();
		List<Collision> lstActualCollision = new ArrayList<>();
		for (GameObject gameObject1 : lstGa) {
			for (GameObject gameObject2 : lstGa) {
				if(gameObject1 != gameObject2 && gameObject1.getHitbox() != null && gameObject2.getHitbox() != null && gameObject1.getzIndex() >0 && gameObject2.getzIndex()>0) {
					if(gameObject1.getHitbox().isCollide(gameObject2.getHitbox())) {
						Collision c =  new Collision(gameObject1, gameObject2);
						lstActualCollision.add(c);
					}
				}
			}
		}
		manageCollision(lstActualCollision);
	}
}
