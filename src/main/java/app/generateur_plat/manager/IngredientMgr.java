package app.generateur_plat.manager;

import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientMgr extends JsonManager implements InterfaceJsonMgr {

    // Variable
    static final private List<String> colonneObligatoireSpecifique = List.of("nom", "typeIngredient", "sousTypeIngredient", "saveur");
    static final private String TYPE = "INGREDIENT";

    @Override
    public JsonNode getDataJsonEntite() {
        return getDataJson(TYPE, colonneObligatoireSpecifique, null);
    }

    @Override
    public JsonNode getDataJsonEntiteAvecRecherche(Map<String, String> valeurRecherche) {
        return getDataJson(TYPE, colonneObligatoireSpecifique, valeurRecherche);
    }

    public JsonNode getIngredientById(String id) {
        Map<String, String> valeurRecherche = new HashMap<>();
        valeurRecherche.put("id", id);

        return getDataJsonEntiteAvecRecherche(valeurRecherche);
    }
}
