package app.generateur_plat.controller;

import org.springframework.web.bind.annotation.*;
import app.generateur_plat.service.PlatSrv;

import java.io.IOException;

@RestController
@RequestMapping("/plat")
public class PlatCtrl {
    PlatSrv platSrv = new PlatSrv();

    @GetMapping()
    public String getPlat() throws IOException {
        return platSrv.descriptionPlat(platSrv.generationPlat(null));
    }

    @GetMapping("/{id}")
    public String getPlatCompose(@PathVariable Integer id) throws IOException {
        return platSrv.descriptionPlat(platSrv.generationPlat(id));
    }


}
