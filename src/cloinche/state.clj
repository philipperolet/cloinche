(ns cloinche.state
  "Etat d'une partie, représenté par une liste de taille 3 (etat, atout, deck) 
  Etat
  --
  INITIAL -> cartes non distribuées
  CUT -> jeu coupé
  DEALT -> cartes distribuées selon la règle 3-2-3 à partir de l'initial
  0 -> Enchères faites, atout connu, la partie peut commencer
  1 -> le joueur 1 (à droite du donneur) a joué sa première carte
  2,3, 4-> idem joueur 2, 3 et 4 ont joué leur 1ère carte
  5 -> le joueur ayant gagné le dernier pli a joué sa 2e carte
  6, 7, 8 -> les joueurs suivants ont joué leurs 2e carte
  9-32-> idem pour les plis 3 à 8
  
  Atout
  --
  UNDECIDED si enchères pas faites
  0-3 -> pique, coeur, carreau, trèfle
  
  Deck
  --
  Permutation des entiers de 0 à 31 représentant
  les cartes du jeu, dans l'ordre où elles se trouvent selon l'état de
  la partie
  
  Les cartes sont codées de la façon suivante: 0 à 7 = sept de pique à
  as de pique, ordre croissant. 8 à 15, idem coeur, 16 à 31 idem
  carreau puis trèfle Une fois la partie débutée (à partir de l'état
  DEALT), les cartes représentent dans l'ordre les cartes du joueur 1,
  puis 2, puis 3, puis 4, puis les plis dans l'ordre du premier au
  dernier (incluant celui en cours).")

(defn valid-deck? [deck]
  (and
    (coll? deck)
    (= (count deck) 32) ;; 32 is more readable than a nb-cards constant
    (= (sort deck) (range 32))))

(defn create-new-state
  ([]
   '[INITIAL UNDECIDED (range 32)]) ;; 32 is more readable than a constant
  ([deck]
   {:pre [(valid-deck? deck)]}
   '[INITIAL UNDECIDED deck]))

   


  
