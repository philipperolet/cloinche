(ns cloinche.state
  "Etat d'une partie, représenté par une map {::state ::trump ::deck}

  Step
  --
  :initial -> cartes non distribuées
  :cut -> jeu coupé
  :dealt -> cartes distribuées selon la règle 3-2-3 à partir de l'initial
  0 -> Enchères faites, atout connu, la partie peut commencer
  1 -> le joueur 1 (à droite du donneur) a joué sa première carte
  2,3, 4-> idem joueur 2, 3 et 4 ont joué leur 1ère carte
  5 -> le joueur ayant gagné le dernier pli a joué sa 2e carte
  6, 7, 8 -> les joueurs suivants ont joué leurs 2e carte
  9-32-> idem pour les plis 3 à 8
  Ainsi, lorsque l'état est un nombre, il correspond au nombre de cartes jouées.
  
  Trump
  --
  :undecided si enchères pas faites
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
  dernier (incluant celui en cours)."
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [cloinche.utils :as u]))

(s/def ::step (s/or
               :pre-hands #{:initial :cut :dealt}
               :hands (s/int-in 0 32)))

(s/def ::trump (s/or
                :undecided #{:undecided}
                :trump #{0 1 2 3}))

(s/def ::deck (s/with-gen
                (s/and ::u/permutation #(= (count %) 32))
                #(gen/shuffle (range 32))))


(s/def ::state (s/and
                (s/keys :req [::step ::trump ::deck])
                ;; L'atout est décidé ssi la partie est commencée
                #(not (and (int? (% ::step)) (= (% ::trump) :undecided)))
                #(not (and (int? (% ::trump)) (not (int? (% ::step)))))))

(s/fdef create-new-state
  :args (s/or :noarg nil? :deck (s/cat :deck ::deck))
  :ret ::state)

(defn create-new-state
  "Crée un nouvel état de début de partie, avec un deck random si pas
  d'arguments (et le deck spécifié si arguments)"
  ([]
   {::step :initial
    ::trump :undecided
    ::deck (u/gen-permutation 32)}) ;; 32 is more readable than a constant
  ([deck]
   {::step :initial
    ::trump :undecided
    ::deck deck}))

(s/fdef cut
  :args (s/and (s/cat :state ::state)
               #(= (-> :state :step %) :initial))
  :ret ::state
  :fn (s/and #(= (-> % :ret :step) :cut)))

(defn cut [state]
  "Coupe le jeu à une position aléatoire, d'au moins 5 cartes"
  {::step :cut
   ::trump :undecided
   ::deck (u/shift-permutation (state ::deck) (rand-int 5 27))})
