package me.logic.info;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Classe regroupant les signaux d'entrées et de sorties des portes logiques
 */
public class Wires{

    public Wire[] wiresInput;
    public Wire[] wiresOutput;

    public Wires(Wire[] wiresInput,  Wire[] wiresOutput) {
        this.wiresInput = wiresInput;
        this.wiresOutput = wiresOutput;
    }

    /**
     * Met à jour les wires
     * @param wire Wires
     */
    public void update(Wires wire){
        for(int i = 0; i < wire.wiresInput.length;i++) this.wiresInput[i] = wire.wiresInput[i];
        for(int i = 0; i < wire.wiresOutput.length;i++) this.wiresOutput[i] = wire.wiresOutput[i];
    }

    /**
     * Retourne les états des signaux d'entrées
     * @return
     */
    public Boolean[] getInputsSignals(){
        return Arrays.stream(wiresInput).map(Wire::getSignal).collect(Collectors.toList()).toArray(new Boolean[wiresInput.length]);
    }

    @Override
    public String toString() {
        return "\n\tInputs : "+Arrays.stream(this.wiresInput).map(Wire::info).collect(Collectors.toList()).toString()+" \n\tOutputs : "+Arrays.stream(this.wiresOutput).map(Wire::info).collect(Collectors.toList()).toString();
    }
}