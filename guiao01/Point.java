package guiao01;

public class Point {
	private double x;
	private double y;
	
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double x() {
		return this.x;
	}
	public double y() {
		return this.y;
	}
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	public String toString(String sep) {
		return "(" + this.x + sep + this.y + ")";
	}
	public double distance(Point p2) {
		return Math.sqrt((p2.y()-this.y())*(p2.y()-this.y()) + (p2.x()-this.x())*(p2.x()-this.x()));
	}
}
