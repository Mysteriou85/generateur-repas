package app.generateur_plat.controller;

import app.generateur_plat.manager.IngredientMgr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
public class IngredientCtrl {
    IngredientMgr ingredientMgr = new IngredientMgr();

    @GetMapping("/all")
    public Object getAllIngredientJson() {
        return ingredientMgr.getDataJsonEntite();
    }

    @GetMapping("/random")
    public Object getRandomIngredientJson() {
        return ingredientMgr.getRandomIngredient();
    }

}
