package app.generateur_plat.entite;

import java.util.List;

public class Plat {

    public Preparation basePreparation;
    public List<Preparation> composition;

    public Preparation getBasePreparation() {
        return basePreparation;
    }

    public void setBasePreparation(Preparation basePreparation) {
        this.basePreparation = basePreparation;
    }

    public List<Preparation> getComposition() {
        return composition;
    }

    public void setComposition(List<Preparation> composition) {
        this.composition = composition;
    }
}
