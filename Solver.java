import java.util.*;

public class Solver {
    private List<DR_strat> strategie;
    public Sudoku sudoku;
    public Solver(List<DR_strat> strategie,Sudoku sudoku) {
        this.strategie = strategie;
        this.sudoku=sudoku;
    }
    public int verif_ligne(int[][] grille) {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (grille[i][j] != -1) {
                    set.add(grille[i][j]);
                }
            }
            if (set.size() != 9) {
                return 1;
            }
        }
        return 0;
    }
    public int verif_colonne(int[][]grille) {
        for (int j = 0; j < 9; j++) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (grille[i][j] != -1) {
                    set.add(grille[i][j]);
                }
            }
            if (set.size() != 9) {
                return 1;
            }
        }
        return 0;
    }

    public int verif_block(int[][] grille) {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (verif_a_block(i, j,grille) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private int verif_a_block(int a, int b,int[][] grille) {
        HashSet<Integer> set = new HashSet<>();
        int new_a = 3 * (a / 3);
        int new_b = 3 * (b / 3);
        for (int i = new_a; i < new_a + 3; i++) {
            for (int j = new_b; j < new_b + 3; j++) {
                if (grille[i][j] != -1) {
                    set.add(grille[i][j]);
                }
            }
        }
        return set.size() == 9 ? 0 : 1;
    }
    public int verif_sudoku(){
        if (verif_block(sudoku.grille)==0 && verif_colonne(sudoku.grille)==0 && verif_ligne(sudoku.grille)==0){
            return 0;
        }
        return 1;
    }
    public int solver_dr(){
        int iterator=0;
        while (iterator<=2){
            strategie.get(iterator).applyDR(sudoku.grille);
            if (verif_sudoku()==0){
                return iterator;
            }
            iterator = iterator+1;
        }
        return 3;
    }
}