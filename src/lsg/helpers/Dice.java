package lsg.helpers;
import java.util.Random;
public class Dice {
    private int faces;
    Random random = new Random();

    // getter et setter
    public int getFaces() { return faces; }
    public void setFaces(int faces) { this.faces = faces; }

    public Dice(int faces){setFaces(faces);} // renseigne la face

    public int roll(){
        return random.nextInt(getFaces());
    } // retourne un nombre aleatoire
    public void printStats(){
        System.out.println(this.toString());
    } // Affichage dans la console
}




