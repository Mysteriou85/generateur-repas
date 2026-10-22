package service;

import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.service.IngredientSrv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IngredientSrvTest {

    IngredientSrv ingredientSrv = new IngredientSrv();

    @Test
    public void testFolder() {
        List<Ingredient> listIngredient = ingredientSrv.getAllIngredient();

        Assertions.assertNotNull(listIngredient);
        Assertions.assertTrue(listIngredient.stream().allMatch(f -> "INGREDIENT".equals(f.getType())));
    }

}
