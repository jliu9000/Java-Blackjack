import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JOptionPane;

public class GUI extends Applet implements Runnable, MouseListener {

	private static final int WIDTH = 720;
	private static final int HEIGHT = 450;
	private Image background, deck;
	private URL base;
	private int mouseX, mouseY;
	private int currentPlayer, totalPlayers;
	private int gameStage; //stage 0 = start screen, stage 1 = options, stage 2 = in game

	@Override
	public void init() {

		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		setBackground(Color.WHITE);
		addMouseListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Blackjack");

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		background = getImage(base, "images/start.png");
		deck = getImage(base, "images/back.png");
		gameStage = 0;
	}

	@Override
	public void start() {

		Thread thread = new Thread(this);
		thread.start();

	}

	public void run() {
		while (true) {
			if (gameStage == 1) {
				background = getImage(base, "images/options.png");
			}

			if (gameStage > 1) {
				background = getImage(base, "images/board.png");
			}

			repaint();

			try {
				Thread.sleep(350);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		
		//always display a bg
		g.drawImage(background, 0, 0, this);
		
		//draw ingame board
		if (gameStage > 1) {
			g.drawImage(deck, 490, 350, this);
			g.drawString("Round is currently on Player:  ", 25, 400);
			g.drawString(Integer.toString(currentPlayer), 25, 420);
			for (int i = 1; i <= totalPlayers; i++) {
				g.drawString("Player" + i, -70 + 90 * i, 100);
			}
			
			
			for (int i = 1; i <= totalPlayers; i++){
				//draw each player's hand
			}
			
			//draw dealer's hand
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		// in game
		if (gameStage > 1) {
			if (mouseX > 570 && mouseX < 710) {
				if (mouseY > 140 && mouseY < 180) {
					// hit
					System.out.println("hit");
					
				} else if (mouseY > 183 && mouseY < 220) {
					//stand
					System.out.println("stand");

				} else if (mouseY > 225 && mouseY < 260) {
					//double down
					System.out.println("double down");

				} else if (mouseY > 260 && mouseY < 300) {
					//split
					System.out.println("split");

				} else if (mouseY > 400 && mouseY < 438) {
					//exit
					System.exit(0);
				} else if (mouseY > 12 && mouseY < 38){
					JOptionPane.showMessageDialog(null,"do you even blackjack???");
				}

			}
		}

		// start screen
		if (gameStage == 0) {
			if (mouseX > 265 && mouseX < 450) {
				if (mouseY > 200 && mouseY < 250) {
					gameStage = 1;
					System.out.println("++gameStage");
				}

			}
		}

		// options screen
		if (gameStage == 1) {
				if (mouseY > 95  && mouseY < 145) {
					if(mouseX >60 && mouseX <110){
						totalPlayers = 1;
						gameStage = 2;
					} else if(mouseX>140 && mouseX<188){
						totalPlayers = 2;
						gameStage = 2;
					} else if(mouseX>214 && mouseX<265){
						totalPlayers = 3;
						gameStage = 2;
					} else if(mouseX>295 && mouseX<343){
						totalPlayers = 4;
						gameStage = 2;
					} else if(mouseX>370 && mouseX<420){
						totalPlayers = 5;
						gameStage = 2;
					} else if(mouseX>448 && mouseX<500.){
						totalPlayers = 6;
						gameStage = 2;
					}
					
				}

			}

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// dont need
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// dont need

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// dont need

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// dont need

	}
	
	protected void nextPlayer (){
		currentPlayer++;
	}

}
