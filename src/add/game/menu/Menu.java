package add.game.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import app.game.graphics.Board;
import app.game.pawns.PawnColors;
import app.game.players.Player;

public class Menu {

	
	static String AfficherMenu(){
		try
	    {
			
	      // Le fichier d'entrée pour afficher le menu du jeu
	      FileInputStream file = new FileInputStream("res/menu.txt");   
	      Scanner scanner = new Scanner(file);  
	      
	      //renvoie true s'il y a une autre ligne à lire
	      while(scanner.hasNextLine())
	      {
	        System.out.println(scanner.nextLine());
	      }
	      scanner.close();    
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	  
		try {
			int choixMenu = readIntMenu();
		} catch (Exception e) {
			System.out.println("Nous n'avons pas compris votre choix.");
			int choixMenu = readIntMenu();
		}
		return null;   
		

	}

	
    // Renvoie l'entier associé au choix de l'utilisateur, s'il est valide
    public static int readIntMenu(){
        boolean demande = true;
        int entree = 0;

        System.out.println("Votre choix : [1], [2] ou [3] ?");
        while(demande){
        	
        	Scanner reponse = new Scanner(System.in);
        	entree = reponse.nextInt();
            if(entree == 1){
                System.out.println("Lancement de la partie :");
                    demande = false;
                    System.out.println("Quel est le nom du premier joueur ?");
                    Scanner p1 = new Scanner(System.in);
                    String m_nickname1 = p1.nextLine();
                    Player Player1 = new Player(m_nickname1,PawnColors.WHITE);
                    System.out.println(Player1.getNickname()+" "+ "a les pions blanc.");
                    
                    System.out.println("Quel est le nom du deuxième joueur ?");
                    Scanner p2 = new Scanner(System.in);
                    String m_nickname2 = p2.nextLine();
                    Player Player2 = new Player(m_nickname2,PawnColors.BLACK);
                    System.out.println(Player2.getNickname()+" "+ "a les pions noir.");
                    System.out.println("La partie commence !");
                    demande = false;
   
                }else if(entree == 2) {
                	AfficherRegles();
                	demande = false;
                }else if(entree == 3) {
                	System.out.println("Quitter le jeu");
                	demande = false;
                }else{
                    System.out.println("Veuillez saisir 1, 2 ou 3");
                }
        }
		return entree;
    }
    
    
    // Afficher les regles
    
    static String AfficherRegles(){
		try
	    {
			
	      // Le fichier d'entrée pour afficher le menu du jeu
	      FileInputStream file = new FileInputStream("res/regles.txt");   
	      Scanner scanner = new Scanner(file);  
	      
	      //renvoie true s'il y a une autre ligne à lire
	      while(scanner.hasNextLine())
	      {
	        System.out.println(scanner.nextLine());
	      }
	      scanner.close();    
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	  
		try {
			System.out.println();
			int choixRegles = readIntMenu();
			if(choixRegles == 1) {
				System.out.println("Support en cours de rédaction.");
				AfficherRegles();
			}
		} catch (Exception e) {
			System.out.println("Nous n'avons pas compris votre choix.");
			int choixRegles = readIntRegles();
		}
		return null;   
	
	}
    
    
    // Renvoie l'entier associé au choix de l'utilisateur, s'il est valide
    public static int readIntRegles(){
        boolean demande = true;
        int entree = 0;

        System.out.println("Votre choix : [1], [2] ou [3] ?");
        while(demande){
        	
        	Scanner reponse = new Scanner(System.in);
        	entree = reponse.nextInt();
            if(entree == 1){
                System.out.println("Document en cours de redaction.");
                    demande = false;
                    readIntRegles();
   
                }else if(entree == 2) {
                	AfficherMenu();
                	demande = false;
                }else if(entree == 3) {
                	System.out.println("Quitter le jeu");
                	demande = false;
                }else{
                    System.out.println("Veuillez saisir 1, 2 ou 3");
                }
        }
		return entree;
    }

	
    public static void main(String[] args) {
    
    	AfficherMenu();
    
    }
}
