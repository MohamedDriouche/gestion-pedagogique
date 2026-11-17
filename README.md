# Gestion pédagogique — V1 monolithique

Application REST Spring Boot / MySQL. Java 17, Maven, Spring Data JPA, Bean Validation.

## Démarrer

1. Installer Java 17+, Maven et Docker (ou une instance MySQL locale).
2. Dans ce dossier : `docker compose up -d`.
3. Attendre que MySQL soit prêt : `docker compose ps`.
4. Exécuter : `mvn spring-boot:run`.
5. API sur `http://localhost:8080`.
6. Tests : `mvn test`.

Pour MySQL externe, définir `DB_URL`, `DB_USER`, `DB_PASSWORD`.
Les identifiants du compose sont réservés au développement local. Ne pas exposer cette V1 sans authentification sur Internet.

## Endpoints

| Méthode | URL | Description |
|---|---|---|
| GET, POST | /api/students | Lister / créer étudiants |
| GET, PUT, DELETE | /api/students/{id} | Consulter / modifier / supprimer |
| GET, POST | /api/courses | Lister / créer modules |
| GET, PUT, DELETE | /api/courses/{id} | Consulter / modifier / supprimer |
| POST | /api/registrations | Inscrire un étudiant |
| GET | /api/registrations | Toutes les inscriptions |
| GET | /api/students/{id}/registrations | Inscriptions d'un étudiant |
| GET, POST | /api/exams | Lister / planifier examens |
| GET, PUT, DELETE | /api/exams/{id} | Consulter / modifier / supprimer |
| GET, POST | /api/grades | Lister / saisir notes |
| GET, PUT, DELETE | /api/grades/{id} | Consulter / modifier / supprimer |
| GET | /api/grades/students/{id} | Notes d'un étudiant |

## Exemples JSON (Postman : Body > raw > JSON)

**POST /api/students**
```json
{"name":"Sara","email":"sara@example.com","program":"Génie Informatique"}
```
**POST /api/courses**
```json
{"code":"GI101","title":"Architecture logicielle","capacity":30}
```
**POST /api/registrations** (utiliser les IDs retournés)
```json
{"studentId":1,"courseId":1}
```
**POST /api/exams**
```json
{"courseId":1,"date":"2026-12-15T09:00:00"}
```
**POST /api/grades**
```json
{"examId":1,"studentId":1,"value":16.5}
```

## Règles et hypothèses V1

- Une inscription `ACTIVE` est autorisée si étudiant et module existent, si l'étudiant n'est pas déjà inscrit et si la capacité n'est pas atteinte.
- Le verrou pessimiste sur le module évite de dépasser la capacité lors de demandes concurrentes.
- Les notes sont comprises entre 0 et 20, et exigent une inscription ACTIVE au module de l'examen.
- Un étudiant ou module avec des dépendances ne peut pas être supprimé.
- Un examen possédant des notes ne peut pas être supprimé.
- Un e-mail étudiant et un code module doivent être uniques selon les vérifications applicatives.
- La gestion des enseignants, des absences, l'annulation d'inscription, les comptes et l'authentification sont hors périmètre de la V1 montrée dans les diapositives.
- Ces règles supplémentaires sont des hypothèses à faire valider par le client.

## Structure

- `entity/` : modèle JPA
- `repository/` : accès aux données
- `service/` : règles métier et transactions
- `controller/` : API REST
- `dto/` : données d'entrée dédiées
- `exception/` : erreurs HTTP

`ddl-auto=update` est adapté à une démonstration locale, pas à une production. Utiliser des migrations Flyway/Liquibase pour une version déployée. Pas d'authentification dans cette V1 pédagogique.
