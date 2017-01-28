package com.ars.gates;

import com.ars.qubits.Qubit;

public final class IdentityGate implements IGate{

	@Override
	public Qubit applyGate(Qubit inputQubit, int[] targetPosition,
			int[] conditions, int noOfEntangledQubits) {
		
		return inputQubit;
	}

}
