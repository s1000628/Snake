import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snake.setSize(640, 480);
		snake.setResizable(false);
		snake.setLocationByPlatform(true);
		snake.add(new SnakePanel(), BorderLayout.CENTER);
		snake.setVisible(true);
	}

}
