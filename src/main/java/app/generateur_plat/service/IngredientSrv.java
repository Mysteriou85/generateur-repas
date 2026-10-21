package app.generateur_plat.service;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.manager.JsonManager;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class IngredientSrv {

    public List<Ingredient> conversionJsonToEntite(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        List<Ingredient> listEntite = new ArrayList<>();

        for(JsonNode jsonEntite: node) {
            listEntite.add(mapper.treeToValue(jsonEntite, Ingredient.class));
        }

        return listEntite;
    }

}
