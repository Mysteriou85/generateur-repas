package app.generateur_plat.controller;

import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.service.IngredientSrv;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientCtrl {
    IngredientSrv ingredientSrv = new IngredientSrv();

    @GetMapping("/all")
    public Object getAllIngredientJson() {
        return ingredientSrv.findAllIngredientJson();
    }

    @GetMapping("/random")
    public Object getRandomIngredientJson() {
        return ingredientSrv.getRandomIngredient();
    }

    @GetMapping("/id/{id}")
    public Object getIngredientById(@PathVariable String id) {
        return ingredientSrv.findIngredientById(id);
    }

    /*
     * Cette méthode ne fait rien, elle a été juste créé pour expérimenter avec les posts
     */
    @PostMapping()
    public ResponseEntity<Void> addIngredient(@RequestBody Ingredient ingredient) {
        ResponseEntity<Void> response;

        if(ingredientSrv.controleIngredient(ingredient)) {
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

}
