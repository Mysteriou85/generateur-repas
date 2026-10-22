package manager;

import app.generateur_plat.manager.IngredientMgr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;

public class IngredientMgrTest {

    final private IngredientMgr ingredientMgr = new IngredientMgr();

    @Test
    public void test() {
        JsonNode result = ingredientMgr.getDataJsonEntite();
        Assertions.assertNotNull(result);
    }

    @Test
    public void getIngredientByIdTestExiste() {
        JsonNode result = ingredientMgr.getIngredientById("haricot").path(0);
        Assertions.assertEquals("haricot", result.path("id").asString());
    }

    @Test
    public void getIngredientByIdTestExistePas() {
        JsonNode result = ingredientMgr.getIngredientById("existePas");
        Assertions.assertEquals("", result.path("id").asString());
    }
}
