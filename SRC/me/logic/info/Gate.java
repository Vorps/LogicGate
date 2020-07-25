package me.logic.info;


import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Classe pour la définition d'une porte logique
 */
public class Gate extends Actor{

	/**
	 * Ensemble des signaux en entrées et en sorties de la porte logique
	 * @see Wire
	 */
	private Wires wires;

	/**
	 * Si la porte logique est de type combinatoire la sortie dépend des entrée et cette fonction permet de définir son comportement
	 */
	private GateActivator activate;

	/**
	 * Si la porte logique est de type structurel elle est définit a partir d'un ensemble de porte logique cette méthode permet de définir le cablage de ces porte logiques lors de son initialisation
	 */
	private GateLoad loader;
	/**
	 * Donne la vitesse de propagation de la porte logique
	 */
	private int delay;


	public int getDelay(){
		return this.delay;
	}
	public void setDelay(int delay){
		this.delay = delay;
	}

	/**
	 * Affectation des signaux en entrées et sorties de la porte logique
	 * @param wires Ensemble des signaux à cabler
	 * @return Gate
	 */
	public Gate setWires(Wires wires){
		if(this.wires == null) this.wires = wires;
		else this.wires.update(wires);
		if(this.loader != null) this.loader.activate(this.wires.wiresInput, this.wires.wiresOutput);
		else
			for(int i = 0; i < wires.wiresInput.length;i++) //Permet de ne redéfinir uniquement que les premiers signaux
				this.wires.wiresInput[i].addGate(this);
		return this;
	}

	public Gate setWires(Wire[] wiresInput, Wire[] wireOutput){
		this.setWires(new Wires(wiresInput, wireOutput));
		return this;
	}

	public Wires getSignals(){
		return this.wires;
	}

	public void activate(){
		this.wires.wiresOutput[0].setSignal(this.activate.activate(this.wires.getInputsSignals()));
	}

	private Wires getNewWire(int wiresInputNb, int wiresOutputNb, String[] nameWire){
		Wire[]  wiresInput = new Wire[wiresInputNb];
		Wire[]  wiresOutput = new Wire[wiresOutputNb];
		for(int i = 0; i < wiresInputNb; i++) wiresInput[i] = new Wire(nameWire[i]);
		for(int i = 0; i < wiresOutputNb; i++) wiresOutput[i] = new Wire(nameWire[wiresInputNb+i]);
		return new Wires(wiresInput, wiresOutput);
	}

	public Gate(String name,int delay,  int wiresInputNb, int wiresOutputNb, GateActivator activate, String[] nameWire) {
		super(name);
		this.activate = activate;
		this.setWires(this.getNewWire(wiresInputNb, wiresOutputNb, nameWire));
		this.delay = delay;
	}

	public Gate(String name,  int wiresInputNb, int wiresOutputNb, GateLoad loader, String[] nameWire) {
		super(name);
		this.loader = loader;
		this.setWires(this.getNewWire(wiresInputNb, wiresOutputNb, nameWire));
	}

	
	@Override
	public String toString() {
		return this.name;
	}

	public Gate clone(String name){
		Gate gate = this.activate != null ?
			new Gate(name,this.delay, this.wires.wiresInput.length, this.wires.wiresOutput.length,  this.activate,  Stream.concat(Arrays.stream(this.wires.wiresInput).map(Actor::getName), Arrays.stream(this.wires.wiresOutput).map(Actor::getName)).toArray(String[]::new))
		: new Gate(name, this.wires.wiresInput.length, this.wires.wiresOutput.length, this.loader, Stream.concat(Arrays.stream(this.wires.wiresInput).map(Actor::getName), Arrays.stream(this.wires.wiresOutput).map(Actor::getName)).toArray(String[]::new));
		return gate;
	}

	public String info(){
		return this+"\nDelay : "+this.delay+" ms \n"+this.getSignals();
	}
}
