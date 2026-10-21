package app.generateur_plat.manager;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.service.IngredientSrv;
import app.generateur_plat.entite.entiteJson.Ingredient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class IngredientMgr implements InterfaceJsonMgr {

    // Variable
    static final private List<String> colonneObligatoireSpecifique = List.of("nom", "typeIngredient", "sousTypeIngredient", "saveur");
    static final private String TYPE = "INGREDIENT";

    IngredientSrv ingredientSrv = new IngredientSrv();

    @Override
    public JsonNode getDataJsonEntite() {
        return jsonManager.getDataJson(TYPE, colonneObligatoireSpecifique);
    }

    public List<Ingredient> getAllIngredient() {
        return ingredientSrv.conversionJsonToEntite(getDataJsonEntite());
    }

    public Ingredient getRandomIngredient() {
        Random rand = new Random();

        List<Ingredient> allIngredient = ingredientSrv.conversionJsonToEntite(getDataJsonEntite());
        return allIngredient.get(rand.nextInt(0, allIngredient.size()));
    }
}
