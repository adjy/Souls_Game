


public class Main {
    
    public static void main (String[] args) {
//        Groupe<Etudiant> gr = new Groupe<Etudiant>();
//        gr.ajouter(new Etudiant(45712125, "Adjy", "Desir"));
//        gr.ajouter(new Etudiant(12345678, "Andrew", "Sedar"));
//        System.out.println(gr);
//        System.out.println("Le numero du premier etudiant est " +
//                gr.premier().getNumCarte());

        Fraction f1 = new Fraction(10);

        try{
            f1.setDenominateur(0);
        }
        catch (ZeroDivideException e){
            System.err.println("Probleme: " + e);
        }

        System.out.println();

        Fraction f2;
        try{
            f2 = new Fraction(20,0);
        }
        catch (ZeroDivideException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("oeeeee");
        }
    }
}


