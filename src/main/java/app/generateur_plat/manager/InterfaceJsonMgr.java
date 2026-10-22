package app.generateur_plat.manager;

import tools.jackson.databind.JsonNode;

import java.util.Map;

public interface InterfaceJsonMgr {

    JsonNode getDataJsonEntite();

    JsonNode getDataJsonEntiteAvecRecherche(Map<String, String> valeurRecherche);

}
