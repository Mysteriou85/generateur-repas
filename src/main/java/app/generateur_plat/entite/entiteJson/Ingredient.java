package app.generateur_plat.entite.entiteJson;

public class Ingredient extends EntiteJson {

    public String nom;
    public String typeIngredient;
    public String sousTypeIngredient;
    public String saveur;

    /*
     *  Getter & Setter
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeIngredient() {
        return typeIngredient;
    }

    public void setTypeIngredient(String typeIngredient) {
        this.typeIngredient = typeIngredient;
    }

    public String getSousTypeIngredient() {
        return sousTypeIngredient;
    }

    public void setSousTypeIngredient(String sousTypeIngredient) {
        this.sousTypeIngredient = sousTypeIngredient;
    }

    public String getSaveur() {
        return saveur;
    }

    public void setSaveur(String saveur) {
        this.saveur = saveur;
    }
}
