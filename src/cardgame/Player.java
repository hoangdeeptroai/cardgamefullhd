package cardgame;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	protected String name;
	protected int balance;
	protected List<Card> hand = new ArrayList<>();

	public enum State {
		TRONG_VONG, BO_LUOT, HET_BAI
	}

	public State playerstate;

	public Player(String name) {
		this.name = name;
		this.balance = 1000; // Số dư mặc định
	}

	public void subBalance(int amount) {
		if (this.balance - amount < 0) {
			return;
		}
		this.balance -= amount;
	}

	public void resetHand() {
		this.hand.clear();
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public State getPlayerstate() {
		return playerstate;
	}

	public void setPlayerstate(State playerstate) {
		this.playerstate = playerstate;
	}

}
