package com.ars.quantumapp;


import com.ars.algorithms.grover.GroversAlgorithm;

public class GroverRunner implements Runnable {

	@Override
	public void run() {
		GroversAlgorithm ga = null;
		ga = new GroversAlgorithm();
		ga.init();
		ga.run();
		ga.measure();
		
	}

}
