package com.ars.gates;

import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.Qubit;

/**
 * Implements the CNOT Gate
 * 
 *
 */
public final class CNotGate implements IGate {

	@Override
	public Qubit applyGate(Qubit inputQubit, int[] targetPosition,
			int[] conditions, int noOfEntangledQubits) {

		int mask = 0;
		int newPosition = 0;
		ComplexNumber[] states = inputQubit.getQubit();
		for (int i : conditions) {
			mask |= (1 << (noOfEntangledQubits - 1 - i));
		}
		mask |= (1 << (noOfEntangledQubits - targetPosition[0]));
		newPosition = mask | 0x01;
		ComplexNumber state = states[mask];
		states[mask] = states[newPosition];
		states[newPosition] = state;

		return new Qubit(states);
	}

}