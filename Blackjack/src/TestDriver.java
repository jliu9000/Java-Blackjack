public class TestDriver {

	//test deck consistency
	public static void main(String args[]) {
		Deck d = new Deck(52);

		d.printDeckCards();

		for (int i = 0; i < 10; i++) {
			d.shuffleDeck();
			d.printDeckCards();
		}

	}

}
