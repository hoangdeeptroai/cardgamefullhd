package cardgame;

public class BlackJackPlayer extends Player {
	protected int point;
	private int bet;

	public BlackJackPlayer() {
		super("spilted_player");
	}

	// phương thức khởi tạo ra người chơi, nạp tên player,
	// thêm 2 lá vào hand của người chơi và tính point ban đầu
	public BlackJackPlayer(String name, Deck deck) {
		super(name);
		this.bet = deck.getBetInDeck();
		this.balance -= bet;
		this.point = 0;
		initHand(deck);
	}

	// rút 1 lá từ pack
	public void hit(Deck deck) {
		Card temp = deck.dealCard();
		hand.add(temp);
		int cardValue = temp.getValueValue();

		// Xử lý lá Át và lá hình
		if (cardValue == 1) {
			this.point += (this.point + 11 <= 21) ? 11 : 1;
		} else if (cardValue > 10) { // Quân bài hình J, Q, K
			this.point += 10;
		} else {
			this.point += cardValue;
		}

		if (point > 21) {
			playerstate = State.BO_LUOT; // Người chơi bị Bust
		}
		if (point == 21) {
			playerstate = State.HET_BAI;
		}
	}

	// thao tác stand
	public void stand() {
		playerstate = State.BO_LUOT;
	}

	// thao tác double
	public void d0uble(Deck deck) {
		hit(deck);
		playerstate = State.BO_LUOT;
	}

	// thao tác split nếu ban đầu trên tay có 2 lá cùng giá trị
	public Card split() {
		if (hand.size() >= 2) {
			return this.hand.remove(0);
		}
		return null;
	}

	public boolean checkSplit() {
		if (this.hand.size() == 2 && this.hand.get(0).getValueValue() == this.hand.get(1).getValueValue()) {
			return true;
		}
		return false;
	}

	public boolean checkBust() {
		if (point > 21) {
			playerstate = State.BO_LUOT;
			return true;
		}
		return false;
	}

	public boolean checkBlackJack() {
		if (point == 21) {
			return true;
		}
		return false;
	}

	public void initHand(Deck deck) {
		for (int i = 0; i < 2; i++) {
			hit(deck);
		}
		playerstate = State.TRONG_VONG;// người chơi trong lượt chơi của mình
		if (point == 21) {
			playerstate = State.HET_BAI;// số điểm được BlackJack và end
		}
	}

	public boolean isWinner(Dealer dealer) {
		if (this.point > dealer.point) {
			System.out.println("người chơi đã chiến thắng!");
			return true;
		}
		System.out.println("Người chơi đã thua cuộc!0000");
		return false;
	}

	public int calBonusCash(Dealer dealer) {
		if (isWinner(dealer)) {
			if (checkBlackJack()) {
				return bet * 3;
			} else {
				return bet * 2;
			}
		} else {
			if (this.point == dealer.point) {
				return bet;
			} else {
				return 0;
			}
		}
	}

	@Override
	public void resetHand() {
		this.hand.clear();
		this.point = 0;
	}

	// hàm getter setter các thuộc tính trong class
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
}