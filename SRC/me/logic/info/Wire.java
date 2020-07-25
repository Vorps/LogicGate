package me.logic.info;

import java.util.LinkedHashSet;

/**
 * Pour la définition des signaux cablés aux portes logiques
 */
public class Wire extends Actor{

	/**
	 * Etat du signal
	 */
	private boolean signal;

	/**
	 * Utile pour la première activation de la porte logique
	 */
	private boolean first;

	/**
	 * Le signal connait les portes logiques dont il est connecté
	 */
	private LinkedHashSet<Gate> gates;

	public Wire(String name) {
		super(name);
		this.gates = new LinkedHashSet<>();
	}

	@Override
	public String info() {
		return this.name + " "+ signal;
	}


	/**
	 * Ajout de porte logique
	 * @param gate Porte logic
	 * @see Gate
	 */
	public void addGate(Gate gate) {
		this.gates.add(gate);
	}


	/**
	 * Changement d'état du signal
	 * @param signal
	 */
	public void setSignal(boolean signal) {
		if(this.signal != signal || !this.first) {
			this.first = true;
			this.signal = signal;
			if(!this.gates.isEmpty()) Scheduler.getScheduler().schedule(new  LinkedHashSet<>(this.gates));
		}
	}
	
	public boolean getSignal() {
		return this.signal;
	}


	@Override
	public String toString() {
		return this.signal+"";
	}
}
