package cardgame;

import java.util.Scanner;

import cardgame.Player.State;

public class App {
	public static void main(String[] args) {
		Deck start = new Deck();
		Dealer dealer = new Dealer(start);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập số người chơi: ");
		int numPlayer = scanner.nextInt();
		String temp = scanner.nextLine();
		System.out.println("Nhập tên các người chơi: ");
		for (int i = 0; i < numPlayer; i++) {
			String name = scanner.nextLine();
			BlackJackPlayer player0 = new BlackJackPlayer(name, start);
			start.players.add(player0);
		}
		System.out.println("Nhập mức cược của bạn($$$): ");
		int betInDeck = scanner.nextInt();
		start.setBetInDeck(betInDeck);
		while (true) {
			SupportTools.clearScreen();
			SupportTools.printOut(start.players, dealer);
			for (int i = 0; i < start.players.size(); i++) {
				BlackJackPlayer x = (BlackJackPlayer) start.players.get(i);
				while (x.playerstate == State.TRONG_VONG) {
					System.out.println("Hãy lựa chọn yêu cầu của bạn: ");
					System.out.println("1-Hit!");
					System.out.println("2-Stand!");
					System.out.println("3-Double!");
					if (x.checkSplit()) {
						System.out.println("4-Split!");
					}
					System.out.println("Nhập(1\2\3\4): ");
					int choosen = scanner.nextInt();
					switch (choosen) {
					case 1: {
						x.hit(start);
						break;
					}
					case 2: {
						x.stand();
						break;
					}
					case 3: {
						x.d0uble(start);
						betInDeck *= 2;
						break;
					}
					case 4: {
						if (!x.checkSplit()) {
							break;
						}
						BlackJackPlayer splitedPlayer = new BlackJackPlayer();
						splitedPlayer.hand.add(x.split());
						splitedPlayer.point = splitedPlayer.hand.get(0).getValueValue();
						start.players.add(splitedPlayer);
						x.point /= 2;
						break;
					}
					default: {
						System.out.println("Lựa chọn không hợp lệ!");
						break;
					}
					}
					SupportTools.clearScreen();
					SupportTools.printOut(start.players, dealer);
					if (x.checkBust()) {
						System.out.println("Bạn đã bị BUST");
						x.playerstate = State.BO_LUOT;
					}
					if (x.checkBlackJack()) {
						System.out.println("Bạn đã được BlackJack");
						x.playerstate = State.HET_BAI;
					}
				}
			}
			for (BlackJackPlayer y : start.players) {
				if (y.checkBust()) {
					System.out.println("Dealer đã thắng!1");
					y.subBalance(start.getBetInDeck());
					break;
				}
				dealer.hitToEnd(start);
				y.isWinner(dealer);
				y.balance += y.calBonusCash(dealer);
				SupportTools.clearScreen();
				SupportTools.printOut(start.players, dealer);
			}
			System.out.println("Bạn muốn tiếp tục chứ(1/0)?");
			int isRetry = scanner.nextInt();
			if (isRetry == 0) {
				System.out.println("Hẹn gặp lại!!!");
				break;
			}
			SupportTools.resetGame(start.players, start, dealer);
		}
		scanner.close();
	}
}
