package com.ars.algorithms.jozsa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ars.algorithms.MeasurementPerformer;
import com.ars.algorithms.QuantumAlgorithms;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.gates.EGateTypes;
import com.ars.gates.IGate;
import com.ars.quantum.utils.MatrixOperations;
import com.ars.quantum.utils.QuantumOperations;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitZero;

public class JozsaAlgorithm extends QuantumAlgorithms {

	private Qubit				inputQubit;
	private double[][]			constantFunctionOracle	= { { -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
	        { 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
	        { 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0 },
	        { 0.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0 },
	        { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0 } };

	private double[][]			balancedFunctionOracle	= { { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
	        { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
	        { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 },
	        { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 },
	        { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0 } };
	private double[][]			oracle;
	private static final int	NO_OF_QUBITS			= 3;
	private IGate				gateHadamard;
	private double[][]			gateHadamardN;
	private BufferedWriter		writer;

	public JozsaAlgorithm(String filePath) throws IOException, SecurityException {
		writer = new BufferedWriter(new FileWriter(new File(filePath)));
	}

	public JozsaAlgorithm() throws IOException, SecurityException {
		File outputDir = new File("output");

		if (!outputDir.exists())
			outputDir.mkdir();

		writer = new BufferedWriter(new FileWriter(new File(outputDir, "out.csv")));
	}

	@Override
	public void init() {
		oracle = constantFunctionOracle;
		inputQubit = new QubitZero();
		gateHadamardN = gateFactory.getGate(EGateTypes.E_HadamardGate).getUnitaryMatrix();
		gateHadamard = gateFactory.getGate(EGateTypes.E_HadamardGate);
		/* Calculate inputQubits to be |000> */
		for (int i = 0; i < NO_OF_QUBITS - 1; i++) {
			inputQubit = QuantumOperations.entangle(inputQubit, new QubitZero());
		}
		// Calculate H gate to be H^3
		for (int i = 0; i < NO_OF_QUBITS - 1; i++) {
			gateHadamardN = MatrixOperations.tensorProduct(gateHadamardN, gateHadamard.getUnitaryMatrix());
		}

		// for(int i=0;i<8;i++){
		// for(int j=0;j<8;j++){
		// System.out.print(gateHadamardN[i][j]+" ");
		// }
		// System.out.println();
		// }

	}

	@Override
	public void measure() {
		MeasurementPerformer measureObject = new MeasurementPerformer();
		// Measure qubit |000>
		measureObject = measureObject.configure(inputQubit);
		inputQubit = measureObject.measure();
		try {

			for (ComplexNumber c : inputQubit.getQubit()) {
				writer.write(c.getReal() + ",");

			}
			writer.write("\n");
			// bw.newLine();
			// bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// First Step: Apply H Gate to |000>
		inputQubit = QuantumOperations.applyGate(inputQubit, gateHadamardN);
		// Second Step:Apply oracle
		inputQubit = QuantumOperations.applyGate(inputQubit, oracle);
		// Third Step: Apply H Gate
		inputQubit = QuantumOperations.applyGate(inputQubit, gateHadamardN);
		// System.out.println(inputQubit);
	}

	public void isFunctionConstant() {
		Qubit testQubit = new QubitZero();
		for (int i = 0; i < NO_OF_QUBITS - 1; i++) {
			testQubit = QuantumOperations.entangle(testQubit, new QubitZero());
		}
		if (inputQubit.equals(testQubit)) {
			System.out.println("function is constant!");
		}
	}

}
