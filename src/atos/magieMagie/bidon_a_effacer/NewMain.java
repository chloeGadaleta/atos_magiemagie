/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.bidon_a_effacer;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class NewMain {

    public static void aleatoire() {
        
        
        Random r = new Random();
        int nombreAleatoire = r.nextInt(101);
        Scanner scan = new Scanner(System.in);
        int nombreEcrit;

        
        do {            
            System.out.println("Veuillez entrer un nombre");
            nombreEcrit = scan.nextInt();            
            
            if (nombreAleatoire < nombreEcrit) {
                System.out.println("le nbr aleatoire est plus petit");
            } else if (nombreAleatoire > nombreEcrit) {
                System.out.println(" le nbr aleatoire est plus grand");
            } else {
                System.out.println("sont egaux");
            }
        } while (!(nombreAleatoire == nombreEcrit));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        aleatoire();
    }

}
