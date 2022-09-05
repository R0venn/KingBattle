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

public class SwitchWeaponCard implements ICard{

	@Override
	public String getEffect() {	
		return "Permet à une pièce sélectionnée d'obtenir aléatoirementl'arme d'une autre pièce. (Permanent)";
	}

	@Override
	public void use(Game game) {
		BasePawn currentPawn = game.getCurrentPlayer().getCurrentPawn();
		Random rdmValue = new Random();
		BasePawn[] pawnList = new BasePawn[] {new King(), new Knight(), new Rook(), new Bishop(), new Pawn()};
		// on affecta à la pièce courrante l'arme d'une pièce chosie aléatoirement parmi toutes les pièces disponibles.
		currentPawn.setWeapon(pawnList[rdmValue.nextInt(0, pawnList.length)].getWeapon());
	}

}
