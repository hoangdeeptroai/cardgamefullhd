package cardgame;

public class Dealer extends BlackJackPlayer {
	public Dealer() {

	}

	public Dealer(Deck deck) {
		this.name = "DEALER";
		this.balance = Integer.MAX_VALUE;
		playerstate = State.TRONG_VONG;
		for (int i = 0; i < 2; i++) {
			hit(deck);
		}
		playerstate = State.TRONG_VONG;// người chơi trong lượt chơi của mình
		if (point == 21) {
			playerstate = State.HET_BAI;// số điểm được BlackJack và end
		}
	}

	public void hitToEnd(Deck deck) {
		while (this.point < 17) {
			hit(deck);
		}
	}
}