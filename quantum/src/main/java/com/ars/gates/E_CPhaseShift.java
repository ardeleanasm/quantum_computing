package com.ars.gates;

import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.Qubit;

public final class E_CPhaseShift implements IGate {

	@Override
	public Qubit applyGate(Qubit inputQubit, int[] targetPosition,
			int[] conditions, int noOfEntangledQubits) {
		int mask = 0;
		int new_position = 0;

		ComplexNumber[] states = inputQubit.getQubit();
		for (int i : conditions) {
			mask |= (1 << (noOfEntangledQubits - 1 - i));
		}
		mask |= (1 << (noOfEntangledQubits - targetPosition[0] - 1));
		new_position = mask | 0x01;
		states[mask] = ComplexMath.multiply(states[mask], new ComplexNumber(
				-1.0, 0.0));

		states[new_position] = ComplexMath.multiply(states[new_position],
				new ComplexNumber(-1.0, 0.0));

		return new Qubit(states);
	}

}
