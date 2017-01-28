package com.ars.algorithms.grover;


import com.ars.algorithms.MeasurementPerformer;
import com.ars.algorithms.QuantumAlgorithms;
import com.ars.gates.EGateTypes;
import com.ars.gates.IGate;
import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.quantum.utils.QRegisterOperations;
import com.ars.quantum.utils.QuantumOperations;
import com.ars.qubits.QRegister;

public class GroversAlgorithm extends QuantumAlgorithms {
	private static final int NO_OF_INPUT = 3;
	private IGate gateH;
	private IGate gateX;
	private IGate gateCNot;
	private IGate gateCPhaseShift;
	

	public GroversAlgorithm() {


	}


	@Override
	public void init() {
		gateH = gateFactory.getGate(EGateTypes.E_HadamardGate);
		gateX = gateFactory.getGate(EGateTypes.E_XGate);
		gateCNot = gateFactory.getGate(EGateTypes.E_CNotGate);
		gateCPhaseShift = gateFactory.getGate(EGateTypes.E_CPhaseShift);
		QRegister qRegister=new QRegister(NO_OF_INPUT+1);
		QRegisterOperations qRegOps=QRegisterOperations.getInstance();
		try {
			qRegister=qRegOps.fillWithPattern("0001");
		} catch (RegisterOverflowException e) {
			e.printStackTrace();
		}
		resultQubit=QuantumOperations.entangle(qRegister);
	}

	@Override
	public void run() {
		resultQubit=gateH.applyGate(resultQubit, new int[]{0,1,2,3}, null, NO_OF_INPUT+1);
		resultQubit=gateCNot.applyGate(resultQubit, new int[]{3}, new int[]{0,1,2}, NO_OF_INPUT+1);
		
		
		resultQubit=gateH.applyGate(resultQubit, new int[]{0,1,2}, null, NO_OF_INPUT+1);
		resultQubit=gateX.applyGate(resultQubit, new int[]{0,1,2}, null, NO_OF_INPUT+1);
		resultQubit=gateCPhaseShift.applyGate(resultQubit, new int[]{2}, new int[]{0,1}, NO_OF_INPUT+1);
		
		resultQubit=gateX.applyGate(resultQubit, new int[]{0,1,2}, null, NO_OF_INPUT+1);
		resultQubit=gateH.applyGate(resultQubit, new int[]{0,1,2,3}, null, NO_OF_INPUT+1);
		
		
		
		
	}

	@Override
	public void measure() {
		MeasurementPerformer measurementPerformer = new MeasurementPerformer().configure(resultQubit);
		resultQubit = measurementPerformer.measure();
		System.out.println(resultQubit);

	}



}
