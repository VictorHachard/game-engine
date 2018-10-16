package gameengine.ia.pathFinding;

import java.util.ArrayList;
import java.util.List;

import gameengine.entities.GameObject;
import gameengine.ia.Goal;
import gameengine.physic.Point2D;
import gameengine.world.GameWorld;

public class PathFinding {
	Cell[][] lstAllCells;	
    GameObject ga;
	GameWorld gw;
	int height;
	int width;
	
	public PathFinding(GameObject gameObject,Point2D goal,GameWorld gw) {
		this.ga = gameObject;
		this.gw = gw;
		height = gw.getLevel().getHeight();
		width = gw.getLevel().getWidth();
		lstAllCells = new Cell[height][width];
		for (double i = 0; i < height; i++) {
			for (double j = 0; j < width; j++) {
				lstAllCells[(int)i][(int)j] =
						new Cell((Math.abs(ga.getPosition().getX() - goal.getX()) + Math.abs(ga.getPosition().getY() - goal.getY()))
						,isCollide(gameObject,j,i)
						,(int)i,(int)j);
			}
		}
		Point2D start = ga.getPosition().copy();
		Cell goalCell = lstAllCells[(int) Math.round(goal.getY())][(int) Math.round(goal.getX())];
		Cell currentCell= lstAllCells[(int) Math.round(start.getY())][(int) Math.round(start.getX())];
		
		List<Cell> closeList = estimatePath(goalCell, currentCell);
		
		List<Cell>lstGoodPath =new ArrayList<>();
		System.out.println(lstGoodPath.size());
		for (Cell tt : lstGoodPath) {
			System.out.println(tt.toString());
		}
		dessinerMap2(goalCell,lstGoodPath);		
	}
	
	/**
	 * 
	 * @param goal
	 * @return
	 */
	private List<Cell> estimatePath(Cell goalCell, Cell currentCell) {
		currentCell.setG(0.0);
		List<Cell>lstOpen = new ArrayList<>();
		List<Cell>lstClose = new ArrayList<>();
		lstOpen.add(currentCell);
		while(!lstOpen.isEmpty()) {
			System.out.println("cell"+currentCell.toString2());
			List<Cell> temp = findAdjacent(currentCell, lstClose);
			for (Cell cell : temp) {
				if(isDestination(cell, goalCell)) {
					lstClose.add(cell);
					return lstClose;
				}
				findH(cell,goalCell);
				findG(cell, lstOpen);
			}
			if(temp.isEmpty()) {
				currentCell = currentCell.getParent();
			} else {
				addOpen(temp, lstOpen);
				double minF = temp.get(0).getF();
				Cell cellWithMinF = temp.get(0);
				for (Cell cell : temp) {
					if(minF >cell.getF()) {
						minF = cell.getF();  
						cellWithMinF = cell;
					} 
				}                 
				for (Cell cell : temp) {
					if(minF == cell.getF() && cell != cellWithMinF) {
						List<Cell> Closeun = estimatePath(goalCell, cell);
						List<Cell> Closedeux = estimatePath(goalCell,cellWithMinF);
						if (Closeun.size() < Closedeux.size()) {
							lstClose.addAll(Closeun);
						} else {
							lstClose.addAll(Closedeux);
						}
					}
				}
				lstClose.add(currentCell);
				lstOpen.remove(currentCell);
				currentCell = cellWithMinF;
				System.out.println("estimate cell"+currentCell.toString2());
			}

			dessinerMap(goalCell, lstClose);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} return null;
	}
	
	/**
	 * Add the cell from the list if the not already contain in the open list.
	 * @param l The list to add.
	 */
	private void addOpen(List<Cell> l, List<Cell> lstOpen) {
		for (Cell cell : l) {
			if (!lstOpen.contains(cell)) {
				lstOpen.add(cell);
			}
		}
	}
	
	/**
	 * Draw the list of all cell.
	 * @param goal
	 */
	public void dessinerMap(Cell goal, List<Cell> lstClose) {
		System.out.println("******************************");
//		System.out.println("liste open size ="+this.lstOpen.size());
//		System.out.println(this.lstOpen.toString());
//		System.out.println("liste close size ="+this.lstClose.size());
//		System.out.println(this.lstClose.toString());
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
		System.out.println("******************************");
		System.out.println("");
	}
	
	
	public void dessinerMap2(Cell goal,List<Cell> lstGoal) {
		System.out.println("******************************");
//		System.out.println("liste open size ="+this.lstOpen.size());
//		System.out.println(this.lstOpen.toString());
//		System.out.println("liste close size ="+this.lstClose.size());
//		System.out.println(this.lstClose.toString());
		for (Cell[] cells : lstAllCells) {
			for (Cell cell : cells) {
				String s = "";
				if(cell.getCelluleBloquante())
					s = "|";
				else {
					s = "-";
				}
				if(lstGoal.contains(cell)) {
					s = "C";
				}
				if(cell == goal) {
					s ="D";
				}
				System.out.print(s);
			}
			System.out.println();
		}
		System.out.println("******************************");
		System.out.println("");
	}
	/**
	 * Find the path from parent cell.
	 * @param c
	 * @param cells
	 * @return List<Cell> The solution list of deplacement to find the destination.
	 */
	private List<Cell> construcGoodPath(Cell c, List<Cell> cells) {
		if(c.getParent() !=null)
			cells.add(c.getParent());
		if(c.getParent() != null) {
			construcGoodPath(c.getParent(),cells);
		}else {
			List<Cell> temp = new ArrayList<>();
			for (int i = cells.size()-1; i >= 0 ; i--) {
//				System.out.println("c"+cells.get(i));
				temp.add(cells.get(i));
			}
			return temp;
		}
		return cells;
	}
	
	/**
	 * Find the possible 8 adjancent cell from c. 
	 * @param c The cell.
	 * @return List<Cell> A cell list that complet all the condition : need to be in the lstAllCells, need to be not contain in the lstClose, and need to be free to move in.
	 */
	private List<Cell> findAdjacent(Cell c,List<Cell> lstClose){
		int x = c.getX();
		int y = c.getY();
		List<Cell> lst = new ArrayList<>();
		if((x+1<height) && !lstAllCells[x+1][y].getCelluleBloquante() && !lstClose.contains(lstAllCells[x+1][y])) {
			lst.add(lstAllCells[x+1][y]);
		} if((x-1>=0) && !lstAllCells[x-1][y].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y]) ) {
			lst.add(lstAllCells[x-1][y]);
		} if((x+1<height) && (y+1<width) && !lstAllCells[x+1][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x+1][y+1])) {
			lst.add(lstAllCells[x+1][y+1]);
			lstAllCells[x+1][y+1].setIsDiagonal(true);
		} if((x+1<height)&& (y-1>=0) && !lstAllCells[x+1][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x+1][y-1]) )	{
			lst.add(lstAllCells[x+1][y-1]);
			lstAllCells[x+1][y-1].setIsDiagonal(true);
		} if((x-1>=0) && (y-1>=0) && !lstAllCells[x-1][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y-1]))	{
			lst.add(lstAllCells[x-1][y-1]);
			lstAllCells[x-1][y-1].setIsDiagonal(true);
		} if((x-1>=0) && (y+1<width) && !lstAllCells[x-1][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x-1][y+1]))	{
			lst.add(lstAllCells[x-1][y+1]);
			lstAllCells[x-1][y+1].setIsDiagonal(true);
		} if((y-1>=0) && !lstAllCells[x][y-1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x][y-1])) {
			lst.add(lstAllCells[x][y-1]);
		} if((y+1<width) && !lstAllCells[x][y+1].getCelluleBloquante() && !lstClose.contains(lstAllCells[x][y+1]) ) {
			lst.add(lstAllCells[x][y+1]);	
		}
		for (Cell cell : lst) {
			cell.setParent(c);
		}
		return lst;
	}
	
	/**
	 * Find the heuristic distance from the targetCell and the goalCell.
	 * @param targetCell
	 * @param goalCell
	 */
	private void findH(Cell targetCell, Cell goalCell) {
		double h = Math.abs(goalCell.getX() - targetCell.getX()) + Math.abs(goalCell.getY() - targetCell.getY());
		targetCell.setH(h);
	}
	
	/**
	 * Find the coast of all the deplacement for the cell and set it.
	 * @param cell
	 */
	private void findG(Cell cell, List<Cell> lstOpen) {
		double g = 0;
		if(cell.getIsDiagonal()) {
			g = 1.4;			
		} else {
			g = 1;			
		}

		if(cell.getParent() != null && cell.getParent().getParent() != null && lstOpen.contains(cell)) {
			double gParentDeParent = findGParentOfParent(cell);
			double gParent = cell.getParent().getG() + g;
//			System.out.println("cellule"+cell.toString()+" parent="+cell.getParent()+" pdp="+cell.getParent().getParent());
			if(gParentDeParent < gParent) {
//				System.out.println("on set le gpp");
				cell.setParent(cell.getParent().getParent());
				cell.setG(gParentDeParent);
			}else {
//				System.out.println("on set le gp");
				cell.setG(gParent);
			}
		}else {
			cell.setG(cell.getParent().getG()+g);
//			System.out.println(cell);
		}
	}
	
	/**
	 * Find if diagonal
	 * @param c
	 * @return
	 */
	private double findGParentOfParent(Cell c) {
		Cell parent = c.getParent().getParent();
		int delta = Math.abs(parent.getX()-c.getX()) + Math.abs(parent.getY() - c.getY());
		if(delta >= 2) {
			return parent.getG()+1.4;
		} else {
			return parent.getG()+1;
		}
	}
	
	/**
	 * Verifying if the game object is colliding with something in the world.
	 * @param ga The game world that need to be verifying.
	 * @return true is there is a collision, false otherwise.
	 */
	private boolean isCollide(GameObject ga,double x,double y) {
		for (GameObject gas : gw.getLevel().getLstGameObject()) {
			if (gas.getHitbox() != null && gas.getzIndex().equals(ga.getzIndex()) && gas.getPosition().equals(new Point2D(x,y))) {
				return true;
			}
		} 
		return false;
	} 
	/**
	 * Verifying if the actual cell is the destination cell.
	 * @param destination The destination cell.
	 * @param actual The actual cell.
	 * @return true the actual cell is the same of the destination one, false otherwise.
	 */
	private boolean isDestination(Cell destination, Cell actual) {
		return destination.equals(actual);
	}
}
