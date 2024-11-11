import java.util.*;

public static void main(String[] args) {
        Sudoku s = new Sudoku();
        s.lireGrille("C:\\Users\\Allah Eddine\\Downloads\\grille.txt");
        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();
        DR3 dr3 = new DR3();
        s.afficherGrille();

        // Ajouter les stratégies dans l'ordre
        Solver solver = new Solver(Arrays.asList(dr1, dr2, dr3), s);

        // Appliquer les règles de déduction initiales
        int solved = solver.solver_dr();
        int boucle = 1;

        Scanner option = new Scanner(System.in); // Créer le Scanner une seule fois.

        // Sauvegarde initiale avant toute modification
        SudokuMemento memento = s.save();
        int c=0;
        while (boucle == 1) {
            if (solved == 0) {
                System.out.println("La règle 1 a réussi à résoudre le sudoku");
                boucle = 0;
            } else if (solved == 1) {
                System.out.println("La règle 1 n'a pas réussi à résoudre le sudoku");
                System.out.println("La règle 2 a réussi à résoudre le sudoku");
                boucle = 0;
            } else if (solved == 2) {
                System.out.println("Les règles 1 et 2 n'ont pas réussi à résoudre le sudoku");
                System.out.println("La règle 3 a réussi à résoudre le sudoku");
                boucle = 0;
            } else {
                System.out.println("Impossible de résoudre complètement la grille avec les règles actuelles.");
                s.afficherGrille();
                System.out.println("Vous avez 3 options :");
                System.out.println("- Vous entrez 1 pour arrêter la résolution du sudoku.");
                System.out.println("- Vous entrez 2 pour tenter de le résoudre.");
                if (c!=0){
                System.out.println("- Vous entrez 3 pour annuler votre dernière modification.");}

                int number = option.nextInt();
                if (number == 1) {
                    boucle = 0; // Si l'utilisateur choisit d'arrêter, on arrête la boucle
                } else if (number == 2) {
                    System.out.println("Bravo ! Vous avez décidé de poursuivre l'aventure. Maintenant vous allez pouvoir ");
                    System.out.println("ajouter un nombre à l'une des cases du sudoku, puis, nous allons à nouveau appliquer les règles de déduction.");

                    // Sauvegarder l'état actuel avant toute modification
                    memento = s.save();

                    // Boucle pour garantir que les entrées sont valides
                    int ligne, colonne, valeur;

                    while (true) {
                        c=c+1;
                        System.out.println("Entrez la ligne (1-9) : ");
                        ligne = option.nextInt() - 1;  // La ligne est entre 1 et 9, donc on soustrait 1 pour l'indexation à partir de 0.

                        System.out.println("Entrez la colonne (1-9) : ");
                        colonne = option.nextInt() - 1; // Idem pour la colonne.

                        System.out.println("Entrez la valeur (1-9) : ");
                        valeur = option.nextInt();

                        // Validation des entrées
                        if (ligne >= 0 && ligne < 9 && colonne >= 0 && colonne < 9 && valeur >= 1 && valeur <= 9) {
                            // Si l'entrée est valide, on sort de la boucle
                            s.grille[ligne][colonne] = valeur;
                            while (solver.verif_sudoku()!=0){
                                System.out.println("Entrez la ligne (1-9) : ");
                                ligne = option.nextInt() - 1;  // La ligne est entre 1 et 9, donc on soustrait 1 pour l'indexation à partir de 0.

                                System.out.println("Entrez la colonne (1-9) : ");
                                colonne = option.nextInt() - 1; // Idem pour la colonne.

                                System.out.println("Entrez la valeur (1-9) : ");
                                valeur = option.nextInt();
                            }
                            System.out.println("La valeur " + valeur + " a été ajoutée à la position [" + (ligne + 1) + "][" + (colonne + 1) + "].");
                            break;  // Sort de la boucle si l'entrée est correcte
                        } else {
                            // Si l'entrée est invalide, on redemande l'entrée
                            System.out.println("Entrée invalide. Assurez-vous que la ligne, la colonne et la valeur soient dans les bonnes plages.");
                        }
                    }

                    // Appliquer à nouveau les règles de déduction après l'ajout du chiffre
                    solved = solver.solver_dr();  // Réapplique les règles DR1, DR2, DR3

                    // Réafficher la grille après l'ajout du chiffre et l'application des règles
                    s.afficherGrille();
                } else if (number == 3) {
                    // Restaurer l'état précédent du Sudoku
                    s.restore(memento);
                    System.out.println("Modification annulée, retour à l'état précédent :");
                    s.afficherGrille();
                }
            }
        }

        // Fermer le scanner une fois qu'on a terminé
        option.close();

        // Affiche la grille finale après la boucle
        s.afficherGrille();
    }
