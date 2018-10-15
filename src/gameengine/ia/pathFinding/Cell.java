package gameengine.ia.pathFinding;

public class Cell {
    private double g = 0;  // g is distance from the source
    private double h;  // h is the heuristic of destination.
    private double f;  // f = g + h 
    private int x,y;
    private Boolean celluleBloquante;
    private Cell parent;
    private Boolean isDiagonal = false;
    
	public Cell(Double h,boolean celluleBloquante,int x,int y) {
		this.h= h;
		this.celluleBloquante = celluleBloquante;
		this.x=x;
		this.y=y;
	}
	
	public Boolean getIsDiagonal() {
		return isDiagonal;
	}

	public void setIsDiagonal(Boolean isDiagonal) {
		this.isDiagonal = isDiagonal;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getF() {
		return h+g;
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
	public String toString2() {
		return "Cell [g=" + g + ", h=" + h + ", f=" +(g+h) + ", x=" + x + ", y=" + y + ", celluleBloquante="
				+ celluleBloquante + ", parent=" + parent + ", isDiagonal=" + isDiagonal + "]";
	}
	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y+ "]";
	}
    
    
}
