import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sudoku {
    public int[][] grille;

    public Sudoku() {
        grille = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grille[i][j] = -1; // Valeur par défaut pour les cases vides
            }
        }
    }

    public void afficherGrille() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (grille[i][j] == -1) {
                    System.out.print(". ");
                } else {
                    System.out.print(grille[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public SudokuMemento save() {
        return new SudokuMemento(grille);
    }

    public void restore(SudokuMemento memento) {
        grille = memento.sauvegarde();
    }

    public void set(int i, int j, int v) {
        grille[i][j] = v;
    }

    public void lireGrille(String nomFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            int i = 0;

            while ((ligne = br.readLine()) != null && i < 9) {
                String[] valeurs = ligne.split(",");

                if (valeurs.length != 9) {
                    throw new IllegalArgumentException("Chaque ligne doit contenir exactement 9 valeurs.");
                }

                for (int j = 0; j < valeurs.length; j++) {
                    int valeur = Integer.parseInt(valeurs[j].trim());
                    grille[i][j] = (valeur == 0) ? -1 : valeur; // Convertit 0 en -1 pour cases vides
                }
                i++;
            }

            if (i != 9) {
                throw new IllegalArgumentException("Le fichier doit contenir exactement 9 lignes.");
            }

        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }
}