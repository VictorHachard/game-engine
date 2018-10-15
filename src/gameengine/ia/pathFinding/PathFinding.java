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
		for (double i = 0; i < gw.getLevel().getY(); i++) { //x
			for (double j = 0; j < gw.getLevel().getX(); j++) { //y
				lstAllCells[(int)i][(int)j] = new Cell((Math.abs(ga.getPosition().getX() - goal.getX()) + Math.abs(ga.getPosition().getY() - goal.getY()))
						,isValide(gameObject,j,i)
						,(int)i,(int)j);
				
			}
		}
		List<Cell> lstGoodPath = estimatePath(goal);
		System.out.println(lstGoodPath.size());
		for (Cell tt : lstGoodPath) {
			System.out.println(tt.toString());
		}
	}
	private List<Cell> estimatePath(Point2D goal) {
		
		//Départ
		Point2D depart = ga.getPosition().copy();
		AABB box = ga.getHitbox();
		Cell currentCell= lstAllCells[(int) Math.round(depart.getY())][(int) Math.round(depart.getX())];
		Cell goalCell = lstAllCells[(int) Math.round(goal.getY())][(int) Math.round(goal.getX())];
		currentCell.setG(0.0);
		lstOpen.add(currentCell);
		while(!lstOpen.isEmpty()) {
			List<Cell> temp = findAdjacent(currentCell);
			for (Cell cell : temp) {
				if(isDestination(cell, goalCell)) {
					List<Cell> lstGoodPath = new ArrayList<>();
					lstGoodPath.add(cell);
					return construcGoodPath(cell,lstGoodPath);
				}
				cell.setH(findH(cell,goalCell));
				findG(cell);
			}
			if(temp.isEmpty()) {
				currentCell = currentCell.getParent();
			} else {
				lstOpen.addAll(temp);
				// on compare les f;
				double minF = 100000000;
				Cell cellWithMinF = temp.get(0);
				for (Cell cell : temp) {
					if(minF >cell.getF()) {
						minF = cell.getF();
						cellWithMinF = cell;
					}
				}
				lstClose.add(currentCell);
				lstOpen.remove(currentCell);
				currentCell = cellWithMinF;
			}
			System.out.println(currentCell.toString2());
			dessinerMap(goalCell);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} return null;
	}
	
	
	public void dessinerMap(Cell goal) {
		System.out.println("liste open size ="+this.lstOpen.size());
		System.out.println(this.lstOpen.toString());
		System.out.println("liste close size ="+this.lstClose.size());
		System.out.println(this.lstClose.toString());
		
		for (Cell[] cells : lstAllCells) {
			for (Cell cell : cells) {
				String s = "";
				if(cell.getCelluleBloquante())
					s = "|";
				else {
					s = "-";
				}
				if(lstClose.contains(cell)) {
					s = "C";
				}
				if(cell == goal) {
					s ="D";
				}
				System.out.print(s);
			}
			System.out.println();
		}
	}
	
	private List<Cell> construcGoodPath(Cell c, List<Cell> cells) {
		if(c.getParent() !=null)
			cells.add(c.getParent());
		if(c.getParent() != null) {
			construcGoodPath(c.getParent(),cells);
		}else {
			List<Cell> temp = new ArrayList<>();
			for (int i = cells.size()-1; i >= 0 ; i--) {
				System.out.println("c"+cells.get(i));
				temp.add(cells.get(i));
			}
			return temp;
		}
		return cells;
	}
	private List<Cell> findAdjacent(Cell c){
		System.out.println();
		int x = c.getX();
		int y = c.getY();
		System.out.println("sdfsdfsdfsdfsdfds" + x + " " + y+"  -- " +lstAllCells[x].length+ "" + gw.getLevel().getX()) ;
		List<Cell> lst = new ArrayList<>();
		if((x+1<gw.getLevel().getY()) &&
				
				!lstAllCells[x+1][y].getCelluleBloquante() &&
				!lstClose.contains(lstAllCells[x+1][y]))
			lst.add(lstAllCells[x+1][y]);
		if((x-1>=0) && !lstAllCells[x-1][y].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y]) )
			lst.add(lstAllCells[x-1][y]);
		if((x+1<gw.getLevel().getY()) && (y+1<gw.getLevel().getX()) && !lstAllCells[x+1][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x+1][y+1])) {
			lst.add(lstAllCells[x+1][y+1]);
			lstAllCells[x+1][y+1].setIsDiagonal(true);
		}

		if((x+1<gw.getLevel().getY())&& (y-1>=0) && !lstAllCells[x+1][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x+1][y-1]) )	{
			lst.add(lstAllCells[x+1][y-1]);
			lstAllCells[x+1][y-1].setIsDiagonal(true);
		}
			
		if((x-1>=0) && (y-1>=0) && !lstAllCells[x-1][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y-1]))	{
			lst.add(lstAllCells[x-1][y-1]);
			lstAllCells[x-1][y-1].setIsDiagonal(true);
		}
			
		if((x-1>=0) && (y+1<gw.getLevel().getX()) && !lstAllCells[x-1][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y+1]))	{
			lst.add(lstAllCells[x-1][y+1]);
			lstAllCells[x-1][y+1].setIsDiagonal(true);
		}

		if((y-1>=0) && !lstAllCells[x][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x][y-1]))
			lst.add(lstAllCells[x][y-1]);
		if((y+1<gw.getLevel().getX()) && !lstAllCells[x][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x][y+1]) )
			lst.add(lstAllCells[x][y+1]);	
		for (Cell cell : lst) {
			cell.setParent(c);
		}
		return lst;
	}
	
	private int findH(Cell targetCell,Cell goalCell) {
		return Math.abs(goalCell.getX() - targetCell.getX()) + Math.abs(goalCell.getY() - targetCell.getY());
	}
	
	
	private void findG(Cell cell) {
		
		double g = 0;
		if(cell.getIsDiagonal())
			g = 14;
		else
			g = 10;
		
		if(cell.getParent() != null && cell.getParent().getParent() != null && lstOpen.contains(cell)) {
			double gParentDeParent = findGParentOfParent(cell);
			double gParent = cell.getParent().getG() + g;
			if(gParentDeParent < gParent) {
				cell.setParent(cell.getParent().getParent());
				cell.setG(gParentDeParent);
			}else {
				cell.setG(gParent);
			}
		}else {
			System.out.println("ici"+g);
			cell.setG(cell.getParent().getG()+g);
			System.out.println(cell.getG());
		}
	}
	
	private double findGParentOfParent(Cell c) {
		Cell parent = c.getParent().getParent();
		int delta = Math.abs(parent.getX()-c.getX()) + Math.abs(parent.getY() - c.getY());
		if(delta > 2 ) {
			return parent.getG()+14;
		}else {
			return parent.getG()+10;
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
	private boolean isDestination(Cell destination, Cell actual) {
		return destination.equals(actual);
	}
}
