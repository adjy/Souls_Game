import java.util.ArrayList;

public class Groupe <Person extends Personne>{
    ArrayList<Person> group;

    public Groupe(){
        super();
        group = new ArrayList<Person>();
    }
    public void ajouter(Person personne){
        group.add(personne);
    }
    public Person premier(){
        return group.get(0);
    }
    public String toString(){
        String str = "----- Listing ----- \n";
        for(Person elem: group)
            str += elem + "\n";
        return str;
    }
}
