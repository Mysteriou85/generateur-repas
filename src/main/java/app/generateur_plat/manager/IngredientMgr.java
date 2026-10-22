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

    @Override
    public JsonNode getDataJsonEntite() {
        return jsonManager.getDataJson(TYPE, colonneObligatoireSpecifique);
    }
}
