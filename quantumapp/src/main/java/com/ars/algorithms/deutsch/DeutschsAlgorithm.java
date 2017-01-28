package com.ars.algorithms.deutsch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ars.algorithms.QuantumAlgorithms;
import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.gates.EGateTypes;
import com.ars.gates.IGate;
import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.quantum.utils.QRegisterOperations;
import com.ars.quantum.utils.QuantumOperations;
import com.ars.qubits.QRegister;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitOne;
import com.ars.qubits.QubitZero;

public class DeutschsAlgorithm extends QuantumAlgorithms {
	private static final Logger LOGGER = LogManager
			.getLogger(DeutschsAlgorithm.class);
	private static final Qubit QUBIT_0 = new QubitZero();
	private static final Qubit QUBIT_1 = new QubitOne();
	private IGate gateH;
	private IGate gateX;
	private IGate oracle;

	private double[] measurementResults;

	public DeutschsAlgorithm(double[][] testedFunctionOperator) {
		super(testedFunctionOperator);

	}

	public DeutschsAlgorithm() {

	}

	@Override
	public void init() {
		LOGGER.info("Started initialization...");
		gateH = gateFactory.getGate(EGateTypes.E_HadamardGate);
		gateX = gateFactory.getGate(EGateTypes.E_XGate);
		oracle = gateFactory.getGate(EGateTypes.E_IGate);
		LOGGER.info("Finished initialization.");
	}

	@Override
	public void run() {
		QRegister qReg = new QRegister(2);
		try {
			qReg = QRegisterOperations.getInstance().fillWithPattern("01");
		} catch (RegisterOverflowException e) {
			e.printStackTrace();
		}
		resultQubit = QuantumOperations.entangle(qReg);

		/**
		 * The following operations are performed: entangle qubitZero (qubitZero
		 * |> gateX) |> gateH2 |> f |> gateH2
		 */
		LOGGER.info("Started running algorithm...");
		resultQubit = gateX.applyGate(resultQubit, new int[] { 1 }, null, 2);
		resultQubit = gateH.applyGate(resultQubit, new int[] { 0, 1 }, null, 2);
		resultQubit = oracle
				.applyGate(resultQubit, new int[] { 0, 1 }, null, 2);
		resultQubit = gateH.applyGate(resultQubit, new int[] { 0 }, null, 2);

		LOGGER.info("Finished running algorithm.");
	}

	@Override
	public void measure() {
		LOGGER.info("Started measurement process...");
		measurementResults = new double[resultQubit.getQubit().length];
		int i = 0;
		for (ComplexNumber c : resultQubit.getQubit()) {
			measurementResults[i++] = Math.round(ComplexMath.multiply(c,
					ComplexMath.conjugate(c)).getReal());
		}
		LOGGER.info("Finished measurement process...");
	}

	public boolean isFunctionConstant() {
		int i = 0;
		ComplexNumber[] q01 = QuantumOperations.entangle(QUBIT_0, QUBIT_1)
				.getQubit();
		for (ComplexNumber c : q01) {
			if (Double.compare(Math.round(ComplexMath.multiply(c,
					ComplexMath.conjugate(c)).getReal()),
					measurementResults[i++]) != 0) {
				return false;
			}
		}
		return true;

	}

	public boolean isFunctionBalanced() {
		int i = 0;
		ComplexNumber[] q01 = QuantumOperations.entangle(QUBIT_1, QUBIT_1)
				.getQubit();
		for (ComplexNumber c : q01) {
			if (Double.compare(Math.round(ComplexMath.multiply(c,
					ComplexMath.conjugate(c)).getReal()),
					measurementResults[i++]) != 0) {
				return false;
			}
		}
		return true;
	}

}
