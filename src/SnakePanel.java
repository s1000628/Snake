import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

public class SnakePanel extends JPanel implements ActionListener, KeyListener {
	
	public SnakePanel() {
		this.setFocusable(true);
		this.addKeyListener(this);
		
		logic.setLength(3);
		logic.setWidth(20);
		logic.setHeight(20);
		
		posX = (640 - logic.getWidth() * cellW) / 2;
		posY = (480 - logic.getHeight() * cellH) / 2;
				
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(new Color(1.0f, 1.0f, 1.0f));
		g2.fill(new Rectangle2D.Double(posX, posY, cellW * logic.getWidth(), cellH * logic.getHeight()));
		
		Point2D bait = logic.getBait();
		drawEllipse(g2, bait.x, bait.y, new Color(1.0f, 0.5f, 0.0f));

		Point2D head = logic.getHead();
		drawRect(g2, head.x, head.y, new Color(1.0f, 0.0f, 0.0f));
		
		for (Point2D tail : logic.getTails()) {
			drawRect(g2, tail.x, tail.y, new Color(0.5f, 0.0f, 0.0f));
		}
		
		g2.setColor(new Color(0.0f, 0.0f, 0.0f));
		g2.fill(new Rectangle2D.Double(0, 0, 640, posY));
		g2.fill(new Rectangle2D.Double(0, 0, posX, 480));
		g2.fill(new Rectangle2D.Double(posX + cellW * logic.getWidth(), 0, 640 - posX - cellW * logic.getWidth(), 480));
		g2.fill(new Rectangle2D.Double(0, posY + cellH * logic.getHeight(), 640, 480 - posY - cellH * logic.getHeight()));
	}
	
	private void drawRect(Graphics2D g2, int x, int y, Color color) {
		Rectangle2D rect = new Rectangle2D.Double(
				posX + x * cellW, posY + y * cellH, cellW, cellH
				);
		Color prevCol = g2.getColor();
		g2.setColor(color);
		g2.fill(rect);
		g2.setColor(prevCol);
	}
	
	private void drawEllipse(Graphics2D g2, int x, int y, Color color) {
		Ellipse2D ellipse = new Ellipse2D.Double(
				posX + x * cellW, posY + y * cellH, cellW, cellH
				);
		Color prevCol = g2.getColor();
		g2.setColor(color);
		g2.fill(ellipse);
		g2.setColor(prevCol);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (hasInput && !input.equals(logic.getDirection().getOpposite())) {
			logic.step(input);
			hasInput = false;
		} else {
			logic.step();
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		int key = ev.getKeyCode();
		if (key == 39 || key == 68) {
			hasInput = true;
			input = Direction.RIGHT;
		} else if (key == 38 || key == 87) {
			hasInput = true;
			input = Direction.UP;
		} else if (key == 37 || key == 65) {
			hasInput = true;
			input = Direction.LEFT;
		} else if (key == 40 || key == 83) {
			hasInput = true;
			input = Direction.DOWN;
		}
	}

	@Override
	public void keyReleased(KeyEvent ev) {
	}

	@Override
	public void keyTyped(KeyEvent ev) {
	}
	
	private SnakeLogic logic = new SnakeLogic();
	private int cellW = 16, cellH = 16;
	private int posX = 0, posY = 0;
	
	private Timer timer = new Timer(1000 / 6, this);
	
	boolean hasInput = false;
	Direction input = Direction.RIGHT;

}
