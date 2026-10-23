package app.generateur_plat.service;

import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.manager.CuissonMgr;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CuissonSrv {
    CuissonMgr cuissonMgr = new CuissonMgr();

    /**
     * Convertie un JsonNode en entite Cuisson
     * @param node JsonNode
     * @return List<Cuisson>
     */
    public List<Cuisson> conversionJsonToEntite(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        List<Cuisson> listEntite = new ArrayList<>();

        for(JsonNode jsonEntite: node) {
            listEntite.add(mapper.treeToValue(jsonEntite, Cuisson.class));
        }

        return listEntite;
    }

    /**
     * Récupère toute les cuissons
     * @return List<Cuisson>
     */
    public List<Cuisson> getAllCuisson() {
        return conversionJsonToEntite(cuissonMgr.getDataJsonEntite());
    }

    /**
     * Récupère une cuisson aléatoire partie tout celle existant en donnée
     * @return Cuisson
     */
    public Cuisson getRandomCuisson() {
        Random rand = new Random();

        List<Cuisson> allCuisson = conversionJsonToEntite(cuissonMgr.getDataJsonEntite());
        return allCuisson.get(rand.nextInt(0, allCuisson.size()));
    }

    /**
     * Récupère un ingrédient aléatoire parmis une liste donnée
     * @param listCuisson List<Cuisson>
     * @return Cuisson
     */
    public Cuisson choseRandomCuisson(List<Cuisson> listCuisson) {
        Random rand = new Random();
        return listCuisson.get(rand.nextInt(0, listCuisson.size()));
    }

    public JsonNode findAllCuissonJson() {
        return cuissonMgr.getDataJsonEntite();
    }

    public JsonNode findCuissonById(String id) {
        return cuissonMgr.getCuissonById(id);
    }
}
