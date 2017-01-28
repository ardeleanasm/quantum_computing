package com.ars.algorithms.jozsa;

import com.ars.algorithms.MeasurementPerformer;
import com.ars.algorithms.QuantumAlgorithms;
import com.ars.gates.EGateTypes;
import com.ars.gates.IGate;
import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.quantum.utils.QRegisterOperations;
import com.ars.quantum.utils.QuantumOperations;
import com.ars.qubits.QRegister;

public class JozsaAlgorithm extends QuantumAlgorithms {

	private static final int NO_OF_QUBITS = 3;
	private IGate gateHadamard;
	private IGate oracle;


	public JozsaAlgorithm(){
	}

	@Override
	public void init() {
		gateHadamard = gateFactory.getGate(EGateTypes.E_HadamardGate);
		oracle = gateFactory.getGate(EGateTypes.E_CPhaseShift);
		QRegister reg=new QRegister(NO_OF_QUBITS);
		try {
			reg=QRegisterOperations.getInstance().fillWithPattern("000");
		} catch (RegisterOverflowException e) {
			e.printStackTrace();
		}
		resultQubit=QuantumOperations.entangle(reg);

	}

	@Override
	public void measure() {
		MeasurementPerformer measureObject = new MeasurementPerformer();
		// Measure qubit |000>
		measureObject = measureObject.configure(resultQubit);
		resultQubit = measureObject.measure();
	}

	@Override
	public void run() {
		// First Step: Apply H Gate to |000>
		resultQubit = gateHadamard.applyGate(resultQubit, new int[]{0,1,2}, null, NO_OF_QUBITS);
		// Second Step:Apply oracle
		resultQubit = oracle.applyGate(resultQubit, new int[]{2}, new int[]{0,1}, NO_OF_QUBITS);
		// Third Step: Apply H Gate
		resultQubit = gateHadamard.applyGate(resultQubit, new int[]{0,1,2}, null, NO_OF_QUBITS);
	}


}
