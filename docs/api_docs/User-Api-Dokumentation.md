# User API Dokumentation

Base URL: `http://localhost:8080`  
Authentifizierung: JWT Bearer Token (`Authorization: Bearer <token>`)

---

## Übersicht

| Methode | Pfad | Auth | Beschreibung |
|--------|------|------|--------------|
| `GET` | `/users` | – | Alle User abrufen |
| `GET` | `/users/{id}` | – | Einzelnen User abrufen |
| `PUT` | `/users/{id}` | Eigener Account | User-Daten aktualisieren |
| `PUT` | `/users/password/{id}` | Eigener Account | Passwort ändern |
| `DELETE` | `/users/{id}` | Eigener Account / ADMIN | User löschen |
