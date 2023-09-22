package enemies;
import java.util.Random;
public class Difficulty {

	private int id;
	private String name;
	private int damage;
	private int health;
	private int awardMoney;
	private int defaultHealth;

	public Difficulty(int id, String name, int damage, int health, int awardMoney) {
		this.id = id;
		this.name = name;
		this.damage = damage;
		this.health = health;
		this.awardMoney = awardMoney;
		this.defaultHealth = health;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setHealth(int health) {
		if (health < 0) {
			health = 0;
		}
		this.health = health;
	}

	public int getAwardMoney() {
		return awardMoney;
	}

	public void setAwardMoney(int awardMoney) {
		this.awardMoney = awardMoney;
	}

	public int getDefaultHealth() {
		return defaultHealth;
	}

	@Override
	public String toString() {
		return "\u001B[33mCanavarın Özellikleri :\n" + " Adı = " + this.getName() + ", Hasarı = " + this.getDamage()
				+ ", Canı = " + this.getHealth() + ", Ödülü = " + this.getAwardMoney() + "\u001B[0m";
	}
}
