package gameengine.ia.pathFinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gameengine.entities.GameObject;
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
		lstGoodPath = construcGoodPath(closeList.get(closeList.size()-1),lstGoodPath);
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
		Map<Cell,Cell> mEgalite = new HashMap<>();
		List<Cell>lstCellCurrent = new ArrayList<>();
		lstCellCurrent.add(currentCell);
		lstOpen.add(currentCell);
		List<Cell> lstAlreadyDetected = new ArrayList<>();
		
		while(!lstOpen.isEmpty()) {
			for (Iterator<Cell> iterator = lstCellCurrent.iterator(); iterator.hasNext();) {
				System.out.println(lstCellCurrent.size());
				Cell cellCurrentN = (Cell) iterator.next();
				List<Cell> temp = findAdjacent(cellCurrentN, lstClose);
				System.out.println("cellcourante"+cellCurrentN);
				for (Cell cell : temp) {
					if(isDestination(cell, goalCell)) {
						System.out.println("lA FINNNNN");
						lstClose.add(cell);
						return lstClose;
					}
					findH(cell,goalCell);
					cell.setG(cell.getParent().getG());
				}
				
				
				if(temp.isEmpty()) {
					System.out.println("pas de solution");
					if(lstAlreadyDetected.contains(cellCurrentN)) {
						iterator.remove();
					}
					
					if(cellCurrentN.getParent() != null && !lstAlreadyDetected.contains(cellCurrentN))
					{
						lstAlreadyDetected.add(cellCurrentN);
						int inde = lstCellCurrent.lastIndexOf(cellCurrentN);
						lstCellCurrent.set(inde, cellCurrentN.getParent());
						lstClose.add(cellCurrentN);
					}

					


				} else {
					addOpen(temp, lstOpen);
					double minF = temp.get(0).getF();
					Cell cellWithMinF = temp.get(0);
					for (Cell cell : temp) {
						if(minF >cell.getF()) {
							minF = cell.getF();  
							cellWithMinF = cell;
						} else if(minF == cell.getF() && cell != cellWithMinF) {
							
							mEgalite.put(cellCurrentN,cell); 

							System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
						}
					}
					lstClose.add(cellCurrentN);
					lstOpen.remove(cellCurrentN);
					int inde = lstCellCurrent.lastIndexOf(cellCurrentN);
					lstCellCurrent.set(inde, cellWithMinF);
					
					
					System.out.println("estimate cell"+cellWithMinF.toString2());
				}
			}
			if(!mEgalite.isEmpty()) {
				for (Entry<Cell, Cell> cell : mEgalite.entrySet()) {
					addCurrent(cell.getValue(), lstCellCurrent);
				}
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
	 * Add the cell from the list if the not already contain in the open list.
	 * @param l The list to add.
	 */
	private void addCurrent(Cell cell, List<Cell> lstCurrent) {
		if (!lstCurrent.contains(cell)) {
			lstCurrent.add(cell);
		}
	}
	
	/**
	 * Draw the list of all cell.
	 * @param goal
	 */
	public void dessinerMap(Cell goal, List<Cell> lstClose) {
		System.out.println("******************************");
		for (Cell[] cells : lstAllCells) {
			for (Cell cell : cells) {
				String s = "";
				if(cell.getCelluleBloquante())
					s = " | ";
				else {
					s = " - ";
				}
				if(lstClose.contains(cell)) {
					s = " C ";
				}
				if(cell == goal) {
					s =" D ";
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
