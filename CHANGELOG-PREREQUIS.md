# Changement 1 — Prérequis pédagogiques

Cette évolution conserve tous les fichiers de la V1 et ajoute les relations de prérequis et les équivalences.

## Hypothèses métier à valider
- Un prérequis est considéré validé si l’étudiant a **au moins une note >= 10/20** à un examen du module requis. C’est une simplification : elle ne calcule pas la moyenne finale du module.
- Une équivalence est enregistrée manuellement par l’API (aucune authentification/autorisation n’est implémentée dans cette V1).
- Les prérequis sont directs ; les cycles de prérequis sont interdits.
- Les prérequis sont vérifiés au moment de la création de l’inscription, sans réévaluation automatique des inscriptions existantes.

## Nouveaux endpoints
- `POST /api/courses/{courseId}/prerequisites` avec `{ "prerequisiteCourseId": 1 }`
- `GET /api/courses/{courseId}/prerequisites`
- `DELETE /api/courses/{courseId}/prerequisites/{requiredId}`
- `POST /api/equivalences` avec `{ "studentId": 1, "prerequisiteCourseId": 2 }`

## Test manuel
1. Créer deux modules A et B et un étudiant.
2. Ajouter A comme prérequis de B.
3. Essayer d’inscrire l’étudiant à B : HTTP 409.
4. Créer un examen pour A et une note >= 10 pour l’étudiant (selon les règles existantes de saisie des notes).
5. Réessayer l’inscription à B : succès.
6. Avec un autre étudiant, reconnaître une équivalence pour A puis l’inscrire à B : succès.

## Attention
Le contrôle d'équivalence nécessite une gestion des permissions avant tout usage réel. Les données existantes ne sont pas effacées. `spring.jpa.hibernate.ddl-auto=update` est acceptable pour une démonstration locale ; préférer Flyway pour des migrations en production.
