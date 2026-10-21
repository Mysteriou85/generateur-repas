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
        List<JsonNode> listJsonFile = new ArrayList<>();

        // L'on rentre dans le dossier et récupère tout les fichiers JSON
        try(Stream<Path> paths = Files.walk(dossier)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        listJsonFile.add(mapper.readTree(path.toFile()));
                    });

        } catch(IOException oiErr) {
            System.err.println(oiErr.getMessage());
        }

        // L'on scan chaque dossier JSON pour en récupéré lesdonnée et les ajoutés au périmètre si ils correspondent à l'attendu.
        for (JsonNode root : listJsonFile) {
            JsonNode perimetre = perimetreJsonTypeSpecifique(root, type, colonneObligatoire);
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
