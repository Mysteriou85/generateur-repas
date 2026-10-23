package app.generateur_plat.service;

import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.manager.IngredientMgr;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IngredientSrv {
    IngredientMgr ingredientMgr = new IngredientMgr();

    /**
     * Convertie un JsonNode en entite Ingredient
     * @param node JsonNode
     * @return List<Ingredient>
     */
    public List<Ingredient> conversionJsonToEntite(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        List<Ingredient> listEntite = new ArrayList<>();

        for(JsonNode jsonEntite: node) {
            listEntite.add(mapper.treeToValue(jsonEntite, Ingredient.class));
        }

        return listEntite;
    }

    /**
     * Récupère tout les ingrédients
     * @return List<Ingredient>
     */
    public List<Ingredient> getAllIngredient() {
        return conversionJsonToEntite(ingredientMgr.getDataJsonEntite());
    }

    /**
     * Récupère un ingrédient aléatoire
     * @return Ingredient
     */
    public Ingredient getRandomIngredient() {
        Random rand = new Random();

        List<Ingredient> allIngredient = conversionJsonToEntite(ingredientMgr.getDataJsonEntite());
        return allIngredient.get(rand.nextInt(0, allIngredient.size()));
    }

    /**
     * Récupère un ingrédient aléatoire parmis une liste donnée
     * @param listIngredient List<Ingredient>
     * @return Ingredient
     */
    public Ingredient choseRandomIngredient(List<Ingredient> listIngredient) {
        Random rand = new Random();

        return listIngredient.get(rand.nextInt(0, listIngredient.size()));
    }

    /**
     * TODO faire un véritable contrôle, c'est qu'une excuse pour le moment
     * @param ingredient Ingredient
     * @return boolean
     */
    public boolean controleIngredient(Ingredient ingredient) {
        return !ingredient.getId().isEmpty() && !ingredient.getType().isEmpty() && "INGREDIENT".equals(ingredient.getType());
    }

    public JsonNode findAllIngredientJson() {
        return ingredientMgr.getDataJsonEntite();
    }

    public JsonNode findIngredientById(String id) {
        return ingredientMgr.getIngredientById(id);
    }
}
