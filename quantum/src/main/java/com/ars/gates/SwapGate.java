package com.ars.gates;

/**
 * 
 * Implement the Swap Gate.
 *
 */
public class SwapGate implements IGate {
	/**
	 * Matrix corresponding to a Swap Gate.
	 */
	private static final double[][] GATE_MATRIX = { { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 1} };

	/**
	 * Return the 2D array corresponding to a Swap gate.
	 * @return double[][] the corresponding 2D array 
	 */
	@Override
	public double[][] getUnitaryMatrix() {
		double[][] copyOfGateMatrix = GATE_MATRIX;
		return copyOfGateMatrix;
	}

}
