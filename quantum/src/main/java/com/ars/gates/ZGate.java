package com.ars.gates;

import java.util.Arrays;

import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.Qubit;

/**
 * 
 * Implements the Pauli-Z Gate.
 *
 */
public final class ZGate implements IGate {

	@Override
	public Qubit applyGate(Qubit inputQubit, int[] targetPosition,
			int[] conditions, int noOfEntangledQubits) {
		int mask = 0;
		int newPosition = 0;
		ComplexNumber[] states = inputQubit.getQubit();
		int[] markedStates = new int[states.length];
		for (int i : targetPosition) {
			Arrays.fill(markedStates, 0);
			mask = (1 << (noOfEntangledQubits - 1 - i));
			for (int j = 0; j < states.length; j++) {
				if (markedStates[j] == 0) {
					newPosition = j ^ mask;
					states[j] = ComplexMath.multiply(states[j],
							new ComplexNumber(1.0, 0.0));
					states[newPosition] = ComplexMath.multiply(
							states[newPosition], new ComplexNumber(-1.0, 0.0));
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
