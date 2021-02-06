package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;


public class TwoPlayerFrame extends JFrame {

	public static String player1;
	public static String player2;
	public static boolean control;
	public static boolean control2;
	public static Color ballColor = Color.WHITE;
	public static Color paddleColorLeft = Color.WHITE;
	public static Color paddleColorRight = Color.WHITE;
	private static final int frameWidth = 800;
	private static final int frameHeight = 400;
	public static GameFrame game;
	public static int scoreLimit;

	TwoPlayerFrame() {

		final JLabel panel = new JLabel(new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/tpb.png"));
		this.setTitle("2 PLAYER MENU");
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

		Icon icon = new ImageIcon("/Users/****/eclipse-workspace/sysout/src/pong/back.png");
		JButton back = new JButton(icon);
		back.setBounds(10, 310, 50, 50);
		panel.add(back);

		JLabel title = new JLabel();
		title.setText("2 PLAYER MENU");
		title.setFont(new java.awt.Font("Arial Black", Font.BOLD, 20));
		title.setOpaque(true);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.RED);
		title.setBounds(310, 20, 200, 20);
		panel.add(title);

		JLabel paddleLabelLeft = new JLabel();
		paddleLabelLeft.setText("Left Paddle Color");
		paddleLabelLeft.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		paddleLabelLeft.setOpaque(true);
		paddleLabelLeft.setBackground(Color.BLACK);
		paddleLabelLeft.setForeground(Color.WHITE);
		paddleLabelLeft.setBounds(20, 250, 110, 30);
		panel.add(paddleLabelLeft);

		JLabel paddleLabelRight = new JLabel();
		paddleLabelRight.setText("Right Paddle Color");
		paddleLabelRight.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		paddleLabelRight.setOpaque(true);
		paddleLabelRight.setBackground(Color.BLACK);
		paddleLabelRight.setForeground(Color.WHITE);
		paddleLabelRight.setBounds(680, 250, 110, 30);
		panel.add(paddleLabelRight);

		JLabel ballLabel = new JLabel();
		ballLabel.setText("Ball Color");
		ballLabel.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		ballLabel.setOpaque(true);
		ballLabel.setBackground(Color.BLACK);
		ballLabel.setForeground(Color.WHITE);
		ballLabel.setBounds(370, 210, 60, 28);
		panel.add(ballLabel);

		JLabel spinnerLabel = new JLabel();
		spinnerLabel.setText("Score Limit");
		spinnerLabel.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
		spinnerLabel.setOpaque(true);
		spinnerLabel.setBackground(Color.BLACK);
		spinnerLabel.setForeground(Color.WHITE);
		spinnerLabel.setBounds(369, 140, 60, 28);
		panel.add(spinnerLabel);

		String[] colors = new String[] { "WHITE", "BLUE", "RED", "YELLOW" };

		final JComboBox<String> paddleColorComboBoxLeft = new JComboBox<>(colors);
		paddleColorComboBoxLeft.setBounds(20, 265, 100, 50);
		paddleColorComboBoxLeft.setForeground(Color.WHITE);
		panel.add(paddleColorComboBoxLeft);

		final JComboBox<String> paddleColorComboBoxRight = new JComboBox<>(colors);
		paddleColorComboBoxRight.setBounds(680, 265, 100, 50);
		paddleColorComboBoxRight.setForeground(Color.WHITE);
		panel.add(paddleColorComboBoxRight);

		final JComboBox<String> ballColorComboBox = new JComboBox<>(colors);
		ballColorComboBox.setBounds(350, 225, 100, 50);
		ballColorComboBox.setForeground(Color.WHITE);
		panel.add(ballColorComboBox);

		final JTextField user1 = new JTextField(20);
		user1.setBounds(20, 20, 100, 30);
		panel.add(user1);

		final JTextField user2 = new JTextField(20);
		user2.setBounds(670, 20, 100, 30);
		panel.add(user2);

		SpinnerModel spinnerModel = new SpinnerNumberModel(5, // initial value
				1, // min
				10, // max
				1);// step
		final JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(375, 160, 50, 40);
		spinner.setBackground(Color.BLACK);
		spinner.setForeground(Color.WHITE);
		panel.add(spinner);

		this.setVisible(true);

		this.add(panel);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (user1.getText().length() > 1)
					player1 = user1.getText();
				else
					player1 = "Player1";
				if (user2.getText().length() > 1)
					player2 = user2.getText();
				else
					player2 = "Player2";
				scoreLimit = (int) spinner.getValue();
				System.out.println("************************************");
				System.out.println(player1 + " VS " + player2);
				System.out.println("Score Limit: " + scoreLimit);
				System.out.println("Ball Color: " + (String) ballColorComboBox.getSelectedItem());
				System.out.println(player1 + "'s Paddle Color: " + (String) paddleColorComboBoxLeft.getSelectedItem());
				System.out.println(player2 + "'s Paddle Color: " + (String) paddleColorComboBoxRight.getSelectedItem());
				System.out.println("************************************");
				game = new GameFrame();
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

		paddleColorComboBoxLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String selectedColor = (String) paddleColorComboBoxLeft.getSelectedItem();
			//	System.out.println("You selected the color: " + selectedColor);
				if (selectedColor.equals("BLUE")) {
					paddleColorLeft = Color.BLUE;
					paddleColorComboBoxLeft.setForeground(Color.BLUE);

				}
				if (selectedColor.equals("WHITE")) {
					paddleColorLeft = Color.WHITE;
					paddleColorComboBoxLeft.setForeground(Color.WHITE);
				}
				if (selectedColor.equals("RED")) {
					paddleColorLeft = Color.RED;
					paddleColorComboBoxLeft.setForeground(Color.RED);
				}
				if (selectedColor.equals("YELLOW")) {
					paddleColorLeft = Color.YELLOW;
					paddleColorComboBoxLeft.setForeground(Color.YELLOW);
				}

			}
		});

		paddleColorComboBoxRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String selectedColor = (String) paddleColorComboBoxRight.getSelectedItem();
			//	System.out.println("You selected the color: " + selectedColor);
				if (selectedColor.equals("BLUE")) {
					paddleColorRight = Color.BLUE;
					paddleColorComboBoxRight.setForeground(Color.BLUE);

				}
				if (selectedColor.equals("WHITE")) {
					paddleColorRight = Color.WHITE;
					paddleColorComboBoxRight.setForeground(Color.WHITE);
				}
				if (selectedColor.equals("RED")) {
					paddleColorRight = Color.RED;
					paddleColorComboBoxRight.setForeground(Color.RED);
				}
				if (selectedColor.equals("YELLOW")) {
					paddleColorRight = Color.YELLOW;
					paddleColorComboBoxRight.setForeground(Color.YELLOW);
				}

			}
		});

		ballColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String selectedColor = (String) ballColorComboBox.getSelectedItem();
			//	System.out.println("You selected the color: " + selectedColor);
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
					// System.out.println("The entered text is: " + user.getText()); deneme yaptým
					// kalsýn.
				}

			}
		});

		user1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("The entered text is: " + user1.getText()); // klavye keyine setledik
				control = true;
			}
		});

		user2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("The entered text is: " + user2.getText()); // klavye keyine setledik
			}
		});

	}

}