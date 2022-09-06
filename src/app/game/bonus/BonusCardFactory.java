package app.game.bonus;

import core.Utils;

public class BonusCardFactory {
	public static final int CARDS_AMOUNT = 6;

	public static ICard getRandomCard() {
		ICard card;
		int rand = Utils.randInt(0, CARDS_AMOUNT);
		switch(rand) {
			case 1:
				card = new AddArmorCard();
				break;
			case 2:
				card = new AddMPCard();
				break;
			case 3:
				card = new CriticalDmgCard();
				break;
			case 4:
				card = new HealingCard();
				break;
			case 5:
				card = new SwitchWeaponCard();
				break;
			case 6:
				card = new AddAPCard();
				break;
			default:
				card = new AddArmorCard();
				break;
		}
		return card;
	}
}
