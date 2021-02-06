package pong;

import java.awt.*;

public class Score extends Rectangle{

	static int gameWidth;
	static int gameHeight;
	int player1;
	int player2;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.gameWidth = GAME_WIDTH;
		Score.gameHeight = GAME_HEIGHT;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		g.drawLine(gameWidth/2, 0, gameWidth/2, gameHeight);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (gameWidth/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (gameWidth/2)+20, 50);
	}
}
