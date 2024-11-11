import java.util.*;

public class DR1 extends List_DR implements DR_strat {
    public DR1() {}
    public void applyDR(int[][] grille) {
        int boucle = 0;
        while (boucle == 0) {
            boucle = 1;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grille[i][j] == -1) {
                        HashSet<Integer> possibles = trouverPossibles(i, j,grille);
                        if (possibles.size() == 1) {
                            Iterator<Integer> it = possibles.iterator();
                            grille[i][j] = it.next();
                            boucle = 0;
                        }
                    }
                }
            }
        }
    }
}