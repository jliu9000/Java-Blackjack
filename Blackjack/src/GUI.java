import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GUI extends Applet implements Runnable, MouseListener {

	private static final int WIDTH = 720;
	private static final int HEIGHT = 450;
	private Image background, playerBar, dealerCard, betArrows, beginBtn;
	private URL base;
	private int mouseX, mouseY;
	private int currentPlayer, totalPlayers = 0;
	private int gameStage; // stage 0 = start screen, stage 1 = options, stage 2
							// = in game
	private boolean handDealt; // start play if hand has been delt. reset this
								// boolean at the end of dealer turn
	private Game game;
	private int currentHand, startingBank = 0;
	private String winner;
	private Image image;
	private Graphics graphics;

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

		playerBar = getImage(base, "images/playerbar.png");
		background = getImage(base, "images/start.png");
		gameStage = 0;
		handDealt = false;
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
				if (totalPlayers == 0) {
					background = getImage(base, "images/options.png");
				} else {
					background = getImage(base, "images/options" + totalPlayers
							+ ".png");

				}
				beginBtn = getImage(base, "images/beginbtn.png");

			}

			if (gameStage == 2) {
				background = getImage(base, "images/board0.png");
				betArrows = getImage(base, "images/betarrows.png");
			}

			if (gameStage > 2) {
				background = getImage(base, "images/board.png");
			}

			if (gameStage == 3) {
				// deal
				dealerCard = getImage(base, "images/back.png");

				game.startingDeal();

				if (game.dealer.hands.get(0).getTotal() == 21) {
					gameStage = 5;
					currentPlayer = 0;
				} else {

					gameStage++;
				}
			}

			if (gameStage == 4) {
				// in round

				// if the last player has played, advance the game
				if (currentPlayer > game.users.size()) {
					gameStage++;
					currentPlayer = 0;
				} else if (game.users.get(currentPlayer - 1).startingBet == 0) {
					// skip current player if they did not place a bet
					currentPlayer++;
				} else if (game.users.get(currentPlayer - 1).hands.get(
						currentHand).getValueOfHand() == 21
						&& game.users.get(currentPlayer - 1).hands.get(
								currentHand).getNumberOfCards() == 2) {
					if (game.users.get(currentPlayer - 1).hands.size() > currentHand + 1) {
						currentHand++;
					} else {
						currentPlayer++;
						currentHand = 0;
					}

				}

			}

			if (gameStage == 5) {
				// dealer play

				dealerCard = game.dealer.hands.get(0).cards.get(0).getImage();

				boolean allBust = true;

				for (User u : game.users) {
					for (Hand h : u.hands) {
						if (!h.isBust()) {
							allBust = false;
							break;
						}

					}
				}

				if (!allBust) {
					while (game.dealer.hands.get(0).getValueOfHand() < 17) {

						repaint();
						// small delay before dealer hitting
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						game.dealer.play(game.deck);

					}
				}
				game.dealer.stand();

				winner = game.calculateWinners();

				gameStage++;

			}

			if (gameStage == 6) {
				// print winners - this is done in paint method

			}

			if (gameStage >= 7) {
				// round over, delay, then start new round
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				game.clearHands();
				game.addUserWinnings();
				currentPlayer++;
				for (int i = 0; i < totalPlayers; i++) {
					game.users.get(i).startingBet = 0;
					game.users.get(i).clearWinnings();
				}
				gameStage = 2;

			}

			repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// always display a bg
		g.drawImage(background, 0, 0, this);

		// draw starting bank string
		if (gameStage == 1) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString(Integer.toString(startingBank), 230, 312);
			g.drawImage(beginBtn, 460, 220, this);

		}

		// draw ingame board
		if (gameStage > 1) {

			for (int i = 1; i <= totalPlayers; i++) {
				g.drawString("Player " + i, -70 + 90 * i, 100);
			}
			g.drawString("Dealer ", 285, 295);

		}

		// reset font size
		if (gameStage == 2) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		}

		// always draw bank after options selection
		if (gameStage >= 2) {
			for (int x = 0; x < game.users.size(); x++) {

				// draw starting bank
				g.drawString(
						"Bank: "
								+ Integer.toString(game.users.get(x).getBank()
										.checkMoney()), 14 + 90 * x, 25);
			}

		}

		// betting stage
		if (gameStage == 2) {
			for (int x = 0; x < game.users.size(); x++) {

				// draw bet amount
				g.drawString("Bet: ", 14 + 90 * x, 40);

				g.drawString(Integer.toString(game.users.get(x).startingBet),
						36 + 90 * x, 57);

				// draw bet arrows
				g.drawImage(betArrows, 56 + 90 * x, 36, this);
			}
		}

		// draw cards
		if (gameStage >= 4) {

			// draw bar to show current player
			if (currentPlayer <= totalPlayers) {
				g.drawImage(playerBar, 15 + 90 * (currentPlayer - 1),
						135 + 95 * currentHand, this);
			}

			for (int x = 0; x < game.users.size(); x++) {

				for (int k = 0; k < game.users.get(x).hands.size(); k++) {
					if (!game.users.get(x).hands.isEmpty()) {
						if (game.users.get(x).hands.get(k).checkBlackjack()) {
							g.drawString("Blackjack!", 20 + 90 * x,
									115 + 95 * k);

						} else if (game.users.get(x).hands.get(k)
								.getValueOfHand() > 21) {
							g.drawString(
									"Bust: ("
											+ Integer.toString(game.users
													.get(x).hands.get(k)
													.getTotal()) + ")",
									20 + 90 * x, 115 + 95 * k);
						} else {
							g.drawString(
									"Value: "
											+ Integer.toString(game.users
													.get(x).hands.get(k)
													.getTotal()), 20 + 90 * x,
									115 + 95 * k);
						}
					}

					g.drawString(
							"Bet: "
									+ Integer.toString(game.users.get(x).hands
											.get(k).getBet()), 20 + 90 * x,
							130 + 95 * k);

					for (int j = 0; j < game.users.get(x).hands.get(k).cards
							.size(); j++) {

						g.drawImage(game.users.get(x).hands.get(k).cards.get(j)
								.getImage(), 20 + 90 * x + 10 * j,
								140 + 95 * k, this);
					}
				}

			}

			for (int i = 0; i < game.dealer.hands.get(0).cards.size(); i++) {
				if (gameStage > 4) {
					g.drawString(
							"Value: "
									+ Integer.toString(game.dealer.hands.get(0)
											.getTotal()), 285, 310);
				}
				if (i == 0) {
					g.drawImage(dealerCard, 285, 320, this);
				} else {
					g.drawImage(game.dealer.hands.get(0).cards.get(i)
							.getImage(), 285 + 20 * i, 320, this);
				}
			}

		}
		// print winners if the round is over
		if (gameStage >= 6) {
			for (int i = 0; i < game.users.size(); i++) {
				g.drawString(
						"+" + Integer.toString(game.users.get(i).getMoneyWon()),
						15 + 90 * i, 50);

			}
			g.drawString(winner, 275, 400);
			g.drawString("Starting next round...", 275, 420);
			gameStage++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

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
				} else if (mouseX > 140 && mouseX < 188) {
					totalPlayers = 2;
				} else if (mouseX > 214 && mouseX < 265) {
					totalPlayers = 3;
				} else if (mouseX > 295 && mouseX < 343) {
					totalPlayers = 4;
				} else if (mouseX > 370 && mouseX < 420) {
					totalPlayers = 5;
				} else if (mouseX > 448 && mouseX < 500.) {
					totalPlayers = 6;
				}
			}

			// set starting bank
			if (mouseX > 300 && mouseX < 370) {
				if (mouseY > 260 && mouseY < 302) {
					startingBank += 50;
				} else if (mouseY > 310 && mouseY < 350) {
					if (startingBank >= 50) {
						startingBank -= 50;

					}
				}

			}

			// begin
			if (mouseX > 460 && mouseX < 660) {
				if (mouseY > 220 && mouseY < 420) {
					System.out.println("begin");
					if (totalPlayers > 0 && startingBank > 0) {
						game = new Game(totalPlayers, startingBank);

						gameStage++;
					}
				}
			}

		}

		// log starting bet clicks
		if (gameStage == 2) {
			boolean betSet = false;
			// check if a bet has been set yet
			if (!betSet) {
				for (int i = 0; i < totalPlayers; i++) {
					if (game.users.get(i).startingBet > 0) {
						betSet = true;
					}

				}
			}
			// start the hand if at least 1 bet has been set
			if (mouseX > 550 && mouseX < 700) {
				if (mouseY > 40 && mouseY < 135) {
					if (betSet) {

						gameStage++;
					}
				}
			}

			// increase bet
			if (mouseY > 25 && mouseY < 51) {
				// player 1
				if (mouseX > 54 && mouseX < 78) {
					game.users.get(0).increaseBet();
					// player 2
				} else if (mouseX > 145 && mouseX < 169) {
					if (game.users.size() >= 2) {
						game.users.get(1).increaseBet();
					}

					// player 3
				} else if (mouseX > 235 && mouseX < 259) {
					if (game.users.size() >= 3) {
						game.users.get(2).increaseBet();
					}

					// player 4
				} else if (mouseX > 325 && mouseX < 349) {
					if (game.users.size() >= 4) {
						game.users.get(3).increaseBet();
					}

					// player 5
				} else if (mouseX > 415 && mouseX < 439) {
					if (game.users.size() >= 5) {
						game.users.get(4).increaseBet();
					}

					// player 6
				} else if (mouseX > 505 && mouseX < 529) {
					if (game.users.size() == 6) {
						game.users.get(5).increaseBet();
					}
				}

			}

			// decrease bet
			if (mouseY > 51 && mouseY < 72) {
				// player 1
				if (mouseX > 54 && mouseX < 78) {
					game.users.get(0).decreaseBet();
					// player 2
				} else if (mouseX > 145 && mouseX < 169) {
					if (game.users.size() >= 2) {
						game.users.get(1).decreaseBet();
					}

					// player 3
				} else if (mouseX > 235 && mouseX < 259) {
					if (game.users.size() >= 3) {
						game.users.get(2).decreaseBet();
					}

					// player 4
				} else if (mouseX > 325 && mouseX < 349) {
					if (game.users.size() >= 4) {
						game.users.get(3).decreaseBet();
					}

					// player 5
				} else if (mouseX > 415 && mouseX < 439) {
					if (game.users.size() >= 5) {
						game.users.get(4).decreaseBet();
					}

					// player 6
				} else if (mouseX > 505 && mouseX < 529) {
					if (game.users.size() == 6) {
						game.users.get(5).decreaseBet();
					}
				}

			}

		}

		// help and exit buttons work throughout the ingame stages
		if (gameStage >= 2) {
			if (mouseX > 570 && mouseX < 710) {
				if (mouseY > 400 && mouseY < 438) {
					// exit
					System.exit(0);
				} else if (mouseY > 12 && mouseY < 38) {
					JOptionPane
							.showMessageDialog(
									null,
									"Please place your bet using the arrows on the top row of the game. \n "
											+ "Once your bet is placed used the Set Bets button below to start the deal \n\n"
											+ "While in play, use the buttons below to hit, stand, double down, or split\n"
											+ "You may only double down if you have not hit your on your starting hand \n"
											+ "You may only split if your cards are the same face on the beginning hand \n\n"
											+ "~~ Good Luck! ~~");
				}
			}
		}

		// in game
		if (gameStage == 4 && gameStage < 6
				&& game.users.size() >= currentPlayer) {
			if (mouseX > 570 && mouseX < 710) {
				if (mouseY > 140 && mouseY < 180) {
					// hit

					game.users.get(currentPlayer - 1).hit(game.deck.dealCard(),
							currentHand);
					if (game.users.get(currentPlayer - 1).hands
							.get(currentHand).isBust()) {
						if (game.users.get(currentPlayer - 1).hands.size() > currentHand + 1) {
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

					if (game.users.get(currentPlayer - 1).hands.size() > currentHand + 1) {
						currentHand++;
					} else {
						currentPlayer++;
						currentHand = 0;
					}

					System.out.println("stand");

				} else if (mouseY > 225 && mouseY < 260) {
					// double down
					System.out.println("dd");
					if (game.users.get(currentPlayer - 1).hands
							.get(currentHand).cards.size() > 2) {

					} else {
						boolean ddSuccessful = false;
						ddSuccessful = game.users.get(currentPlayer - 1)
								.doubleDown(game.deck, currentHand);
						if (ddSuccessful) {
							if (game.users.get(currentPlayer - 1).hands.size() > currentHand + 1) {
								currentHand++;
							} else {
								currentPlayer++;
								currentHand = 0;
							}
						}
						
					}
				} else if (mouseY > 260 && mouseY < 300) {
					// split
					game.users.get(currentPlayer - 1).split(game.deck);
				}

			}
		}

	}

	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		paint(graphics);
		g.drawImage(image, 0, 0, this);
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
