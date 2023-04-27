public class Fraction {
    private int numerateur, denominateur = 1;
    public Fraction(int numerateur){
        this.numerateur = numerateur;
    }

    public Fraction(int numerateur, int denominateur) throws ZeroDivideException{
        this.numerateur = numerateur;
        setDenominateur(denominateur);
    }

    public void setNumerateur(int numerateur){
        this.numerateur = numerateur;
    }
    public void setDenominateur(int denominateur) throws ZeroDivideException{
        if (denominateur == 0) throw new ZeroDivideException();
        this.denominateur = denominateur;
    }
}
