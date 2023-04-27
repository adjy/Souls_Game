public class Etudiant extends Personne {
    private int numCarte;

    public Etudiant(int num, String prenom, String nom){
        super(prenom, nom);
        numCarte = num;
    }

    public int getNumCarte(){
        return numCarte;
    }

    public String toString(){
        return "Etudiant n° "+ getNumCarte()
                + "-> " + super.toString();
    }
}
