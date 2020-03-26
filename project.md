# Backlog
- refactorer create-new-state pour supprimer tests / pre-conds inutiles & bien faire avec les specs
- identifier si d'autres tests sont à faire pour create-new-state
- décider d'où vont les specs & la doc -- test file vs source file (religion)
- chercher spec vs pre-conditions ou te faire une religion
- coder l'action de coupe
- coder l'action de distribution
- coder l'action d'enchère
- coder l'action du jeu d'une carte par un joueur

### Clojure
- pourquoi spec  devient lent pour la génération de permutations:
  - de taille 1000 avec ta fonction custom?
  - de taille 100000 avec la primitive shuffle?
- quand / comment instrumenter mes runs?

### IDE
- faire démarrer jackin & chargement des bons ns pour les tests au lancement du projet
- rafraichir suffisamment bien les ns lors du testing pour ne pas avoir de vieux tests / etc.
  - e.g. binder la sauvegarde à raffraichir ns + évaluer?
  - oui si automatisation de la sauvegarde par ailleurs
- modifier C-c M-e pour changer le namespace et pretty-printer
- améliorer le flot projet
  - màj lorsque tu sauves pour lancer un test
  - ouvrir le buffer d'erreur dans une fenêtre switchée bas
- gérer le raffraichissement de ns pour ne pas avoir les symboles qui s'accumulent quand on renomme une fonction/un test

## Done
- refactorer create-new-state pour supprimer tests / pre-conds inutiles & bien faire avec les specs
- mieux gérer l'affichage des erreurs de test de "check"
- créer un générateur de permutations
	- ajouter des property check
- voir ce que fait instrument en + de check
  => il assure en live que les calls se font avec des args qui marchent
- pourquoi ça devient lent pour des permutations de taille 1000
  - tester si c'est l'utilisation de liste au lieu de vecteur => un peu mais pas trop
- faire tourner les tests génératifs
- définir spec pour la fonction
- créer des specs
  - pour chaque sous-elt
  - pour state
- refactorer en utilisant un type pour l'état de jeu
- use precondition for check validity
- install clojure
- install Emacs IDE for clojure
- write a hello world in clojure

