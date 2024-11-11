public class SudokuMemento {
    public int[][] grille;

    public SudokuMemento(int[][] grille) {
        this.grille = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grille[i], 0, this.grille[i], 0, 9);
        }
    }
    public int[][] sauvegarde(){
        return grille;
    }
}