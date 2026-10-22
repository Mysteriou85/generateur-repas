package app.generateur_plat.manager;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.JsonNodeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class JsonManager {

    private static final List<String> colonneObligatoireParDefaut = List.of("id", "type");

    /**
     * Récupère les données JSON et les passes dans un traitement pour s'assurer que les données soient correct
     * @param type type d'entité recherché
     * @param colonneObligatoire List de colonnes obligatoires pour l'entité recherchée
     * @param valeurRecherche (optionnel) List de recherche que l'ont souhaient effectué
     * @return JsonNode
     */
    public JsonNode getDataJson(String type, List<String> colonneObligatoire, Map<String, String> valeurRecherche) {
        ObjectMapper mapper = new ObjectMapper();
        Path dossier = Path.of("src/main/resources/json");
        ArrayNode perimetreFinal = JsonNodeFactory.instance.arrayNode();
        List<JsonNode> listJsonFile = new ArrayList<>();

        // L'on rentre dans le dossier et récupère tous les fichiers JSON
        try(Stream<Path> paths = Files.walk(dossier)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> listJsonFile.add(mapper.readTree(path.toFile())));

        } catch(IOException oiErr) {
            System.err.println(oiErr.getMessage());
        }

        // L'ont scan chaque dossier JSON pour en récupéré lesdonnée et les ajoutés au périmètre s'ils correspondent à l'attendu.
        for (JsonNode root : listJsonFile) {
            JsonNode perimetre;
            /*
             * TODO l'on peut ce trouver dans une condition où le type n'est pas spécifier, par exemple avec une barre de rechercher
             *  qui recherche avec un nom plutôt qu'un type. Ce n'est pas implémenté et ne le sera probablement pas
             *  mais il serait interessant de regarder cette difficulté
             */
            if(valeurRecherche == null || valeurRecherche.isEmpty()) {
                perimetre = perimetreJsonTypeSpecifique(root, type, colonneObligatoire);
            } else {
                perimetre = perimetreJsonAvecRecherche(root, type, colonneObligatoire, valeurRecherche);
            }
            // L'on évite les nulls pointer en ajoutant des valeurs null dans le perimetreFinal
            if(perimetre != null) {
                for (JsonNode entiteIndividuel : perimetre){
                    perimetreFinal.add(entiteIndividuel);
                }
            }
        }

        return perimetreFinal;
    }

    /**
     * Controle si les données récupérées du json correspond au type souhaité avec les
     * @param root JsonNode
     * @param type String
     * @param colonneObligatoireSpecifique List<String>
     * @return JsonNode
     */
    private JsonNode perimetreJsonTypeSpecifique(JsonNode root,
                                                 String type,
                                                 List<String> colonneObligatoireSpecifique) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();

        for(JsonNode node: root) {
            if(colonneObligatoireParDefaut.stream().allMatch(node::hasNonNull)
                    && colonneObligatoireSpecifique.stream().allMatch(node::hasNonNull)
                    && type.equals(node.path("type").asString())) {
                arrayNode.add(node);
            }
        }
        return arrayNode;
    }

    private JsonNode perimetreJsonAvecRecherche(JsonNode root,
                                                String type,
                                                List<String> colonneObligatoireSpecifique,
                                                Map<String, String> valeurRecherche) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();

        for(JsonNode node: root) {
            if(verifDonneesObligatoire(node, colonneObligatoireSpecifique)
                    && type.equals(node.path("type").asString())
                    && rechercheData(node, valeurRecherche)) {
                arrayNode.add(node);
            }
        }
        return arrayNode;
    }

    private boolean verifDonneesObligatoire(JsonNode node, List<String> colonneObligatoireSpecifique) {
        return colonneObligatoireParDefaut.stream().allMatch(node::hasNonNull)
                && colonneObligatoireSpecifique.stream().allMatch(node::hasNonNull);
    }

    private boolean rechercheData(JsonNode node, Map<String, String> valeurRecherche) {
        boolean result = true;

        for(Map.Entry<String, String> recherche : valeurRecherche.entrySet()) {
            // Si la valeur ne correspond pas à un quelconque point alors la donnée sera refusé
            if (!recherche.getValue().equals(node.path(recherche.getKey()).asString())) {
                result = false;
            }
        }

        return result;
    }
}
