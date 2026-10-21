package app.generateur_plat.manager;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.JsonNodeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class JsonManager {

    private static final List<String> colonneObligatoireParDefaut = List.of("id", "type");

    /**
     * Récupère les données JSON et les passes dans un traitement pour s'assurer que les données soit correct
     * @param type String
     * @param colonneObligatoire List<String>
     * @return JsonNode
     */
    public JsonNode getDataJson(String type, List<String> colonneObligatoire) {
        ObjectMapper mapper = new ObjectMapper();
        Path dossier = Path.of("src/main/resources/json");
        ArrayNode perimetreFinal = JsonNodeFactory.instance.arrayNode();

        try (Stream<Path> fichiers = Files.list(dossier)) {
            fichiers
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".json"))
                    .forEach(fichier -> {
                        try {
                            JsonNode root = mapper.readTree(fichier.toFile());
                            JsonNode perimetre = perimetreJsonTypeSpecifique(root, type, colonneObligatoire);
                            // L'on évite les nulls pointer en ajoutant des valeurs null dans le perimetreFinal
                            if(perimetre != null) {
                                for (JsonNode entiteIndividuel : perimetre){
                                    perimetreFinal.add(entiteIndividuel);
                                }
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    });
        } catch(IOException oiErr) {
            System.err.println(oiErr.getMessage());
        }

        return perimetreFinal;
    }

    /**
     * Controle si les données récupéré du json correspond au type souhaité avec les
     * @param root JsonNode
     * @param type String
     * @param colonneObligatoireSpecifique List<String>
     * @return JsonNode
     */
    private JsonNode perimetreJsonTypeSpecifique(JsonNode root, String type, List<String> colonneObligatoireSpecifique) {
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
}
