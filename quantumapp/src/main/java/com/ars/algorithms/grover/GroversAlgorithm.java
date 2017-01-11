package com.ars.algorithms.grover;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import com.ars.algorithms.MeasurementPerformer;
import com.ars.algorithms.QuantumAlgorithms;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.gates.EGateTypes;
import com.ars.gates.IGate;
import com.ars.quantum.utils.MatrixOperations;
import com.ars.quantum.utils.QuantumOperations;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitPlus;
import com.ars.qubits.QubitZero;

public class GroversAlgorithm extends QuantumAlgorithms {
	private static final int NO_OF_INPUT = 3;
	private IGate gateH;
	private double[][] gateHn;
	private static final Qubit QUBIT_0 = new QubitZero();
	private Qubit qubitPlus;

	private double[][] oracleMatrix = { { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
			{ 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
			{ 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0 } };
	private BufferedWriter bw;
	private ComplexNumber[][] diffusionMatrix;

	public GroversAlgorithm(String filePath) throws IOException, SecurityException {
		bw = new BufferedWriter(new FileWriter(new File(filePath)));
	}

	public GroversAlgorithm() throws IOException, SecurityException {
		File outputDir = new File("output");

		if (!outputDir.exists())
			outputDir.mkdir();

		bw = new BufferedWriter(new FileWriter(new File(outputDir, "out.csv")));
	}

	private void generateDiffusionMatrix() {
		diffusionMatrix = QuantumOperations.outerProduct(qubitPlus, qubitPlus);
		diffusionMatrix = MatrixOperations.multiplyByConstant(diffusionMatrix, 2);
		ComplexNumber[][] identityMatrix = MatrixOperations.generateIdentityMatrix(8);
		diffusionMatrix = MatrixOperations.subtract(diffusionMatrix, identityMatrix);
	}

	@Override
	public void init() {
		gateH = gateFactory.getGate(EGateTypes.E_HadamardGate);
		resultQubit = QUBIT_0;
		qubitPlus = new QubitPlus();
		for (int i = 0; i < NO_OF_INPUT - 1; i++) {
			resultQubit = QuantumOperations.entangle(resultQubit, QUBIT_0);
		}
		for (int i = 0; i < NO_OF_INPUT - 1; i++) {
			qubitPlus = QuantumOperations.entangle(qubitPlus, new QubitPlus());
		}
		gateHn = gateH.getUnitaryMatrix();
		for (int i = 0; i < NO_OF_INPUT - 1; i++) {
			gateHn = MatrixOperations.tensorProduct(gateHn, gateH.getUnitaryMatrix());
		}
		setOracle(oracleMatrix);
		generateDiffusionMatrix();
	}

	@Override
	public void run() {
		int noOfIterations = (int) Math.sqrt(Math.pow(2, NO_OF_INPUT));
		resultQubit = QuantumOperations.applyGate(resultQubit, gateHn);
		for (int i = 0; i < noOfIterations + 1; i++) {
			resultQubit = QuantumOperations.applyGate(resultQubit, oracleMatrix);
			resultQubit = QuantumOperations.applyGate(resultQubit, diffusionMatrix);
		}
		assert (resultQubit.isValid() == true);
	}

	@Override
	public void measure() {
		MeasurementPerformer measurementPerformer = new MeasurementPerformer().configure(resultQubit);
		resultQubit = measurementPerformer.measure();

		try {

			for (ComplexNumber c : resultQubit.getQubit()) {
				bw.write(c.getReal() + ",");

			}
			bw.write("\n");
			// bw.newLine();
			// bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
