package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private int betInDeck;
	private final List<Card> pack;// túi bài có 52 lá
	// phương thức khởi tạo ra bàn chơi Deck,
	// ban đầu trong Deck sẽ có bộ bài 52 lá khác nhau và sẽ tự động xáo trộn bộ bài
	// danh sách những người chơi
	public List<BlackJackPlayer> players;

	public Deck() {
		pack = new ArrayList<>();
		players = new ArrayList<>();
		initializeDeck();
	}

	// Khởi tạo lại bộ bài
	public void resetDeck() {
		pack.clear();
		initializeDeck();
	}

	// khởi tạo bộ bài ban đầu
	private void initializeDeck() {
		for (Card.Type type : Card.Type.values()) {
			for (Card.Value value : Card.Value.values()) {
				pack.add(new Card(value, type));
			}
		}
		Collections.shuffle(pack);
	}

	// chia bài ra từ bộ bài
	public Card dealCard() {
		if (pack.isEmpty()) {
			throw new IllegalStateException("Không còn bài trong pack");
		}
		return pack.remove(0);
	}

	public int cardsLeft() {
		return pack.size();
	}

	public void addPlayer(BlackJackPlayer newPlayer) {
		players.add(newPlayer);
	}

	public Player remmovePlayer() {
		return players.remove(players.size() - 1);
	}

	// getter && setter
	public int getBetInDeck() {
		return betInDeck;
	}

	public void setBetInDeck(int betInDeck) {
		this.betInDeck = betInDeck;
	}

}