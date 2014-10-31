import java.util.*;

public class SnakeLogic {
	
	public SnakeLogic() {
		reset();
	}
	
	/**
	 * �Q�[����1�X�e�b�v�i�߂�(����Ȃ�)�B
	 */
	public void step() {
		step(d);
	}
	
	/**
	 * �Q�[����1�X�e�b�v�i�߂�B
	 * @param input �v���C���[�̓��͂̕���
	 */
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
	
	/**
	 * �Q�[���̏�Ԃ�����������B
	 */
	public void reset() {
		gameover = false;
		
		tails.clear();
		head = new Point2D(1, 1);
		d = Direction.RIGHT;
		setNextBait();
	}
	
	/**
	 * �t�B�[���h�̕����擾����B
	 * @return �t�B�[���h�̕�
	 */
	public int getWidth() {
		return w;
	}
	
	/**
	 * �t�B�[���h�̕���ݒ肷��B
	 * @param width �t�B�[���h�̕�
	 * @throws IllegalArgumentException
	 */
	public void setWidth(int width) {
		if (w < 2) {
			throw new IllegalArgumentException();
		}
		w = width;
	}
	
	/**
	 * �t�B�[���h�̍������擾����B
	 * @return �t�B�[���h�̍���
	 */
	public int getHeight() {
		return h;
	}
	
	/**
	 * �t�B�[���h�̍�����ݒ肷��B
	 * @param height �t�B�[���h�̍���
	 * @throws IllegalArgumentException();
	 */
	public void setHeight(int height) {
		if (h < 2) {
			throw new IllegalArgumentException();
		}
		h = height;
	}

	/**
	 * �w�r�̓��̈ʒu���擾����B
	 * @return �w�r�̓��̈ʒu
	 */
	public Point2D getHead() {
		return head.clone();
	}
	
	/**
	 * �w�r�̓��̈ʒu��ݒ肷��B
	 * @param head �w�r�̓��̈ʒu
	 * @throws IllegalArgumentException
	 */
	public void setHead(Point2D head) {
		if (head.x < 0 || head.y < 0 || head.x >= w || head.y >= h) {
			throw new IllegalArgumentException();
		}
		this.head = head.clone();
	}
	
	/**
	 * �w�r�̈ړ��������擾����B
	 * @return �w�r�̈ړ�����
	 */
	public Direction getDirection() {
		return d;
	}
	
	/**
	 * �w�r�̈ړ�������ݒ肷��B
	 * @param direction �w�r�̈ړ�����
	 */
	public void setDirection(Direction direction) {
		d = direction;
	}
	
	/**
	 * �w�r�̔�(���ȊO)�̈ʒu�̃��X�g���擾����B
	 * ���X�g�͓��ɋ߂�������̏��ɂȂ��Ă���B
	 * @return �w�r�̔��̈ʒu�̃��X�g
	 */
	public List<Point2D> getTails() {
		return (LinkedList<Point2D>)tails.clone();
	}
	
	/**
	 * �w�r�̓����܂߂��S�̂̒������擾����B
	 * @return �w�r�̒���
	 */
	public int getLength() {
		return len;
	}
	
	/**
	 * �w�r�̓����܂߂��S�̂̒�����ݒ肷��B
	 * @param length �w�r�̒���
	 * @throws IllegalArgumentException
	 */
	public void setLength(int length) {
		if (len < 1) {
			throw new IllegalArgumentException();
		}
		len = length;
	}
	
	/**
	 * �G�T�̈ʒu���擾����B
	 * @return �G�T�̈ʒu
	 */
	public Point2D getBait() {
		return bait.clone();
	}
	
	/**
	 * �G�T�̈ʒu��ݒ肷��B
	 * @param bait �G�T�̈ʒu
	 * @throws IllegalArgumentException
	 */
	public void setBait(Point2D bait) {
		if (bait.x < 0 || bait.y < 0 || bait.x >= w || bait.y >= h) {
			throw new IllegalArgumentException();
		}
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
