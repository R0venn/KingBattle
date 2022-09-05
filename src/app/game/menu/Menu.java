package app.game.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import app.game.GameManager;
import app.game.graphics.Board;
import app.game.pawns.PawnColors;
import app.game.players.Player;
import core.Utils;

public class Menu {

	
	public static String AfficherMenu(){
		Utils.clearScreen();
		try
	    {
			
	      // Le fichier d'entr�e pour afficher le menu du jeu
	      FileInputStream file = new FileInputStream("/home/infoetu/cedric.larsonnier.etu/eclipse-workspace/groupe-16/res/menu.txt");   
	      Scanner scanner = new Scanner(file);  
	      
	      //renvoie true s'il y a une autre ligne � lire
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

	
    // Renvoie l'entier associ� au choix de l'utilisateur, s'il est valide
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
                    
                    System.out.println("Quel est le nom du deuxi�me joueur ?");
                    Scanner p2 = new Scanner(System.in);
                    String m_nickname2 = p2.nextLine();
                    Player Player2 = new Player(m_nickname2,PawnColors.BLACK);
                    System.out.println(Player2.getNickname()+" "+ "a les pions noir.");
                    System.out.println("La partie commence !");
                    GameManager game = new GameManager(m_nickname1,m_nickname2);
                    game.startGame();
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
			
	      // Le fichier d'entr�e pour afficher le menu du jeu
	      FileInputStream file = new FileInputStream("res/regles.txt");   
	      Scanner scanner = new Scanner(file);  
	      
	      //renvoie true s'il y a une autre ligne � lire
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
			int choixRegles = readIntRegles();
			if(choixRegles == 1) {
				System.out.println("Support en cours de r�daction.");
				AfficherPions();
			}
		} catch (Exception e) {
			System.out.println("Nous n'avons pas compris votre choix.");
			int choixRegles = readIntRegles();
		}
		return null;   
	
	}
    
    
    // Renvoie l'entier associ� au choix de l'utilisateur, s'il est valide
    public static int readIntRegles(){
        boolean demande = true;
        int entree = 0;

        System.out.println("Votre choix : [1], [2] ou [3] ?");
        while(demande){
        	
        	Scanner reponse = new Scanner(System.in);
        	entree = reponse.nextInt();
            if(entree == 1){
                    demande = false;
                    AfficherPions();
   
                }else if(entree == 2) {
                	AfficherMenu();
                	demande = false;
                }else if(entree == 3) {
                	System.out.println("Merci d'avoir jour, a bientot !");
                	demande = false;
                }else{
                    System.out.println("Veuillez saisir 1, 2 ou 3");
                }
        }
		return entree;
    }
    
    
    // Renvoie l'entier associ� au choix de l'utilisateur, s'il est valide
    public static int readIntPions(){
        boolean demande = true;
        int entree = 0;

        System.out.println("Votre choix : [1], [2] ou [3] ?");
        while(demande){
        	
        	Scanner reponse = new Scanner(System.in);
        	entree = reponse.nextInt();
            if(entree == 1){
                    demande = false;
                    AfficherRegles();
   
                }else if(entree == 2) {
                	AfficherMenu();
                	demande = false;
                }else if(entree == 3) {
                	System.out.println("Merci d'avoir jour, a bientot !");
                	demande = false;
                }else{
                    System.out.println("Veuillez saisir 1, 2 ou 3");
                }
        }
		return entree;
    }

    
    static String AfficherPions(){
		try
	    {
			
	      // Le fichier d'entr�e pour afficher le menu du jeu
	      FileInputStream file = new FileInputStream("res/pions.txt");   
	      Scanner scanner = new Scanner(file);  
	      
	      //renvoie true s'il y a une autre ligne � lire
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
			int choixPions = readIntPions();
		} catch (Exception e) {
			System.out.println("Nous n'avons pas compris votre choix.");
			int choixPions = readIntPions();
		}
		return null;   
	}
    

	
    public static void main(String[] args) {
    
    	AfficherMenu();
    
    }
}
