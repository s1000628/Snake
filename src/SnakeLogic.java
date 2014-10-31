import java.util.*;

public class SnakeLogic {
	
	public SnakeLogic() {
		reset();
	}
	
	public void step() {
		step(d);
	}
	
	public void step(Direction input) {
		// ���ɃQ�[���I�[�o�[�Ȃ牽�����Ȃ�
		if (gameover) {
			return;
		}
		
		// ���݂̓��̈ʒu�� tails �ɒǉ�����
		tails.addFirst(head.clone());
		
		// �����𑵂���
		while (tails.size() > len - 1) {
			tails.removeLast();
		}
		
		// ���͂ɍ��킹�Č�����ς���
		d = input;
		
		// �����Ă��������1�i��
		if (d.equals(Direction.RIGHT)) {
			++head.x;
		} else if (d.equals(Direction.UP)) {
			--head.y;
		} else if (d.equals(Direction.LEFT)) {
			--head.x;
		} else {
			++head.y;
		}
		
		// ��ʊO�ɏo����Q�[���I�[�o�[
		if (head.x < 0 || head.y < 0 || head.x >= w || head.y >= h) {
			gameover = true;
		}
		
		// �����̐g�̂ɂԂ�������Q�[���I�[�o�[
		for (Point2D tail : tails) {
			if (head.equals(tail)) {
				gameover = true;
				break;
			}
		}
		
		// �G�T���������L�т�
		if (!gameover && head.equals(bait)) {
			++len;
			setNextBait();
		}
	}
	
	public void reset() {
		gameover = false;
		
		tails.clear();
		head = new Point2D(1, 1);
		d = Direction.RIGHT;
		setNextBait();
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
		return head.clone();
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
	
	public int getLength() {
		return len;
	}
	
	public void setLength(int length) {
		len = length;
	}
	
	public Point2D getBait() {
		return bait.clone();
	}
	
	public void setBait(Point2D bait) {
		this.bait = bait.clone();
	}
	
	private void setNextBait() {
		boolean[][] bodyExists = new boolean[w][h];
		for (int x = 0; x < w; ++x) {
			for (int y = 0; y < h; ++y) {
				bodyExists[x][y] = false;
			}
		}
		if (head.x >= 0 && head.y >= 0 && head.x < w && head.y < h) {
			bodyExists[head.x][head.y] = true;
		}
		for (Point2D tail : tails) {
			bodyExists[tail.x][tail.y]= true; 
		}
		LinkedList<Point2D> spaces = new LinkedList<Point2D>();
		for (int x = 0; x < w; ++x) {
			for (int y = 0; y < h; ++y) {
				if (!bodyExists[x][y]) {
					spaces.add(new Point2D(x, y));
				}
			}
		}
		bait = spaces.get(r.nextInt(spaces.size()));
	}
	
	private int w = 16, h = 16;
	private Point2D head = new Point2D(1, 1);
	private Direction d = Direction.RIGHT;
	private LinkedList<Point2D> tails = new LinkedList<Point2D>();
	private int len = 3;
	private Point2D bait = new Point2D(14, 14);
	private boolean gameover = false;
	
	private Random r = new Random();

}
