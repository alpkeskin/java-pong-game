package pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

	private Color beginnerBallColor = OnePlayerFrame.ballColor;
	private Color twoPlayerBallColor = TwoPlayerFrame.ballColor;
	private Random random;
	public int xVelocity;
	public int yVelocity;
	private int initialSpeed = 2;
	Random rand = new Random();
	OnePlayerFrame dFrame;

	Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection * initialSpeed);

		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection * initialSpeed);

	}

	Color randomColor;

	public void ColorLooper() {

		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		randomColor = new Color(r, g, b);

	}

	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}

	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}

	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	public void draw(Graphics g) {

		if (dFrame.difficulty == 3 || dFrame.difficulty == 2) {

			g.setColor(randomColor);
		} else {
			if (MenuFrame.mod == false)
				g.setColor(beginnerBallColor);
			else
				g.setColor(twoPlayerBallColor);
		}

		g.fillOval(x, y, height, width);
	}
}