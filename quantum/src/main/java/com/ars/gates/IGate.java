package com.ars.gates;

import com.ars.qubits.Qubit;

/**
 * 
 * Interface for all types of Quantum Gates.
 *
 */
public interface IGate {
	
	public Qubit applyGate(Qubit inputQubit,int[] targetPosition,int[] conditions,int noOfEntangledQubits);
	
	
	
}
