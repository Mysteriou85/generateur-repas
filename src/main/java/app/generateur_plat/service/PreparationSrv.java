package app.generateur_plat.service;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.entite.Preparation;

public class PreparationSrv {
    public Preparation createPreparation(Ingredient ingredient, Cuisson cuisson) {
        Preparation preparation = new Preparation();
        preparation.setIngredient(ingredient);
        preparation.setCuisson(cuisson);
        return preparation;
    }
}
