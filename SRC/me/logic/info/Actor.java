package me.logic.info;

/**
 * Classe mère de la classe Gate et Wire permettant de donner un nom ainsi qu'une info
 */
public abstract class Actor {

    protected String name;

    public Actor(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Méthode qui retourne des informations sur l'état de l'actor
     * @return String
     */
    public abstract String info();
}
