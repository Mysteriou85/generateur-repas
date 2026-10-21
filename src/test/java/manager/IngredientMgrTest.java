package manager;

import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.manager.IngredientMgr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class IngredientMgrTest {

    private IngredientMgr ingredientMgr = new IngredientMgr();

    @Test
    public void testFolder() throws IOException {
        List<Ingredient> listIngredient = ingredientMgr.getAllIngredient();

        Assertions.assertNotNull(listIngredient);
        Assertions.assertTrue(listIngredient.stream().allMatch(f -> "INGREDIENT".equals(f.getType())));
    }

//    @Test
//    public void test() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String string = mapper.valueToTree(ingredientMgr.getAllIngredient()).asString();
//
//        Assertions.assertNotNull(string);
//    }
}
