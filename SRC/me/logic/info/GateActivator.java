package me.logic.info;

/**
 * Interface pour le changement d'états des sorties en fonction des états des entrées d'une porte logique
 */
@FunctionalInterface
public interface GateActivator{

     /**
      * Fonction permettant le changement d'états des sorties en fonction des états des entrées
      * @param input Etats des entrées de la portes logiques
      * @return
      */
     boolean activate(Boolean[] input);
}
