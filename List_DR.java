import java.util.HashSet;

public abstract class List_DR {
    public List_DR(){}
    public HashSet<Integer> trouverPossibles(int ligne, int colonne, int[][] grille) {
        HashSet<Integer> possibles = new HashSet<>();
        for (int n = 1; n <= 9; n++) {
            possibles.add(n);
        }
        for (int j = 0; j < 9; j++) {
            possibles.remove(grille[ligne][j]);
        }
        for (int i = 0; i < 9; i++) {
            possibles.remove(grille[i][colonne]);
        }
        int blocLigne = (ligne / 3) * 3;
        int blocColonne = (colonne / 3) * 3;
        for (int i = blocLigne; i < blocLigne + 3; i++) {
            for (int j = blocColonne; j < blocColonne + 3; j++) {
                possibles.remove(grille[i][j]);
            }
        }
        return possibles;
    }
}
