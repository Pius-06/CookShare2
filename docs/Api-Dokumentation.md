## API-Dokumentation
Momentan existieren folgende Endpunkte:

- `GET /recipes` – alle Rezepte abrufen
- `GET /recipes/me/{id}` – eigene Rezepte des angemeldeten Nutzers
- `POST /recipes` – neues Rezept erstellen (Body: `RecipeRequestDTO`)
- `PUT /recipes/{id}` – bestehendes Rezept bearbeiten
- `DELETE /recipes/{id}` – Rezept löschen
- `GET /users`, `GET /users/{id}`, `PUT /users/{id}`, `DELETE /users/{id}` – Benutzerverwaltung

## Beispiele
### curl-Beispiele
```bash
# alle Rezepte
curl -X GET http://localhost:8080/recipes

# Rezept anlegen
curl -X POST http://localhost:8080/recipes \
  -H "Content-Type: application/json" \
  -d '{"authorId":1,"title":"Test","introduction":"...","preparation":"...","duration":{},"ingredients":[],"servings":1,"category":"Dinner","difficulty":"Easy","isPublic":true}'
```
