package app.generateur_plat.service;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.entite.entiteJson.Ingredient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CuissonSrv {

    public List<Cuisson> conversionJsonToEntite(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        List<Cuisson> listEntite = new ArrayList<>();

        for(JsonNode jsonEntite: node) {
            listEntite.add(mapper.treeToValue(jsonEntite, Cuisson.class));
        }

        return listEntite;
    }
}
