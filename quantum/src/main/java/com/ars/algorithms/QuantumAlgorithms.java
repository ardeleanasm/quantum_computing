package com.ars.algorithms;



import com.ars.gates.GateProducer;
import com.ars.gates.GatesAbstractFactory;
import com.ars.gates.IGate;
import com.ars.qubits.Qubit;

public abstract class QuantumAlgorithms {
	protected IGate oracle;
	protected Qubit resultQubit;
	protected GatesAbstractFactory gateFactory = GateProducer.getGateFactory();


	public QuantumAlgorithms() {

	}

	public QuantumAlgorithms(IGate oracle) {
		this.oracle = oracle;
	}

	public void setOracle(IGate oracle) {
		this.oracle = oracle;
	}

	public abstract void init();

	public abstract void run();

	public abstract void measure();


}
