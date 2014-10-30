import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

public class SnakePanel extends JPanel implements ActionListener {
	
	public SnakePanel() {
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		logic.step(Direction.RIGHT);
		repaint();
	}
	
	private SnakeLogic logic = new SnakeLogic();
	private int cellW = 16, cellH = 16;
	private int posX = 0, posY = 0;
	
	private Timer timer = new Timer(1000 / 4, this);

}
