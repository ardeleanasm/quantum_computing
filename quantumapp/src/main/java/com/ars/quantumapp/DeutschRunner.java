package com.ars.quantumapp;


import com.ars.algorithms.QuantumAlgorithms;
import com.ars.algorithms.deutsch.DeutschsAlgorithm;

public class DeutschRunner implements Runnable {
	private static final QuantumAlgorithms	ALGORITHM		= new DeutschsAlgorithm();
	

	@Override
	public void run() {
		ALGORITHM.init();
		ALGORITHM.run();
		ALGORITHM.measure();
		
	}
}
