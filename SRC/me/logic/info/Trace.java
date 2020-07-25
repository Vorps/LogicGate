package me.logic.info;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Classe pour les informations de simulation
 */
public class Trace {

    /**
     * Porte logique
     */
    private String gate;
    /**
     * Etats des entrées
     */
    private String input;
    /**
     * Etats des sorties
     */
    private String output;
    /**
     * Date de l'evenement
     */
    private Long date;

    public Trace(String gate, String input, String output)
    {
        this.gate = gate;
        this.input = input;
        this.output = output;
        this.date = System.currentTimeMillis();
    }

    public String getGate(){
        return this.gate;
    }

    public String getInput(){
        return this.input;
    }
     public String getOutput(){
        return this.output;
     }

     public Long getDate(){
        return this.date;
     }
}
