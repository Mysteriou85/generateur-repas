package app.generateur_plat.controller;

import app.generateur_plat.service.CuissonSrv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuisson")
public class CuissonCtrl {
    CuissonSrv cuissonSrv = new CuissonSrv();

    @GetMapping("/all")
    public Object getAllCuissonJson() {
        return cuissonSrv.findAllCuissonJson();
    }

    @GetMapping("/random")
    public Object getRandomCuissonJson() {
        return cuissonSrv.getRandomCuisson();
    }

    @GetMapping("/id/{id}")
    public Object getCuissonById(@PathVariable String id) {
        return cuissonSrv.findCuissonById(id);
    }
}
