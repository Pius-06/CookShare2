# Git Team Workflow
Dieser Workflow beschreibt den grundlegenden Ablauf für die Zusammenarbeit im Team mit Git und GitHub.

## 1. Repository klonen

- git clone https://github.com/USER/REPO.git
- cd REPO

## 2. main aktualisieren
- git checkout main
- git pull origin main

## 3. Neuen Branch erstellen
- git checkout -b BRANCH-NAME

## 4. Änderungen machen und committen
- git status
- git add .
- git commit -m "COMMIT-MESSAGE"

## 5. Branch zu GitHub pushen
- Beim ersten Push: git push -u origin BRANCH-NAME
- Danach reicht: git push

## 6. Pull Request erstellen
- Auf GitHub:
    - Repository öffnen
    - Compare & Pull Request
    - Pull Request erstellen
    - Review durch Team
    - Merge in main

## 7. Nach dem Merge: main aktualisieren
- git checkout main
- git pull origin main

## 8. Alten Feature Branch löschen
- Lokal löschen: git branch -d feature-name
- Remote löschen (auf GitHub): git push origin --delete feature-name

## 9. Neues Feature starten
- git checkout -b NEW-BRANCH-NAME
Dann beginnt der Workflow wieder bei Schritt 4.

## Nützliche Befehle
- Aktuellen Status anzeigen: git status
- Branches anzeigen: git branch
- Remote Branches anzeigen: git branch -r
- Alle Branches anzeigen: git branch -a