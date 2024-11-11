import java.util.*;
public class DR2 extends List_DR implements DR_strat{
    public DR2() {}
    public void applyDR(int[][] grille) {
        int boucle_exterieur = 0;
        while (boucle_exterieur < 100) {
            boucle_exterieur++;
            Int_IntHashSet_Tuplet[][] newGrille = new Int_IntHashSet_Tuplet[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grille[i][j] == -1) {
                        HashSet<Integer> candidats = trouverPossibles(i, j, grille);
                        newGrille[i][j] = new Int_IntHashSet_Tuplet(-1);
                        newGrille[i][j].inthashset = candidats;
                    } else {
                        newGrille[i][j] = new Int_IntHashSet_Tuplet(grille[i][j]);
                    }
                }
            }

            //pairesCachees pour chaque ligne et colonne
            for (int i = 0; i < 9; i++) {
                Int_CoordHashSet_Tuplet[] occurences_ligne = new Int_CoordHashSet_Tuplet[9];
                Int_CoordHashSet_Tuplet[] occurences_colonne = new Int_CoordHashSet_Tuplet[9];

                for (int k = 0; k < 9; k++) {
                    occurences_ligne[k] = new Int_CoordHashSet_Tuplet(0);
                    occurences_colonne[k] = new Int_CoordHashSet_Tuplet(0);
                }

                //recherche des candidats aux paires cachees pour chaque ligne et colonne
                for (int j = 0; j < 9; j++) {
                    //ligne
                    if (newGrille[i][j].value == -1) {
                        for (Integer element : newGrille[i][j].inthashset) {
                            occurences_ligne[element - 1].value++;
                            occurences_ligne[element - 1].coordhashset.add(new Coord(i, j));
                        }
                    }
                    //colonne
                    if (newGrille[j][i].value == -1) {
                        for (Integer element : newGrille[j][i].inthashset) {
                            occurences_colonne[element - 1].value++;
                            occurences_colonne[element - 1].coordhashset.add(new Coord(j, i));
                        }
                    }
                }

                //Detection  et traitement des candidats aux paires cachees suite au traitement de chaque élement de la ligne/colonne
                for (int k = 0; k < 9; k++) {
                    if (occurences_ligne[k].value == 3) {
                        for (int l = k + 1; l < 9; l++) {
                            if (occurences_ligne[l].value == 3) {
                                for (int m = l + 1; m < 9; m++) {
                                    if (occurences_ligne[m].value == 3 && occurences_ligne[k].coordhashset.equals(occurences_ligne[l].coordhashset) && occurences_ligne[k].coordhashset.equals(occurences_ligne[m].coordhashset)) {
                                        // Restreindre les candidats des cellules au triplet trouvé
                                        for (Coord element : occurences_ligne[k].coordhashset) {
                                            newGrille[element.x][element.y].inthashset.clear();
                                            newGrille[element.x][element.y].inthashset.add(k + 1);
                                            newGrille[element.x][element.y].inthashset.add(l + 1);
                                            newGrille[element.x][element.y].inthashset.add(m + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (int k = 0; k < 9; k++) {
                    if (occurences_colonne[k].value == 3) {
                        for (int l = k + 1; l < 9; l++) {
                            if (occurences_colonne[l].value == 3) {
                                for (int m = l + 1; m < 9; m++) {
                                    if (occurences_colonne[m].value == 3 && occurences_colonne[k].coordhashset.equals(occurences_colonne[l].coordhashset) && occurences_colonne[k].coordhashset.equals(occurences_colonne[m].coordhashset)) {
                                        // Restreindre les candidats des cellules au triplet trouvé
                                        for (Coord element : occurences_colonne[k].coordhashset) {
                                            newGrille[element.x][element.y].inthashset.clear();
                                            newGrille[element.x][element.y].inthashset.add(k + 1);
                                            newGrille[element.x][element.y].inthashset.add(l + 1);
                                            newGrille[element.x][element.y].inthashset.add(m + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // Gestion des blocs
            for (int bloc = 0; bloc < 9; bloc++) {
                Int_CoordHashSet_Tuplet[] occurences_bloc = new Int_CoordHashSet_Tuplet[9];

                for (int k = 0; k < 9; k++) {
                    occurences_bloc[k] = new Int_CoordHashSet_Tuplet(0);
                }

                int blocLigne = (bloc / 3) * 3;
                int blocColonne = (bloc % 3) * 3;

                for (int i = blocLigne; i < blocLigne + 3; i++) {
                    for (int j = blocColonne; j < blocColonne + 3; j++) {
                        if (newGrille[i][j].value == -1) {
                            for (Integer element : newGrille[i][j].inthashset) {
                                occurences_bloc[element - 1].value++;
                                occurences_bloc[element - 1].coordhashset.add(new Coord(i, j));
                            }
                        }
                    }
                }

                for (int k = 0; k < 9; k++) {
                    if (occurences_bloc[k].value == 3) {
                        for (int l = k + 1; l < 9; l++) {
                            if (occurences_bloc[l].value == 3) {
                                for (int m = l + 1; m < 9; m++) {
                                    if (occurences_bloc[m].value == 3 && occurences_bloc[k].coordhashset.equals(occurences_bloc[l].coordhashset) && occurences_bloc[k].coordhashset.equals(occurences_bloc[m].coordhashset)) {
                                        // Restreindre les candidats des cellules au triplet trouvé
                                        for (Coord element : occurences_bloc[k].coordhashset) {
                                            newGrille[element.x][element.y].inthashset.clear();
                                            newGrille[element.x][element.y].inthashset.add(k + 1);
                                            newGrille[element.x][element.y].inthashset.add(l + 1);
                                            newGrille[element.x][element.y].inthashset.add(m + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int boucle_interieur = 0;
            while (boucle_interieur < 100) {
                boucle_interieur++;
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (grille[x][y] == -1) {
                            HashSet<Integer> possibles = newGrille[x][y].inthashset;
                            if (possibles.size() == 1) {
                                Iterator<Integer> it = possibles.iterator();
                                grille[x][y] = it.next();
                                //System.out.println("["+x+"]["+y+"] : "+grille[x][y]);
                                boucle_interieur++;
                            }
                        }
                    }
                }
            }
        }
    }
}
