# 📊 Bibliothèque d’analyse de données en Java

![Build](https://github.com/xaelxx14/DevOps-Project/actions/workflows/ci.yml/badge.svg)

Projet de M1 INFO – UFR IM2AG – 2025  
Travail collaboratif autour du développement d’une bibliothèque Java pour la manipulation et l’analyse de données, inspirée de la bibliothèque **Pandas** en Python.
## Auteurs

- ERGIN Seçkin Yağmur – [@xaelxx14](https://github.com/xaelxx14) - seckin-yagmur.ergin@etu.univ-grenoble-alpes.fr
- BEN TANFOUS Zakaria – [@ZakzoukBT](https://github.com/ZakzoukBT) - zakaria.ben-tanfous@etu.univ-grenoble-alpes.fr

## Fonctionnalités

### Fonctionnalités de base :
- ✅ Création de `DataFrame` :
  - À partir de tableaux Java.
  - À partir de fichiers CSV (avec inférence ou définition des types de colonnes).
- ✅ Affichage :
  - Affichage complet d’un `DataFrame`.
  - Affichage des `n` premières ou dernières lignes.
- ✅ Sélection :
  - Sélection de lignes par index.
  - Sélection de colonnes par label.
  - Sélection avancée (e.g. par conditions sur les valeurs).
- ✅ Statistiques de base :
  - Moyenne, minimum, maximum sur les colonnes numériques.

## 🛠️ Outils utilisés

| Objectif | Outil |
|-|-|
| Versionnage du code | Git |
| Plateforme de dépôt | GitHub |
| Build & gestion de dépendances | Maven |
| Tests unitaires | JUnit |
| Couverture de code | Jacoco |
| Intégration continue (CI)| GitHub Actions
| Livraison Docker | Docker + DockerHub |
| Documentation | GitHub Pages |

# ⚙️ Workflows GitHub Actions

Plusieurs workflows ont été configurés pour automatiser les tâches clés du projet :

1. **Java CI** (`ci.yml`) :
   - Déclenché à chaque `push` ou `pull request` sur la branche `main`.
   - Étapes :
     - Validation du projet Maven.
     - Compilation du code.
     - Exécution des tests unitaires avec JUnit.
     - Génération de la documentation de notre librairie avec javadoc.

2. **Déploiement Docker** (`docker_image.yml`) :
   - Déclenché après la réussite du workflow **Java CI** sur la branche `main`. Consiste à construire et publier une image Docker sur le registre Docker.

3. **Publication Maven** (`deploy_maven.yml`) :
   - Déclenché après la réussite du workflow **Java CI** sur la branche `main`.
   - Étapes :
     - Construction et vérification du package Maven.
     - Publication automatique de la bibliothèque sur GitHub Packages.

## 🐳 Images Docker

Une image Docker est produite et publiée automatiquement après la réussite du workflow **Docker image**. Cette image execute notre fonction principale situé dans notre Main.java exposant plusieurs fonctionnalités de notre bibliothèque.

### Image Docker Produite :
- **Lien vers le dépôt** : [Image Docker - DevOps Project](https://github.com/xaelxx14/DevOps-Project/pkgs/container/devops-project)
