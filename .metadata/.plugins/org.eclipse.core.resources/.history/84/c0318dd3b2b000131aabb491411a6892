import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GUI extends Applet implements Runnable, MouseListener {

	private static final int WIDTH = 720;
	private static final int HEIGHT = 450;
	private Image background;
	private URL base;
	private int mouseX, mouseY;
	private int currentPlayer, totalPlayers;
	private int gameStage; // stage 0 = start screen, stage 1 = options, stage 2
							// = in game
	private boolean handDelt; // start play if hand has been delt. reset this
								// boolean at the end of dealer turn
	private Game game;
	private int currentHand;
	private String winner;
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
		gameStage = 0;
		handDelt = false;
		currentPlayer = 1;
		currentHand = 0;
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

			if (gameStage == 3) {
				// deal
				game.startingDeal();
				gameStage++;
			}

			if (gameStage == 4) {
				// in round
				if (currentPlayer > game.users.size()) {
					gameStage++;
					currentPlayer = 0;
				}
				// if the last player has played, advance the game

			}

			if (gameStage == 5) {
				// dealer play
				game.dealer.play(game.deck);
				winner = game.calculateWinners();
				gameStage++;

			}

			if (gameStage == 6) {
				// print winners
				

			}

			if (gameStage == 7) {
				// round over, delay, then start new round
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				game.clearHands();
				currentPlayer++;
				gameStage = 3;

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

		// always display a bg
		g.drawImage(background, 0, 0, this);

		// draw ingame board
		if (gameStage > 1) {
			g.drawString("Round is currently on Player:  ", 25, 400);
			g.drawString(Integer.toString(currentPlayer), 25, 420);
			for (int i = 1; i <= totalPlayers; i++) {
				g.drawString("Player " + i, -70 + 90 * i, 100);
			}
			g.drawString("Dealer ", 285, 295);
		}

		// draw cards
		if (gameStage >= 4) {
			// for (int i = 1; i <= totalPlayers; i++) {
			for (int x = 0; x < game.users.size(); x++) {
				for (int k = 0; k < game.users.get(x).hands.size(); k++) {
					for (int j = 0; j < game.users.get(x).hands.get(k).cards
							.size(); j++) {
						g.drawImage(game.users.get(x).hands.get(k).cards.get(j)
								.getImage(), 20 + 90 * x + 10 * j,
								120 + 80 * k, this);
					}
				}

				// }
			}

			for (int i = 0; i < game.dealer.hands.get(0).cards.size(); i++) {
				g.drawImage(game.dealer.hands.get(0).cards.get(i).getImage(),
						285 + 20 * i, 320, this);
			}

		}
		//print winners if the round is over
		if (gameStage >= 6) {
			g.drawString(winner, 275, 400);
			g.drawString("Starting next round...", 275, 420);

			gameStage++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		// help and exit buttons work throughout the ingame stages
		if (gameStage > 2) {
			if (mouseX > 570 && mouseX < 710) {
				if (mouseY > 400 && mouseY < 438) {
					// exit
					System.exit(0);
				} else if (mouseY > 12 && mouseY < 38) {
					JOptionPane
							.showMessageDialog(null,
									"Use the buttons on the right side to play blackjack");
				}
			}
		}

		// in game
		if (gameStage == 4 && gameStage < 6) {
			if (mouseX > 570 && mouseX < 710) {
				if (mouseY > 140 && mouseY < 180) {
					// hit

					game.users.get(currentPlayer - 1).hands.get(currentHand)
							.addCard(game.deck.dealCard());

					if (game.users.get(currentPlayer - 1).hands
							.get(currentHand).isBust()) {
						if (game.users.get(currentPlayer - 1).hands.size() != currentHand+1) {
							currentHand++;
						} else {
							currentPlayer++;
							currentHand = 0;
						}
					}
					System.out.println("hit");

				} else if (mouseY > 183 && mouseY < 220) {
					// stand
					game.users.get(currentPlayer - 1).stand();

					// if this user has no more hands, then move on to next user
					// if (game.users.get(currentPlayer - 1).numberOfHands == 0)
					// {
					// currentPlayer++;
					// }

					if (game.users.get(currentPlayer - 1).hands.size() != currentHand+1) {
						currentHand++;
					} else {
						currentPlayer++;
						currentHand = 0;
					}

					System.out.println("stand");

				} else if (mouseY > 225 && mouseY < 260) {
					// double down
					game.users.get(currentPlayer - 1).doubleDown(game.deck);

					if (game.users.get(currentPlayer - 1).hands.size() != currentHand+1) {
						currentHand++;
					} else {
						currentPlayer++;
						currentHand = 0;
					}

				} else if (mouseY > 260 && mouseY < 300) {
					// split
					game.users.get(currentPlayer - 1).split(game.deck);

				}

			}
		}

		// start screen
		if (gameStage == 0) {
			if (mouseX > 265 && mouseX < 450) {
				if (mouseY > 200 && mouseY < 250) {
					gameStage = 1;
				}

			}
		}

		// options screen
		if (gameStage == 1) {
			if (mouseY > 95 && mouseY < 145) {
				if (mouseX > 60 && mouseX < 110) {
					totalPlayers = 1;
					gameStage = 2;
				} else if (mouseX > 140 && mouseX < 188) {
					totalPlayers = 2;
					gameStage = 2;
				} else if (mouseX > 214 && mouseX < 265) {
					totalPlayers = 3;
					gameStage = 2;
				} else if (mouseX > 295 && mouseX < 343) {
					totalPlayers = 4;
					gameStage = 2;
				} else if (mouseX > 370 && mouseX < 420) {
					totalPlayers = 5;
					gameStage = 2;
				} else if (mouseX > 448 && mouseX < 500.) {
					totalPlayers = 6;
					gameStage = 2;
				}

			}

			// transition from options screen, must deal hand and start round if
			// this occurs
			if (gameStage == 2) {
				game = new Game(totalPlayers);
				gameStage++;
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

	protected void nextPlayer() {
		currentPlayer++;
	}

}
