package app.generateur_plat.entite;

public class Repas {

    public Plat entree;
    public Plat plat;
    public Plat dessert;

    /*
     *  Getter & Setter
     */
    public Plat getEntree() {
        return entree;
    }

    public void setEntree(Plat entree) {
        this.entree = entree;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Plat getDessert() {
        return dessert;
    }

    public void setDessert(Plat dessert) {
        this.dessert = dessert;
    }
}
