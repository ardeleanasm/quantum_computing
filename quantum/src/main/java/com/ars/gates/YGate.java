package com.ars.gates;

import java.util.Arrays;

import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.Qubit;

public final class YGate implements IGate {

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
					newPosition = j ^ mask;
					bufferState = states[j];
					states[j] = ComplexMath.multiply(new ComplexNumber(0.0,
							-1.0), states[newPosition]);
					states[newPosition] = ComplexMath.multiply(
							new ComplexNumber(0.0, 1.0), bufferState);
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
