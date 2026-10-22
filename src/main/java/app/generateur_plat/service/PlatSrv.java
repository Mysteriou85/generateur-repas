package app.generateur_plat.service;

import app.generateur_plat.entite.Plat;
import app.generateur_plat.entite.Preparation;
import app.generateur_plat.entite.entiteJson.Cuisson;
import app.generateur_plat.entite.entiteJson.Ingredient;
import app.generateur_plat.ressource.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlatSrv {
    IngredientSrv ingredientSrv = new IngredientSrv();
    CuissonSrv cuissonSrv= new CuissonSrv();
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

                // Préparation pour la dernière données
                if (i == plat.getComposition().size() - 2) {
                    description.append(Constant.VIRGULE).append(Constant.ESPACE).
                            append("et").append(Constant.ESPACE);

                //Gestion de la dernières donnée
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
    public Plat generationPlat(Integer nbreComposition) {
        Plat plat = new Plat();
        // L'on récupère les données en amont pour ne pas avoir a rappeler le json à chaque fois
        List<Ingredient> allIngredient = ingredientSrv.getAllIngredient();
        List<Cuisson> allCuisson = cuissonSrv.getAllCuisson();

        plat.setBasePreparation(generationBasePreparation(allIngredient, allCuisson));
        if(nbreComposition != null) {
            plat.setComposition(generationCompositions(nbreComposition, allIngredient, allCuisson));
        }

        return plat;
    }

    /**
     * Génération de la préparation de base du repas
     *  Warning: Cette méthode appelle le json deux fois par utilisation préféré l'autre méthode
     * @return Preparation
     * @throws IOException IOException
     */
    @Deprecated
    private Preparation generationBasePreparation() {
        Preparation base = preparationSrv.createPreparation(ingredientSrv.getRandomIngredient(), cuissonSrv.getRandomCuisson());
        return base;
    }

    /**
     * Génération de la préparation de base du repas
     *  Version optimisé de generationBasePreparation(), permet de tirer d'une liste donnée au lieu de rappeler constamment le json
     * @return Preparation
     * @throws IOException IOException
     */
    private Preparation generationBasePreparation(List<Ingredient> listIngredient, List<Cuisson> listCuisson) {
        Preparation base = preparationSrv.createPreparation(ingredientSrv.choseRandomIngredient(listIngredient), cuissonSrv.choseRandomCuisson(listCuisson));
        return base;
    }

    /**
     * Génération des préparations de compositions du repas
     *  Warning: Cette méthode appelle le json deux fois par composition préféré l'autre méthode
     * @param nbreComposition Integer
     * @return List<Preparation>
     * @throws IOException IOException
     */
    @Deprecated
    private List<Preparation> generationCompositions(Integer nbreComposition) {
        List<Preparation> listComposition = new ArrayList<>();

        if(nbreComposition != null && nbreComposition != 0) {
            for (int i = 0; i < nbreComposition; i++) {
                Preparation composition = preparationSrv.createPreparation(ingredientSrv.getRandomIngredient(), cuissonSrv.getRandomCuisson());
                listComposition.add(composition);
            }
        }

        return listComposition;
    }

    /**
     * Génération des préparations de compositions du repas
     *  Version optimisé de generationBasePreparation(), permet de tirer d'une liste donnée au lieu de rappeler constamment le json
     * @param nbreComposition Integer
     * @return List<Preparation>
     * @throws IOException IOException
     */
    private List<Preparation> generationCompositions(Integer nbreComposition,
                                                     List<Ingredient> listIngredient,
                                                     List<Cuisson> listCuisson) {
        List<Preparation> listComposition = new ArrayList<>();

        if(nbreComposition != null && nbreComposition != 0) {
            for (int i = 0; i < nbreComposition; i++) {
                Preparation composition = preparationSrv.createPreparation(
                        ingredientSrv.choseRandomIngredient(listIngredient),
                        cuissonSrv.choseRandomCuisson(listCuisson));
                listComposition.add(composition);
            }
        }

        return listComposition;
    }
}
