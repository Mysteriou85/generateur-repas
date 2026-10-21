package app.generateur_plat.entite;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.entite.entiteJson.Ingredient;

public class Preparation {

    Ingredient ingredient;
    Cuisson cuisson;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Cuisson getCuisson() {
        return cuisson;
    }

    public void setCuisson(Cuisson cuisson) {
        this.cuisson = cuisson;
    }
}
