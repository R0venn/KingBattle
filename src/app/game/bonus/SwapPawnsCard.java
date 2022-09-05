package app.game.bonus;

import app.game.Game;
import app.game.pawns.BasePawn;
import app.game.players.Player;
import core.Utils;

public class SwapPawnsCard implements ICard{

	@Override
	public String getEffect() {
		return "Swap les positions entre deux pièces sélectionnées.";
	}

	@Override
	public void use(Game game) {
		BasePawn currentPawn = game.getCurrentPlayer().getCurrentPawn();
		BasePawn targetPawn = null;
		Player currentPlayer = game.getCurrentPlayer();
		// on stocke les coordonnées de la pièce courante
		int currentPawnPosX = currentPawn.getX();
		int currentPawnPosY = currentPawn.getY();
		// on stocke les coordonnées de la pièce ciblée
		int targetPawnPosX = 0;
		int targetPawnPosY = 0;
		// on demande au joueur courant les coordonnées (alphanumérique) de la pièce avec laquelle il souhaite échanger les positions
		do {
			Utils.info("Avec quelle pièce souhaitez échanger les positions ? Entrez les coordonnées : ");
			Utils.info("Vous ne pouvez pas sélectionner votre KING");
			int[] coordinates = currentPlayer.askCoordinates();
			// on stocke les coordonnées de la pièces ciblées
			targetPawnPosX = coordinates[0];
			targetPawnPosY = coordinates[1];
			// si les coordonnées ciblées sont valides, alors on peut procéder à l'échange des pièces
			if (game.getBoard().isPawn(targetPawnPosX, targetPawnPosY)) {
				// on récupère la pièce ciblée
				targetPawn = game.getCurrentPlayer().getPawnFromPos(targetPawnPosX, targetPawnPosY);	
			}				
		} while(targetPawnPosX == currentPlayer.getKing().getX() && targetPawnPosY == currentPlayer.getKing().getY());
		 // on échange les positions
		currentPawn.setX(targetPawnPosX); currentPawn.setY(targetPawnPosY);
		targetPawn.setX(currentPawnPosX); targetPawn.setX(currentPawnPosY);
	}

}
