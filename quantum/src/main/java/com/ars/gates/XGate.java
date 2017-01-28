package com.ars.gates;

import java.util.Arrays;

import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.Qubit;

/**
 * 
 * Implement the Pauli-X Gate.
 *
 */
public final class XGate implements IGate {

	@Override
	public Qubit applyGate(Qubit inputQubit, int[] targetPosition,
			int[] conditions, int noOfEntangledQubits) {
		int mask = 0;
		int newPosition = 0;
		ComplexNumber[] states = inputQubit.getQubit();
		ComplexNumber bufferState;
		int[] markedStates = new int[states.length];
		for (int i : targetPosition) {
			Arrays.fill(markedStates, 0);
			mask = (1 << (noOfEntangledQubits - 1 - i));
			for (int j = 0; j < states.length; j++) {
				if (markedStates[j] == 0) {
					bufferState = states[j];
					newPosition = j ^ mask;
					states[j] = states[newPosition];
					states[newPosition] = bufferState;
					markedStates[j] = 1;
					markedStates[newPosition] = 1;
				}
				continue;
			}
			mask = 0;
		}
		return new Qubit(states);

	}

}
