import java.util.*;

public class SnakeLogic {
	
	public SnakeLogic() {
		reset();
	}
	
	/**
	 * ゲームを1ステップ進める(操作なし)。
	 */
	public void step() {
		step(d);
	}
	
	/**
	 * ゲームを1ステップ進める。
	 * @param input プレイヤーの入力の方向
	 */
	public void step(Direction input) {
		// 既にゲームオーバーなら何もしない
		if (gameover) {
			return;
		}
		
		// 現在の頭の位置を tails に追加する
		tails.addFirst(head.clone());
		
		// 長さを揃える
		while (tails.size() > len - 1) {
			tails.removeLast();
		}
		
		// 入力に合わせて向きを変える
		d = input;
		
		// 向いている方向に1つ進む
		if (d.equals(Direction.RIGHT)) {
			++head.x;
		} else if (d.equals(Direction.UP)) {
			--head.y;
		} else if (d.equals(Direction.LEFT)) {
			--head.x;
		} else {
			++head.y;
		}
		
		// 画面外に出たらゲームオーバー
		if (head.x < 0 || head.y < 0 || head.x >= w || head.y >= h) {
			gameover = true;
		}
		
		// 自分の身体にぶつかったらゲームオーバー
		for (Point2D tail : tails) {
			if (head.equals(tail)) {
				gameover = true;
				break;
			}
		}
		
		// エサを取ったら伸びる
		if (!gameover && head.equals(bait)) {
			++len;
			setNextBait();
		}
	}
	
	/**
	 * ゲームの状態を初期化する。
	 */
	public void reset() {
		gameover = false;
		
		tails.clear();
		head = new Point2D(1, 1);
		d = Direction.RIGHT;
		setNextBait();
	}
	
	/**
	 * フィールドの幅を取得する。
	 * @return フィールドの幅
	 */
	public int getWidth() {
		return w;
	}
	
	/**
	 * フィールドの幅を設定する。
	 * @param width フィールドの幅
	 * @throws IllegalArgumentException
	 */
	public void setWidth(int width) {
		if (w < 2) {
			throw new IllegalArgumentException();
		}
		w = width;
	}
	
	/**
	 * フィールドの高さを取得する。
	 * @return フィールドの高さ
	 */
	public int getHeight() {
		return h;
	}
	
	/**
	 * フィールドの高さを設定する。
	 * @param height フィールドの高さ
	 * @throws IllegalArgumentException();
	 */
	public void setHeight(int height) {
		if (h < 2) {
			throw new IllegalArgumentException();
		}
		h = height;
	}

	/**
	 * ヘビの頭の位置を取得する。
	 * @return ヘビの頭の位置
	 */
	public Point2D getHead() {
		return head.clone();
	}
	
	/**
	 * ヘビの頭の位置を設定する。
	 * @param head ヘビの頭の位置
	 * @throws IllegalArgumentException
	 */
	public void setHead(Point2D head) {
		if (head.x < 0 || head.y < 0 || head.x >= w || head.y >= h) {
			throw new IllegalArgumentException();
		}
		this.head = head.clone();
	}
	
	/**
	 * ヘビの移動方向を取得する。
	 * @return ヘビの移動方向
	 */
	public Direction getDirection() {
		return d;
	}
	
	/**
	 * ヘビの移動方向を設定する。
	 * @param direction ヘビの移動方向
	 */
	public void setDirection(Direction direction) {
		d = direction;
	}
	
	/**
	 * ヘビの尾(頭以外)の位置のリストを取得する。
	 * リストは頭に近い方からの順になっている。
	 * @return ヘビの尾の位置のリスト
	 */
	public List<Point2D> getTails() {
		return (LinkedList<Point2D>)tails.clone();
	}
	
	/**
	 * ヘビの頭も含めた全体の長さを取得する。
	 * @return ヘビの長さ
	 */
	public int getLength() {
		return len;
	}
	
	/**
	 * ヘビの頭も含めた全体の長さを設定する。
	 * @param length ヘビの長さ
	 * @throws IllegalArgumentException
	 */
	public void setLength(int length) {
		if (len < 1) {
			throw new IllegalArgumentException();
		}
		len = length;
	}
	
	/**
	 * エサの位置を取得する。
	 * @return エサの位置
	 */
	public Point2D getBait() {
		return bait.clone();
	}
	
	/**
	 * エサの位置を設定する。
	 * @param bait エサの位置
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
