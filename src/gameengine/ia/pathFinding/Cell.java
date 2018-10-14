package gameengine.ia.pathFinding;

public class Cell {
    private double g;  // g is distance from the source
    private double h;  // h is the heuristic of destination.
    private double f;  // f = g + h 
    private int x,y;
    private Boolean celluleBloquante;
    private Cell parent;
    private Boolean checked;
    
	public Cell(Double h,boolean celluleBloquante,int x,int y) {
		this.h= h;
		this.celluleBloquante = celluleBloquante;
		this.x=x;
		this.y=y;
	}
	public double getG() {
		return h+f;
	}

	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public Boolean getCelluleBloquante() {
		return celluleBloquante;
	}
	public void setCelluleBloquante(Boolean celluleBloquante) {
		this.celluleBloquante = celluleBloquante;
	}
	
	public Cell getParent() {
		return parent;
	}
	public void setParent(Cell parent) {
		this.parent = parent;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Cell [b=" + celluleBloquante + "]";
	}
    
    
    
}
