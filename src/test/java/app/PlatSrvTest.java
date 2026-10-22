package app;

import app.generateur_plat.entite.Plat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import app.generateur_plat.service.PlatSrv;

public class PlatSrvTest {

    PlatSrv platSrv = new PlatSrv();

    @Test
    public void creationPlatTest() {
        Plat plat = platSrv.generationPlat(null);
        String description = platSrv.descriptionPlat(plat);
        Assertions.assertNotNull(plat);
        Assertions.assertNotNull(description);
    }

    @Test
    public void creationPlatTestAvecZeroComposition() {
        Plat plat = platSrv.generationPlat(0);
        String description = platSrv.descriptionPlat(plat);
        Assertions.assertNotNull(plat);
        Assertions.assertNotNull(description);
    }

    @Test
    public void creationPlatTestAvecComposition() {
        Plat plat = platSrv.generationPlat(3);
        String description = platSrv.descriptionPlat(plat);
        Assertions.assertNotNull(plat);
        Assertions.assertNotNull(description);
    }

}
