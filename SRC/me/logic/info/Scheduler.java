package me.logic.info;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe de gestion d'activation des portes logiques dans le temps
 */
public class Scheduler  {


	private static Scheduler scheduler;

	/**
		Pour les infos de simulation
	 */
	public DefaultListModel<Trace> traces;

	public static Scheduler getScheduler() {
		if (Scheduler.scheduler == null) Scheduler.scheduler = new Scheduler();
		return Scheduler.scheduler;
	}

	public Scheduler(){
		this.traces = new DefaultListModel<>();
	}

	/**
	 * Start la simulation
	 */
	public void schedule(LinkedHashSet<Gate> client) {
		for(Gate gate : client){

			this.traces.addElement(new Trace(gate.getName()+" Delay : "+gate.getDelay()+" ms",  Arrays.stream(gate.getSignals().wiresInput).map(Wire::info).collect(Collectors.toList()).toString(), Arrays.stream(gate.getSignals().wiresOutput).map(Wire::info).collect(Collectors.toList()).toString()));
			new Thread(() -> {

				try {
					Thread.sleep(gate.getDelay());
					gate.activate();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}
