
public class Direction {

	private enum Value {
		RIGHT,
		UP,
		LEFT,
		DOWN,
	}
	public static final Direction RIGHT = new Direction(Value.RIGHT);
	public static final Direction UP = new Direction(Value.UP);
	public static final Direction LEFT = new Direction(Value.LEFT);
	public static final Direction DOWN = new Direction(Value.DOWN);
	
	@Override
	public String toString() {
		if (v == Value.RIGHT) {
			return "RIGHT";
		} else if (v == Value.UP) {
			return "UP";
		} else if (v == Value.LEFT) {
			return "LEFT";
		} else {
			return "DOWN";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direction other = (Direction) obj;
		if (v != other.v)
			return false;
		return true;
	}

	private Direction() {
	}
	
	private Direction(Value value) {
		v = value;
	}
	
	private Value v = Value.RIGHT;
	
}
