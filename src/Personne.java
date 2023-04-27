public class Personne{
    private String prenom, nom;

    public Personne(String prenom, String nom){
        this.prenom = prenom;
        this.nom = nom;
    }

    public String toString(){
        return prenom + " " + nom.toUpperCase();
    }
}

