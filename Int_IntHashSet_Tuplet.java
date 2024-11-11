import java.util.*;
public class Int_IntHashSet_Tuplet{
    public int value;
    public HashSet<Integer> inthashset;
    public Int_IntHashSet_Tuplet(int value){
        this.value = value;
        this.inthashset = new HashSet<Integer>();
        if(value == -1){
            for(int i = 1; i < 10; i++){
                this.inthashset.add(i);
            }
        }
    }
}