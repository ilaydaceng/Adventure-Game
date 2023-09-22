package stock;

import stock.Armor;
import stock.BiggestArmor;
import stock.SmallestArmor;
import stock.MiddleArmor;

public class Armor {
	private int id;
	private String name;
	private int defence;
	private int money;

	public Armor(int id, String name, int defence, int money) {
		this.id = id;
		this.name = name;
		this.defence = defence;
		this.money = money;
	}

	public static Armor[] armorList() {
		return new Armor[] { new BiggestArmor(), new MiddleArmor(), new SmallestArmor() };
	}

	public static Armor getArmorById(int number) {
		Armor arm = null;
		for (Armor armor : Armor.armorList()) {
			if (armor.getId() == number) {
				arm = armor;
			}
		}
		return arm;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDefence() {
		return defence;
	}

	public int getMoney() {
		return money;
	}
}
