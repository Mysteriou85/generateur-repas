package manager;

import app.generateur_plat.manager.IngredientMgr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de test pour la classe abstract "JsonManager". On utilise les classes qui l'extends pour vérifier
 *  le fonctionnement de celui-ci.
 */
public class JsonManagerTest {
    IngredientMgr ingredientMgr = new IngredientMgr();

    public JsonNode getDonneesInexistante(String id) {
        Map<String, String> valeurRecherche = new HashMap<>();
        valeurRecherche.put("existepas", id);

        return ingredientMgr.getDataJsonEntiteAvecRecherche(valeurRecherche);
    }

    /**
     * Dans le cadre que l'on recherche un nom de colonne inexistant aucun nullpointer ne dois apparaitre et la donnée renvoyée doit être vide.
     */
    @Test
    public void testRechercheDonneesInexistante() {
        JsonNode result = getDonneesInexistante("truc");

        Assertions.assertEquals("", result.path("existepas").toString());
    }
}
