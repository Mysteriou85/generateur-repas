package app.generateur_plat.manager;

import tools.jackson.databind.JsonNode;

public interface InterfaceJsonMgr {

    JsonManager jsonManager = new JsonManager();

    public JsonNode getDataJsonEntite();

}
