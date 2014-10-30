import java.util.*;

public class SnakeLogic {
	
	public void step(Direction input) {
		throw new UnsupportedOperationException();
	}
	
	public void reset() {
		throw new UnsupportedOperationException();
	}
	
	public int getWidth() {
		return w;
	}
	
	public void setWidth(int width) {
		if (w < 2) {
			throw new IllegalArgumentException();
		}
		w = width;
	}
	
	public int getHeight() {
		return h;
	}
	
	public void setHeight(int height) {
		if (h < 2) {
			throw new IllegalArgumentException();
		}
		h = height;
	}

	public Point2D getHead() {
		return head;
	}
	
	public void setHead(Point2D head) {
		this.head = head.clone();
	}
	
	public Direction getDirection() {
		return d;
	}
	
	public void setDirection(Direction direction) {
		d = direction;
	}
	
	public List<Point2D> getTails() {
		return (LinkedList<Point2D>)tails.clone();
	}
	
	private int w = 16, h = 16;
	private Point2D head = new Point2D(1, 1);
	private Direction d = Direction.RIGHT;
	private LinkedList<Point2D> tails = new LinkedList<Point2D>();

}
