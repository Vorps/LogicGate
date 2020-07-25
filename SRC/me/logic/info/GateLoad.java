package me.logic.info;

/**
 * Interface pour la définition structurelle de la porte logique
 */
@FunctionalInterface
public interface GateLoad {

     /**
      * Cablages des signaux aux portes logique pour une définition structurelle de la porte logique
      * @param wireInput Signaux d'entrées
      * @param wireOutput Signaux de sorties
      */
     void activate(Wire[] wireInput, Wire[] wireOutput);
}
