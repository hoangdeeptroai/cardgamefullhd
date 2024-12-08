package cardgame;

import java.util.List;

import cardgame.Player.State;

public class SupportTools {
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void printOut(List<BlackJackPlayer> players, Dealer dealer) {
		// In tiêu đề
		System.out.println("=============================================");
		System.out.printf("|| %-17s | %-17s ||\n", "Players " + players.get(0).balance + "$", "Dealer");
		System.out.println("=============================================");

		// Tìm số bài lớn nhất trong tất cả người chơi và dealer
		int maxCards = dealer.hand.size();
		for (BlackJackPlayer player : players) {
			maxCards = Math.max(maxCards, player.hand.size());
		}

		// Duyệt qua từng hàng bài
		for (int i = 0; i < maxCards; i++) {
			for (int j = 0; j < players.size(); j++) {
				BlackJackPlayer player = players.get(j);
				// Lấy lá bài của từng người chơi hoặc hiển thị "No Card" nếu không còn bài
				String playerCard = (i < player.hand.size()) ? player.hand.get(i).toString() : "No Card";

				// In bài của từng người chơi
				System.out.printf("|| %-17s", playerCard);
			}

			// Hiển thị bài của dealer (che bài thứ hai)
			String dealerCard = (i < dealer.hand.size()) ? (i == 1 ? "xxxxxxxx" : dealer.hand.get(i).toString())
					: "No Card";
			System.out.printf(" | %-17s ||\n", dealerCard);
		}

		// In tổng điểm
		System.out.println("=============================================");
		for (int j = 0; j < players.size(); j++) {
			BlackJackPlayer player = players.get(j);
			System.out.printf("|| Player %d: %-17s Total Points: %d ||\n", (j + 1), player.getName(),
					player.getPoint());
		}
		System.out.printf("|| Dealer: %-17s Total Points: %d ||\n", dealer.getName(),
				(dealer.hand.size() == 2) ? 0 : dealer.getPoint());
		System.out.println("=============================================");
	}

	public static void resetGame(List<BlackJackPlayer> players, Deck deck, Dealer dealer) {
		// Reset deck
		deck.resetDeck();
		// Reset dealer
		dealer.resetHand();
		dealer.initHand(deck);
		// Reset players
		for (BlackJackPlayer player : players) {
			player.resetHand();
			player.setPlayerstate(State.TRONG_VONG);
			player.initHand(deck);
		}
	}
}
