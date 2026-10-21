package app.generateur_plat.service;

import app.generateur_plat.entite.Plat;
import app.generateur_plat.entite.Preparation;
import app.generateur_plat.manager.CuissonMgr;
import app.generateur_plat.manager.IngredientMgr;
import app.generateur_plat.ressource.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlatSrv {
    IngredientMgr ingredientMgr = new IngredientMgr();
    CuissonMgr cuissonMgr = new CuissonMgr();
    PreparationSrv preparationSrv = new PreparationSrv();



    /**
     * Créé un string qui décrit simplement le plat
     * @param plat Plat
     * @return String
     */
    public String descriptionPlat(Plat plat) {
        StringBuffer description = new StringBuffer();

        description.append(plat.basePreparation.getIngredient().getNom())
                .append(Constant.ESPACE)
                .append(plat.basePreparation.getCuisson().getCuisson().toLowerCase())
                .append(Constant.POINT);

        // Boucle sur les compositions pour avoir la description total
        if(plat.getComposition() != null) {
            description.append(Constant.ESPACE).append("Accompagné de ");

            for (int i = 0; i < plat.getComposition().size(); i++) {
                Preparation composition = plat.getComposition().get(i);
                description.append(composition.getIngredient().getNom().toLowerCase())
                        .append(Constant.ESPACE)
                        .append(composition.getCuisson().getCuisson().toLowerCase());

                if (i == plat.getComposition().size() - 2) {
                    description.append(Constant.VIRGULE).append(Constant.ESPACE).
                            append("et").append(Constant.ESPACE);
                } else if(i < plat.getComposition().size() - 1) {
                    description.append(Constant.VIRGULE).append(Constant.ESPACE);
                }
            }
            description.append(Constant.POINT);
        }

        return description.toString();
    }

    /**
     * Génération d'un plat
     * @param nbreComposition Integer (opt)
     * @return Plat
     * @throws IOException IOException
     */
    public Plat generationPlat(Integer nbreComposition) throws IOException {
        Plat plat = new Plat();

        plat.setBasePreparation(generationBasePreparation());
        if(nbreComposition != null) {
            plat.setComposition(generationCompositions(nbreComposition));
        }

        return plat;
    }

    /**
     * Génération de la préparation de base du repas
     * @return Preparation
     * @throws IOException IOException
     */
    private Preparation generationBasePreparation() throws IOException {
        Preparation base = preparationSrv.createPreparation(ingredientMgr.getRandomIngredient(), cuissonMgr.getRandomCuisson());
        return base;
    }

    /**
     * Génération des préparations de compositions du repas
     * @param nbreComposition Integer
     * @return List<Preparation>
     * @throws IOException IOException
     */
    private List<Preparation> generationCompositions(Integer nbreComposition) throws IOException {
        List<Preparation> listComposition = new ArrayList<>();

        if(nbreComposition != null && nbreComposition != 0) {
            for (int i = 0; i < nbreComposition; i++) {
                Preparation composition = preparationSrv.createPreparation(ingredientMgr.getRandomIngredient(), cuissonMgr.getRandomCuisson());
                listComposition.add(composition);
            }
        }

        return listComposition;
    }
}
