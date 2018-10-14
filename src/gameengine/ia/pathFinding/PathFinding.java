package gameengine.ia.pathFinding;

import java.util.ArrayList;
import java.util.List;

import gameengine.entities.GameObject;
import gameengine.physic.Point2D;
import gameengine.physic.collision.aabb.AABB;
import gameengine.world.GameWorld;
import javafx.geometry.Point3D;

public class PathFinding {
	
	Cell[][] lstAllCells;
	
	/**
	 * O O O X O O O
	 * O D O X O O A
	 * O O O O O O
	 */
	
	List<Cell> lstOpen = new ArrayList<>();
	List<Cell> lstClose = new ArrayList<>();

    GameObject ga;
	GameWorld gw;
	
	public PathFinding(GameObject gameObject,Point2D goal,GameWorld gw) {
		this.ga = gameObject;
		this.gw = gw;
		lstAllCells = new Cell[gw.getLevel().getY()][gw.getLevel().getX()];
		System.out.println(gw.getLevel().getY()+" - - "+ gw.getLevel().getX());
		for (double i = 0; i < gw.getLevel().getY(); i++) {
			for (double j = 0; j < gw.getLevel().getX(); j++) {
				lstAllCells[(int)i][(int)j] = new Cell((Math.abs(ga.getPosition().getX() - goal.getX()) + Math.abs(ga.getPosition().getY() - goal.getY())),isValide(gameObject,j,i),(int)j,(int)i);
				
			}
		}
		estimatePath(goal);
	}
	private void estimatePath(Point2D goal) {
		
		//Départ
		Point2D depart = ga.getPosition().copy();
		AABB box = ga.getHitbox();
		Cell cDepart= lstAllCells[(int) Math.round(depart.getY())][(int) Math.round(depart.getX())];
		lstOpen.add(cDepart);
		
		while(!lstOpen.isEmpty()) {
			int i = cDepart.getX();
			int j = cDepart.getY();
			if (lstAllCells[i-1][j].getCelluleBloquante()) 
	        { 
	            if (isDestination(goal,new Point2D((double)i-1,(double) j))) 
	            { 
	                // Set the Parent of the destination cell 
	                cellDetails[i-1][j].parent_i = i; 
	                cellDetails[i-1][j].parent_j = j; 
	                printf ("The destination cell is found\n"); 
	                tracePath (cellDetails, dest); 
	                foundDest = true; 
	                return; 
	            } 
	            // If the successor is already on the closed 
	            // list or if it is blocked, then ignore it. 
	            // Else do the following 
	            else if (lstClose.contains(lstAllCells[i-1][j])) 
	            { 
	                gNew = cellDetails[i][j].g + 1.0; 
	                hNew = calculateHValue (i-1, j, dest); 
	                fNew = gNew + hNew; 
	  
	                // If it isn’t on the open list, add it to 
	                // the open list. Make the current square 
	                // the parent of this square. Record the 
	                // f, g, and h costs of the square cell 
	                //                OR 
	                // If it is on the open list already, check 
	                // to see if this path to that square is better, 
	                // using 'f' cost as the measure. 
	                if (cellDetails[i-1][j].f == FLT_MAX || 
	                        cellDetails[i-1][j].f > fNew) 
	                { 
	                    openList.insert( make_pair(fNew, 
	                                               make_pair(i-1, j))); 
	  
	                    // Update the details of this cell 
	                    cellDetails[i-1][j].f = fNew; 
	                    cellDetails[i-1][j].g = gNew; 
	                    cellDetails[i-1][j].h = hNew; 
	                    cellDetails[i-1][j].parent_i = i; 
	                    cellDetails[i-1][j].parent_j = j; 
	                } 
	            } 
	        } 
			
		}
	}
	
	/**
	 * Verifie si le ga collide avec un ga du gw
	 * @param ga
	 * @return
	 */
	private boolean isValide(GameObject ga,double x,double y) {
		//verifier par hors map
		
		//verifier hitbox

		for (GameObject gas : gw.getLevel().getLstGameObject()) {
			if (gas.getHitbox() != null && gas.getzIndex().equals(ga.getzIndex()) && gas.getPosition().equals(new Point2D(x,y))) {
				return true;
			}
		} 
		return false;
	} 
	private boolean isDestination(Point2D destination, Point2D actual) {
		return destination.equals(actual);
	}
}
