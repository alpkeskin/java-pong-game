package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class OnePlayerFrame extends JFrame {
	public static String player;
	public static boolean control;
	public static Color ballColor = Color.WHITE;
	public static Color paddleColor = Color.WHITE;
	private static final int frameWidth = 800;
	private static final int frameHeight = 400;
	static Random rand = new Random();
	static int randomIndex = rand.nextInt(4);
	static String[] botList = new String[] { "Hasanalp", "Mert", "Alp", "Emin" };
	public static String botNames = botList[randomIndex];
	// private static int coinValue = 0;
	public static GameFrame game;
	public static int difficulty = 1;
	public static int scoreLimit;
	public static String difficultyLevel = "BEGINNER";
	public static String controller = "Keyboard";

	public int getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(int difficulty) {
		OnePlayerFrame.difficulty = difficulty;
	}

	OnePlayerFrame() {

		final JLabel panel = new JLabel(
				new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/mainbg.png"));
		this.setTitle("1 PLAYER MENU");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(frameWidth, frameHeight);

		JButton startButton = new JButton("<html><font color=red size=4><b>START!</b></html>");
		startButton.setBounds(310, 280, 180, 70);
		panel.setLayout(null);
		panel.add(startButton);

		Icon icon = new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/mouse.png");
		JButton mouse = new JButton(icon);
		mouse.setBounds(10, 100, 70, 85);
		panel.add(mouse);

		Icon icon2 = new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/keyboard.png");
		JButton keyboard = new JButton(icon2);
		keyboard.setBounds(10, 200, 95, 85);
		panel.add(keyboard);

		Icon icon3 = new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/back.png");
		JButton back = new JButton(icon3);
		back.setBounds(10, 310, 50, 50);
		panel.add(back);

		JLabel title = new JLabel();
		title.setText("1 PLAYER MENU");
		title.setFont(new java.awt.Font("Arial Black", Font.BOLD, 20));
		title.setOpaque(true);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.RED);
		title.setBounds(310, 20, 210, 20);
		panel.add(title);

		JLabel botTitle = new JLabel();
		botTitle.setText("BOT: " + botNames);
		botTitle.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 16));
		botTitle.setOpaque(true);
		botTitle.setBackground(Color.BLACK);
		botTitle.setForeground(Color.WHITE);
		botTitle.setBounds(600, 20, 250, 30);
		panel.add(botTitle);

		/*
		 * JLabel coinTitle = new JLabel(); coinTitle.setText("Amount of Coin:");
		 * coinTitle.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 16));
		 * coinTitle.setOpaque(true); coinTitle.setBackground(Color.BLACK);
		 * coinTitle.setForeground(Color.WHITE); coinTitle.setBounds(345, 60, 170, 30);
		 * panel.add(coinTitle);
		 * 
		 * 
		 * JLabel coinAmount = new JLabel(); String coin = String.valueOf(coinValue);
		 * coinAmount.setText(coin); coinAmount.setFont(new
		 * java.awt.Font("Times New Roman", Font.BOLD, 24)); coinAmount.setOpaque(true);
		 * coinAmount.setBackground(Color.BLACK); coinAmount.setForeground(Color.WHITE);
		 * coinAmount.setBounds(400, 90, 30, 30); panel.add(coinAmount);
		 */

		JLabel levels = new JLabel();
		levels.setText("AI DIFFICULTY LEVEL");
		levels.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 16));
		levels.setOpaque(true);
		levels.setBackground(Color.BLACK);
		levels.setForeground(Color.WHITE);
		levels.setBounds(570, 180, 200, 20);
		panel.add(levels);

		final AbstractButton beginner = new JRadioButton("Beginner", true);
		beginner.setBounds(620, 200, 100, 40);
		beginner.setForeground(Color.WHITE);
		panel.setLayout(null);
		panel.add(beginner);

		final AbstractButton normal = new JRadioButton("Normal", false);
		normal.setBounds(620, 220, 100, 40);
		normal.setForeground(Color.WHITE);
		panel.setLayout(null);
		panel.add(normal);

		final AbstractButton insane = new JRadioButton("Insane", false);
		insane.setBounds(620, 240, 100, 40);
		insane.setForeground(Color.WHITE);
		panel.setLayout(null);
		panel.add(insane);

		JLabel paddleLabel = new JLabel();
		paddleLabel.setText("Paddle Color");
		paddleLabel.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		paddleLabel.setOpaque(true);
		paddleLabel.setBackground(Color.BLACK);
		paddleLabel.setForeground(Color.WHITE);
		paddleLabel.setBounds(370, 150, 80, 28);
		panel.add(paddleLabel);

		final JLabel ballLabel = new JLabel();
		ballLabel.setText("Ball Color");
		ballLabel.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		ballLabel.setOpaque(true);
		ballLabel.setBackground(Color.BLACK);
		ballLabel.setForeground(Color.WHITE);
		ballLabel.setBounds(370, 210, 80, 28);
		panel.add(ballLabel);

		JLabel spinnerLabel = new JLabel();
		spinnerLabel.setText("Score Limit");
		spinnerLabel.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		spinnerLabel.setOpaque(true);
		spinnerLabel.setBackground(Color.BLACK);
		spinnerLabel.setForeground(Color.WHITE);
		spinnerLabel.setBounds(369, 80, 60, 28);
		panel.add(spinnerLabel);

		SpinnerModel spinnerModel = new SpinnerNumberModel(5, // initial value
				1, // min
				10, // max
				1);// step
		final JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(375, 100, 50, 40);
		spinner.setBackground(Color.BLACK);
		spinner.setForeground(Color.WHITE);
		panel.add(spinner);

		String[] colors = new String[] { "WHITE", "BLUE", "RED", "YELLOW" };

		final JComboBox<String> paddleColorComboBox = new JComboBox<>(colors);
		paddleColorComboBox.setBounds(350, 165, 100, 50);
		paddleColorComboBox.setForeground(Color.WHITE);
		panel.add(paddleColorComboBox);

		final JComboBox<String> ballColorComboBox = new JComboBox<>(colors);
		ballColorComboBox.setBounds(350, 225, 100, 50);
		ballColorComboBox.setForeground(Color.WHITE);
		panel.add(ballColorComboBox);

		final JTextField user = new JTextField(20);
		user.setBounds(20, 20, 100, 30);
		panel.add(user);

		this.setVisible(true);

		this.add(panel);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (user.getText().length() > 1)
					player = user.getText();
				else
					player = "Player";
				game = new GameFrame();
				scoreLimit = (int) spinner.getValue();
				System.out.println("************************************");
				System.out.println(player + " VS " + botNames);
				System.out.println("BOT Difficulty level: " + difficultyLevel + " mod");
				System.out.println("Score Limit: " + scoreLimit);
				System.out.println("Ball Color: " + (String) ballColorComboBox.getSelectedItem());
				System.out.println(player + "'s Paddle Color: " + (String) paddleColorComboBox.getSelectedItem());
				System.out.println("Controller: " + controller);
				System.out.println("************************************");
				Timer.start();
				JComponent comp = (JComponent) ev.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MenuFrame mf = new MenuFrame();
				JComponent comp = (JComponent) ev.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});

		mouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				control = true;
				controller = "Mouse";

			}
		});

		keyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				control = false;
				controller = "Keyboard";

			}
		});

		paddleColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String selectedColor = (String) paddleColorComboBox.getSelectedItem();
				//System.out.println("You selected the color: " + selectedColor);
				if (selectedColor.equals("BLUE")) {
					paddleColor = Color.BLUE;
					paddleColorComboBox.setForeground(Color.BLUE);

				}
				if (selectedColor.equals("WHITE")) {
					paddleColor = Color.WHITE;
					paddleColorComboBox.setForeground(Color.WHITE);
				}
				if (selectedColor.equals("RED")) {
					paddleColor = Color.RED;
					paddleColorComboBox.setForeground(Color.RED);
				}
				if (selectedColor.equals("YELLOW")) {
					paddleColor = Color.YELLOW;
					paddleColorComboBox.setForeground(Color.YELLOW);
				}

			}
		});

		ballColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String selectedColor = (String) ballColorComboBox.getSelectedItem();
				//System.out.println("You selected the color: " + selectedColor);
				if (selectedColor.equals("BLUE")) {
					ballColor = Color.BLUE;
					ballColorComboBox.setForeground(Color.BLUE);

				}
				if (selectedColor.equals("WHITE")) {
					ballColor = Color.WHITE;
					ballColorComboBox.setForeground(Color.WHITE);
				}
				if (selectedColor.equals("RED")) {
					ballColor = Color.RED;
					ballColorComboBox.setForeground(Color.RED);
				}
				if (selectedColor.equals("YELLOW")) {
					ballColor = Color.YELLOW;
					ballColorComboBox.setForeground(Color.YELLOW);
					// System.out.println("The entered text is: " + user.getText()); deneme yaptÄ±m
					// kalsÄ±n.
				}

			}
		});

		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("The entered text is: " + user.getText()); // Ã§alÄ±ÅŸmÄ±yor burada ama maksat text i
																				// almak
			}
		});

		beginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setDifficulty(1);
				ballColorComboBox.setVisible(true);
				ballLabel.setVisible(true);
				if (normal.isSelected())
					normal.setSelected(false);
				if (insane.isSelected())
					insane.setSelected(false);
				// System.out.println("MOD : BEGINNER");
				difficultyLevel = "BEGINNER";
			}
		});

		normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setDifficulty(2);
				ballColorComboBox.setVisible(false);
				ballLabel.setVisible(false);
				if (beginner.isSelected())
					beginner.setSelected(false);
				if (insane.isSelected())
					insane.setSelected(false);
				// System.out.println("MOD : NORMAL");
				difficultyLevel = "NORMAL";
			}
		});

		insane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setDifficulty(3);
				ballColorComboBox.setVisible(false);
				ballLabel.setVisible(false);
				if (beginner.isSelected())
					beginner.setSelected(false);
				if (normal.isSelected())
					normal.setSelected(false);
				// System.out.println("MOD : INSANE");
				difficultyLevel = "INSANE";

			}

		});
	}
}