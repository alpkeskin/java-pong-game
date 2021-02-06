package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.*;

public class GamePanel extends JPanel implements Runnable {

	private boolean gameMode = MenuFrame.mod;
	boolean control1 = OnePlayerFrame.control;
	boolean control2 = TwoPlayerFrame.control;
	boolean control3 = TwoPlayerFrame.control;
	static final int gameWidth = 1000;
	static final int gameHeight = (int) (gameWidth * (0.5555));
	static final Dimension screenSize = new Dimension(gameWidth, gameHeight);
	static int ballDiameter = 20;
	static final int paddleWidth = 25;
	static int paddleHeight = 100;
	BufferedImage background;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;// (SOL)
	Paddle paddle3;// (SAG)
	Ball ball;
	AI_paddle paddle2;
	public static Score score;
	private int scoreLimit;
	public static String winner;
	public static String player1, player2;

	GamePanel() {
		newPaddles();
		newBall();

		try {
			background = ImageIO.read(this.getClass().getResource("mainbg.png"));
		} catch (IOException ex) {
			
		}

		if (gameMode == false) {
			newAIPaddle();

		}

		score = new Score(gameWidth, gameHeight);
		this.setFocusable(true);

		this.addKeyListener(new AL());

		this.setPreferredSize(screenSize);

		Handlerclass handler = new Handlerclass();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);

		gameThread = new Thread(this);
		gameThread.start();

		if (score.player1 > 1)
			Paddle.stop = true;
		else if (score.player2 > 1)
			Paddle.stop = true;
	}

	public void printWinner() {
		System.out.println("************************************");
		System.out.println("WINNER: " + winner);
		double seconds = Timer.getTimeSec();
		int value = (int) seconds;
		System.out.println("Game Time: " + value + " seconds.");
		System.out.println("Match Score: " + player1 + ": " + score.player1 + " | " + player2 + ": " + score.player2);
		System.out.println("************************************");
	}

	public void newBall() {
		random = new Random();

		if (OnePlayerFrame.difficulty == 3 && gameMode == false) {

			ballDiameter = 18;

			ball = new Ball((gameWidth / 2) - (ballDiameter / 2), random.nextInt(gameHeight - ballDiameter),
					ballDiameter, ballDiameter);
		}
		if (OnePlayerFrame.difficulty == 2 && gameMode == false) {

			ballDiameter = 23;

			ball = new Ball((gameWidth / 2) - (ballDiameter / 2), random.nextInt(gameHeight - ballDiameter),
					ballDiameter, ballDiameter);
		}
		if (OnePlayerFrame.difficulty == 1 && gameMode == false) {

			ballDiameter = 25;

			ball = new Ball((gameWidth / 2) - (ballDiameter / 2), random.nextInt(gameHeight - ballDiameter),
					ballDiameter, ballDiameter);
		}

		ball = new Ball((gameWidth / 2) - (ballDiameter / 2), random.nextInt(gameHeight - ballDiameter), ballDiameter,
				ballDiameter);
	}

	public void newAIPaddle() {

		if (gameMode == false) {

			paddle2 = new AI_paddle(gameWidth - paddleWidth, (gameHeight / 2) - (paddleHeight / 2), paddleWidth,
					paddleHeight, 2, ball);
		}

	}

	public void newPaddles() {

		if (OnePlayerFrame.difficulty == 3 && gameMode == false) {

			paddleHeight = 60;
			paddle1 = new Paddle(0, (gameHeight / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);

		}
		if (OnePlayerFrame.difficulty == 2 && gameMode == false) {

			paddle1 = new Paddle(0, (gameHeight / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);
		}
		if (OnePlayerFrame.difficulty == 1 && gameMode == false) {

			paddleHeight = 120;
			paddle1 = new Paddle(0, (gameHeight / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);
		}

		paddle1 = new Paddle(0, (gameHeight / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);

		if (gameMode == true) {
			paddle3 = new Paddle(gameWidth - paddleWidth, (gameHeight / 2) - (paddleHeight / 2), paddleWidth,
					paddleHeight, 3);
		}
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, gameWidth, gameHeight, null);
		paddle1.draw(g);
		ball.draw(g);

		if (gameMode == false) {

			paddle2.draw(g);
		} else if (gameMode == true) {

			paddle3.draw(g);
		}
		score.draw(g);
		Toolkit.getDefaultToolkit().sync();

	}

	public void move() {
		paddle1.move();

		if (gameMode == true) {

			paddle3.move();
		}

		if (gameMode == false) {

			paddle2.move();
		}

		ball.move();
		// paddle2.move();
	}

	public void checkCollision() {


		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= gameHeight - ballDiameter) {
			ball.setYDirection(-ball.yVelocity);
		}

		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);

			if (OnePlayerFrame.difficulty == 3 && gameMode == false) {
				ball.xVelocity += 1.5;
				if (ball.xVelocity >= 12) {
					ball.xVelocity = 12;
				}
			}
			if (OnePlayerFrame.difficulty == 2 && gameMode == false) {
				ball.xVelocity += 1.2;
				if (ball.xVelocity >= 10) {
					ball.xVelocity = 10;
				}
				ball.ColorLooper();
			}
			if (OnePlayerFrame.difficulty == 1 && gameMode == false) {
				ball.xVelocity++;
				if (ball.xVelocity >= 8) {
					ball.xVelocity = 8;
				}
			}

			if (ball.yVelocity > 0) {

				if (OnePlayerFrame.difficulty == 3 && gameMode == false) {
					ball.xVelocity += 1.5;
				}
				if (OnePlayerFrame.difficulty == 2 && gameMode == false) {
					ball.xVelocity += 1.2;
					ball.ColorLooper();
				}
				if (OnePlayerFrame.difficulty == 1 && gameMode == false) {
					ball.xVelocity++;
				}
			}
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

		if (gameMode == false) {

			if (ball.intersects(paddle2)) {
				ball.xVelocity = Math.abs(ball.xVelocity);

				if (OnePlayerFrame.difficulty == 3 && gameMode == false) {
					ball.xVelocity += 1.5;
				}
				if (OnePlayerFrame.difficulty == 2 && gameMode == false) {
					ball.xVelocity += 1.2;
					ball.ColorLooper();
				}
				if (OnePlayerFrame.difficulty == 1 && gameMode == false) {
					ball.xVelocity++;
				}


				if (ball.yVelocity > 0) {
					// paddle2.AIColorLooper();

					if (OnePlayerFrame.difficulty == 3 && gameMode == false) {
						ball.xVelocity += 1.5;
					}
					if (OnePlayerFrame.difficulty == 2 && gameMode == false) {
						ball.xVelocity += 1.2;
						ball.ColorLooper();
					}
					if (OnePlayerFrame.difficulty == 1 && gameMode == false) {
						ball.xVelocity++;
					}
				}

				else
					ball.yVelocity--;
				ball.setXDirection(-ball.xVelocity);
				ball.setYDirection(ball.yVelocity);
			}
		}

		else if (gameMode == true) {

			if (ball.intersects(paddle3)) {
				ball.xVelocity = Math.abs(ball.xVelocity);
				ball.xVelocity++;
				if (ball.yVelocity > 0)
					ball.yVelocity++; 
				else
					ball.yVelocity--;
				ball.setXDirection(-ball.xVelocity);
				ball.setYDirection(ball.yVelocity);
			}
		}


		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (gameHeight - paddleHeight))
			paddle1.y = gameHeight - paddleHeight;

		if (gameMode == false) {

			if (paddle2.y <= 0)
				paddle2.y = 0;
			if (paddle2.y >= (gameHeight - paddleHeight))
				paddle2.y = gameHeight - paddleHeight;

		}

		if (gameMode == true) {

			if (paddle3.y <= 0)
				paddle3.y = 0;
			if (paddle3.y >= (gameHeight - paddleHeight))
				paddle3.y = gameHeight - paddleHeight;
		}

		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();

			if (gameMode == false) {

				newAIPaddle();
			}
			if (gameMode == false) { // TEK KİŞİLİK
				scoreLimit = OnePlayerFrame.scoreLimit;
				player2 = OnePlayerFrame.botNames;
				player1 = OnePlayerFrame.player;
			} else {
				scoreLimit = TwoPlayerFrame.scoreLimit;
				player2 = TwoPlayerFrame.player2;
				player1 = TwoPlayerFrame.player1;
			}

			System.out.println(player2 + ": " + score.player2);
			if (score.player2 == scoreLimit) {
				winner = player2;
				Paddle.stop = true;
				WinnerFrame wf = new WinnerFrame();
				printWinner();
			}

		}
		if (ball.x >= gameWidth - ballDiameter) {
			score.player1++;
			newPaddles();
			newBall();

			if (gameMode == false) {

				newAIPaddle();
			}

			if (gameMode == false) { // TEK KİŞİLİK
				scoreLimit = OnePlayerFrame.scoreLimit;
				player1 = OnePlayerFrame.player;
				player2 = OnePlayerFrame.botNames;
			} else {
				scoreLimit = TwoPlayerFrame.scoreLimit;
				player1 = TwoPlayerFrame.player1;
				player2 = TwoPlayerFrame.player2;
			}

			System.out.println(player1 + ": " + score.player1);

			if (score.player1 == scoreLimit) {
				winner = player1;
				Paddle.stop = true;
				WinnerFrame wf = new WinnerFrame();
				printWinner();
			}
		}
	}

	public void run() {
		// game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while (paddle1.stop == false) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				if (OnePlayerFrame.difficulty == 3) {
					ball.ColorLooper();
				}
				repaint();
				delta--;
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (control1 == false) {
				paddle1.keyPressed(e);
			}
			if (control1 == true) {

				paddle1.keyPressed(e);

			}
			if (control2 == true) {
				paddle1.keyPressed(e);
			}

			if (gameMode == true) {

				paddle3.keyPressed(e);
			}
			if (control3 == true) {
				paddle3.keyPressed(e);

			}

		}

		public void keyReleased(KeyEvent e) {
			if (control1 == false) {
				paddle1.keyReleased(e);
			}
			if (control1 == true) {

				paddle1.keyReleased(e);

			}

			if (control2 == true) {
				paddle1.keyReleased(e);
			}

			if (gameMode == true) {

				paddle3.keyReleased(e);

			}
			if (control3 == true) {
				paddle3.keyPressed(e);

			}

		}
	}

	private class Handlerclass implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.mouseDrag(e);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.move(e);
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.mousePressed(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.mouseReleased(e);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.mouseEntered(e);
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (control1 == true) {
				paddle1.mouseExited(e);
			}

		}
	}
}
