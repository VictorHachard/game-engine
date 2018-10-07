package gameengine.physic.collision;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handle collision.
 * @author Main
 *
 */
public class CollisionManager {
	private List<HandleCollision> lstHandleCollision = new ArrayList<>();
	private List<Collision> lstCollision = new ArrayList<>();
	
	public void manageCollision(List<Collision> lstActualCollision) {
		for (Collision collision : lstCollision) {
			if(lstActualCollision.contains(collision)) { // on manage les collisions déjà existante.
				for (HandleCollision handleCollision : lstHandleCollision) {
					if(confirmTypeGameObject(handleCollision, collision)) { // collision = "personnage" - "monstre" ==> "personnage" - "monstre"
						handleCollision.onCollision(collision.getGameObject1(), collision.getGameObject2());
					}
				}
			}else {
				for(HandleCollision hc : lstHandleCollision) { // on manage les collisions qui n'existe plus.
					if(confirmTypeGameObject(hc, collision)) {
						hc.onCollisionEnd(collision.getGameObject1(), collision.getGameObject2());
					}
				}
			}
		}
		lstCollision.removeIf(c -> !lstActualCollision.contains(c));
		for (Collision collision : lstActualCollision) {
			if(!lstCollision.contains(collision)) {
				for(HandleCollision hc : lstHandleCollision) { // on manage les nouvelles collisions.
					if(confirmTypeGameObject(hc, collision)) {
						hc.onCollisionBegin(collision.getGameObject1(), collision.getGameObject2());
					}
				}
				lstCollision.add(collision);
			}
		}
	}
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
}
