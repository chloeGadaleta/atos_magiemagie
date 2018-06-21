/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.main;

import atos.magieMagie.Dao.JoueurDAO;
import atos.magieMagie.Dao.PartieDAO;
import atos.magieMagie.Entity.Carte;
import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.CarteService;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import java.awt.PointerInfo;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class PointEntree {

    private PartieService partieService = new PartieService();
    private JoueurService joueurService = new JoueurService();
    private CarteService carteService = new CarteService();


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // si la fonction n'est pas static nous sommes obligé de l'appeler à l'interieur d'un objet
        PointEntree m = new PointEntree();

        //m.menuPrincipal();
        
       JoueurService joueurService = new JoueurService();
       
       List<Object[]> joueurs = joueurService.listerJoueursEtNombreCartes(18);
        for (Object[] joueur : joueurs) {
            System.out.println( "Le joueur [" + joueur[0]+ " ] " + joueur[1] + " a " + joueur[2]+ " cartes." );
        }
        
        
    }

    // fonction qui s'occupe de lancer pls sos ecrans
    public void menuPrincipal() {

        Scanner scan = new Scanner(System.in);

        String choix;
        do {
            System.out.println("Menu principal");
            System.out.println("-------------");
            System.out.println("1. Lister les partie non démarrées");
            System.out.println("2. Créer partie");
            System.out.println("3. Rejoindre partie");
            System.out.println("4. Démarrer partie");
            System.out.println("Q. Quitter");
            System.out.println("Votre choix :");

            choix = scan.nextLine();
            switch (choix) {
                case "1":
                    List<Partie> parties= partieService.listerPartiesNonDemarrees();
                    for (Partie partie : parties) {
                        System.out.println(partie.getId() +  " - La partie " +  partie.getNom() + " contient " + partie.getJoueurs().size() + " joueurs");
                    }
                    break;

                case "2":
                    System.out.println("Entrez le nom dela partie");
                    String nomPartie = scan.nextLine();
                    partieService.creerNouvellePartie(nomPartie);
                    break;

                case "3":
                    System.out.println("Entrez votre pseudo");
                    String pseudo = scan.nextLine();
                    System.out.println("Entrez votre avatar");
                    String avatar = scan.nextLine();
                    System.out.println("Quelle partie voulez-vous rejoindre");
                    Long id = scan.nextLong();
                    joueurService.rejoindrePartie(pseudo, avatar, id);
                    break;

                case "4":
                    System.out.println("Quelle partie voulez-vous démarrer ? ");
                    String idPartieDemarree = scan.nextLine();
                    Long idPartie = Long.parseLong(idPartieDemarree);
                    
                    
                    System.out.println("Voulez-vous démarrer une partie ? yes or no");
                    String reponse =  scan.nextLine();
                    
                    if (reponse.equals("yes")) {
                        partieService.demarrerPartie(idPartie);
                        System.out.println("la partie va démarrer");
                    }else System.out.println("Vous ne voulez pas lancer la partie.");
                    break;

                case "Q":
                    System.out.println("Etes-vous sûre de vouloir quitter la partie ?");
                    String reponseQuitterPartie = scan.nextLine();
                    
                    if (reponseQuitterPartie.equals("yes")) {
                        System.out.println("A bientôt");
                    }else 
                        // pour sortir de la boucle il faut que le chois soit = Q
                        choix = "";
                    break;

                default:
                    System.out.println("Choix inconnu");
                    break;
            }
        } while (!choix.equals("Q"));
    }
    
    

    // -------------cours ------------------------
//    public static void main(String[] args) {
//
//        System.out.print("Votre choix :");
//        
//        // system.in lire les entrées
//        // objet scanner peut etre utilisé pls fois de suite, pas besoin de faire un new à chaque fois
//        Scanner s = new Scanner(System.in);
//        
//        //lire du texte ( reccup tout ce que l'user tape au clavier et le renvoie)
//        //s.nextLine();
//        
//        String choix = s.nextLine();
//        System.out.println("Vous avez choisi" + choix);
//    }
//    
}
