package app.generateur_plat.manager;

import app.generateur_plat.service.CuissonSrv;
import app.generateur_plat.entite.entiteJson.Cuisson;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Random;

public class CuissonMgr implements InterfaceJsonMgr {

    // Variable
    static final private List<String> colonneObligatoireSpecifique = List.of("cuisson", "accompagnement");
    static final private String TYPE = "CUISSON";

    @Override
    public JsonNode getDataJsonEntite() {
        return jsonManager.getDataJson(TYPE, colonneObligatoireSpecifique);
    }
}
