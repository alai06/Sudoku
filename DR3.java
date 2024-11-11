import java.util.*;


public class DR3 extends List_DR implements DR_strat {
    public DR3() {}
    public ArrayList<HashSet<Integer>> liste_possible(int[][] grille) {
        ArrayList<HashSet<Integer>> liste = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                liste.add(trouverPossibles(i, j,grille));
            }
        }
        return liste;
    }

    public ArrayList<Integer> catch_xwing(ArrayList<HashSet<Integer>> candidats) {
        ArrayList<Integer> modifications = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            ArrayList<Integer> lignesCandidats = new ArrayList<>();
            ArrayList<Integer> colonnesCandidats = new ArrayList<>();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (candidats.get(i * 9 + j).contains(num)) {
                        lignesCandidats.add(i);
                        colonnesCandidats.add(j);
                    }
                }
            }

            for (int i = 0; i < lignesCandidats.size(); i++) {
                for (int j = i + 1; j < lignesCandidats.size(); j++) {
                    if (colonnesCandidats.get(i).equals(colonnesCandidats.get(j))) {
                        int ligne1 = lignesCandidats.get(i);
                        int ligne2 = lignesCandidats.get(j);
                        int col1 = colonnesCandidats.get(i);
                        int col2 = colonnesCandidats.get(j);

                        for (int k = 0; k < 9; k++) {
                            if (k != ligne1 && k != ligne2) {
                                if (candidats.get(k * 9 + col1).remove(num)) {
                                    modifications.add(k * 9 + col1);
                                }
                                if (candidats.get(k * 9 + col2).remove(num)) {
                                    modifications.add(k * 9 + col2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return modifications;
    }

    /*private boolean sudokuEstResolu() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grille[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }*/

    public void applyDR(int[][] grille) {
        boolean modifications;
        int iterationCount = 0;
        int maxIterations = 100;  // Limite du nombre d'itérations

        do {
            modifications = false;
            iterationCount++;

            // Si le nombre d'itérations dépasse la limite, on arrête la boucle
            if (iterationCount >= maxIterations) {
                break;
            }

            // Liste des candidats pour chaque case
            ArrayList<HashSet<Integer>> listeCandidats = liste_possible(grille);

            // Appliquer la stratégie X-Wing
            ArrayList<Integer> modifsXWing = catch_xwing(listeCandidats);
            if (!modifsXWing.isEmpty()) {
                modifications = true;
                mettreAJourListe(modifsXWing, listeCandidats, grille);
            }

            // Appliquer les placements uniques
            boolean modifsUniques = appliquerPlacementsUniques(listeCandidats, grille);
            if (modifsUniques) {
                modifications = true;
            }

        } while (modifications);  // Continue tant qu'il y a des modifications

        // Vous pouvez aussi ajouter ici un retour sur le statut du sudoku si besoin
        // return sudokuEstResolu();
    }


    private void mettreAJourListe(ArrayList<Integer> modifs, ArrayList<HashSet<Integer>> liste,int[][] grille) {
        for (int index : modifs) {
            int row = index / 9;
            int col = index % 9;
            liste.set(index, trouverPossibles(row, col,grille));
        }
    }

    private boolean appliquerPlacementsUniques(ArrayList<HashSet<Integer>> liste,int[][] grille) {
        boolean modifications = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = i * 9 + j;
                if (grille[i][j] == -1 && liste.get(index).size() == 1) {
                    int unique = liste.get(index).iterator().next();
                    grille[i][j] = unique;
                    modifications = true;
                }
            }
        }
        return modifications;
    }
}
