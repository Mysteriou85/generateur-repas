# Générateur de repas
Cette app est un simple webservice qui permet de générer des compositions aléatoires de plat. Le but principal est pour moi de m'exercer à la création d'un webservice ainsi qu'a utilisation de Jackson pour des données enregistré en JSON.

# Requête REST
Liste de requête GET et POST.

|       | Requête | Description |
| ----- | ------- | ----------- |
| GET | /plat |  Récupère une description d'un plat sans composant ajouté. |
| GET | /plat/{nbre} | Récupère une description d'un plat avec un nombre de composant ajouté définie. |
| POST | /ingredient/ | Envois un ingrédient. (ne fait rien) |
| GET | /ingredient/all | Récupère la totalité des ingrédients au format JSON. | 
| GET | /ingredient/random | Récupère un ingrédient aléatoire au format JSON. |
| GET | /ingredient/id/{id} | Récupère un ingrédient par son id. |
| GET | /cuisson/all | Récupère la totalité des cuissons au format JSON. | 
| GET | /cuisson/random | Récupère une cuisson aléatoire au format JSON. |
| GET | /cuisson/id/{id} | Récupère une cuisson par son id. |