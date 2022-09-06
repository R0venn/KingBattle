package app.game.bonus;

import java.util.Random;

import app.game.Game;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.King;
import app.game.pawns.Knight;
import app.game.pawns.Pawn;
import app.game.pawns.Queen;
import app.game.pawns.Rook;
import app.game.weapons.BaseWeapon;
import core.Utils;

public class SwitchWeaponCard implements ICard{

	@Override
	public String getEffect() {	
		return "Change l'arme de votre pièce actuelle pour une arme aléatoire.";
	}

	@Override
	public void use(Game game) {
		BasePawn currentPawn = game.getCurrentPlayer().getCurrentPawn();
		BasePawn[] pawnList = new BasePawn[] {new King(), new Knight(), new Rook(), new Bishop(), new Pawn(), new Queen()};
		BaseWeapon weapon = pawnList[Utils.randInt(0, pawnList.length)].getWeapon();
		currentPawn.setWeapon(weapon);
		Utils.info("Votre " + currentPawn + " a obtenu un : " + weapon);
		Utils.sleep(2);
	}

}
