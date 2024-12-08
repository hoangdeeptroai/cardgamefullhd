package cardgame;

/*
 * Lớp Card là lớp cơ bản của chương trình biểu diễn cho các đối tượng lá bài
 * Một lá bài sẽ có hai thuộc tính là Value(giá trị) và Type(chất)
 */

public class Card {
	// Khai báo 2 enum chứa giá trị quân bài và chất
	public enum Value {
		AT(1), HAI(2), BA(3), BON(4), NAM(5), SAU(6), BAY(7), TAM(8), CHIN(9), MUOI(10), RI(11), QUY(12), KA(13);

		private final int value;

		Value(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum Type {
		CO(4), RO(3), CHUON(2), BICH(1);

		private final int value;

		Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	// Khai báo hai thuộc tính của một lá bài
	private final Value value; // không thay đổi sau khi tạo
	private final Type type; // không thay đổi sau khi tạo

	// Constructor
	public Card() {
		this.value = Value.AT; // Giá trị mặc định
		this.type = Type.BICH; // Giá trị mặc định
	}

	public Card(Value value, Type type) {
		this.value = value;
		this.type = type;
	}

	// Hàm trả về giá trị của lá bài
	public int getValueValue() {
		return value.getValue();
	}

	public int getTypeValue() {
		return type.getValue();
	}

	// Hàm trả về một String chứa thông tin lá bài
	@Override
	public String toString() {
		return value.name() + " of " + type.name();
	}

	// Hàm so sánh giá trị của lá bài
	// Nếu giá trị lá bài hiện tại lớn hơn thì return true
	public boolean compareCard(Card otherCard) {
		if (this.getValueValue() != otherCard.getValueValue()) {
			return this.getValueValue() > otherCard.getValueValue();
		}
		return this.getTypeValue() > otherCard.getTypeValue();
	}

	// Các getter và setter
	public Value getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}
}