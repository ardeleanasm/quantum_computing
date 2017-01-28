package com.ars.quantum.utils;

import com.ars.quantum.exception.IncorrectMatrixSizeException;

import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.QRegister;
import com.ars.qubits.Qubit;

/**
 * Implementations of basic operations that can be applied on qubits.
 * 
 *
 */
public class QuantumOperations {

	/**
	 * Performs the tensor product of two qubits. Example, if q1=|0> and q2=|1>
	 * the method will return |01>.
	 * 
	 * @param q1 first qubit
	 * @param q2 second qubit
	 * @return qubit the tensor product of the two qubits.
	 */
	public static Qubit entangle(Qubit q1, Qubit q2) {

		return performTensorProduct(q1, q2);
	}

	/**
	 * Perform the tensor product between two or more qubits. Example, for three
	 * qubits |0>, |0> and |1>, the result will be |001>.
	 * 
	 * @param Variable number of qubits 
	 * @return qubit the tensor product of the two qubits.
	 */
	public static Qubit entangle(Qubit...qubits){
		Qubit bufferQubit=qubits[0];
		for(int i=1;i<qubits.length;i++){
			bufferQubit = performTensorProduct(bufferQubit, qubits[i]);
		}
		return bufferQubit;
	}
	


	private static Qubit performTensorProduct(Qubit q1, Qubit q2) {
		int len1 = q1.getQubit().length;
		int len2 = q2.getQubit().length;
		ComplexNumber[] complexNumberList = new ComplexNumber[len1 * len2];
		int k = 0;
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				complexNumberList[k++] = ComplexMath.multiply(q1.getQubit()[i], q2.getQubit()[j]);
			}
		}

		return new Qubit(complexNumberList);
	}


	
	
	private static ComplexNumber[][] calculateTranspose(ComplexNumber[] z) {
		ComplexNumber[][] transpose = new ComplexNumber[1][z.length];
		for (int i = 0; i < z.length; i++) {
			transpose[0][i] = z[i];
		}

		return transpose;
	}

	/**
	 * Calculate the transpose of qubit; |q>-> <q|
	 * 
	 * @param q qubit.
	 * @return ComplexNumber[][]
	 */
	public static ComplexNumber[][] transpose(Qubit q) {
		return calculateTranspose(q.getQubit());
	}

	/**
	 * Calculate the transpose of qubit; |q>-> <q|
	 * 
	 * @param q qubit.
	 * @return ComplexNumber[][]
	 */
	public static ComplexNumber[][] transpose(ComplexNumber[] z) {
		return calculateTranspose(z);
	}

	private static ComplexNumber[][] calculateOuterProduct(ComplexNumber[] z1, ComplexNumber[] z2) {
		ComplexNumber[][] result = null;
		if (z1.length == z2.length) {
			ComplexNumber[][] transposeSecondArray = calculateTranspose(z2);
			int numberOfRows = z1.length;
			int numberOfRowsSecondMatrix = 1;
			int numberOfCollsSecondMatrix = transposeSecondArray[0].length;
			result = new ComplexNumber[numberOfRows][numberOfCollsSecondMatrix];
			ComplexNumber sum = new ComplexNumber(0.0, 0.0);
			// fill matrix with 0;
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfCollsSecondMatrix; j++) {
					for (int k = 0; k < numberOfRowsSecondMatrix; k++) {
						sum = ComplexMath.add(sum, ComplexMath.multiply(z1[i], transposeSecondArray[k][j]));
					}
					result[i][j] = sum;
					sum = new ComplexNumber();
				}
			}
		}else{
			throw new IncorrectMatrixSizeException("Both state arrays must have the same size.");
		}
	
		return result;
	}

	
	
	/**
	 * Performs the outer product of two qubits |q1><q2|
	 * 
	 * @param q1 first qubit
	 * @param q2 second qubit
	 * @return ComplexNumber the outer product of the two qubits.
	 */
	public static ComplexNumber[][] outerProduct(Qubit q1, Qubit q2) {
		return calculateOuterProduct(q1.getQubit(), q2.getQubit());
	}

	/**
	 * Performs the outer product of two qubits |q1><q2|
	 * 
	 * @param z1 first qubit
	 * @param z2 second qubit
	 * @return ComplexNumber the outer product of the two qubits.
	 */
	public static ComplexNumber[][] outerProduct(ComplexNumber[] z1, ComplexNumber[] z2) {
		return calculateOuterProduct(z1, z2);
	}

	private static ComplexNumber calculateInnerProduct(ComplexNumber[] z1, ComplexNumber[] z2) {
		ComplexNumber result = new ComplexNumber(0.0, 0.0);
		if (z1.length == z2.length) {
			ComplexNumber[][] transposeFirstArray = calculateTranspose(z1);
			int numberOfRows = z2.length;

			for (int i = 0; i < numberOfRows; i++) {
				result = ComplexMath.add(result, ComplexMath.multiply(transposeFirstArray[0][i], z2[i]));
			}
		}else{
			throw new IncorrectMatrixSizeException("Both state arrays must have the same size.");
		}
	
		return result;
	}

	/**
	 * Performs the inner product of two qubits <q1|q2>
	 * 
	 * @param q1 first qubit
	 * @param q2 second qubit
	 * @return ComplexNumber the inner product of the two qubits.
	 */
	public static ComplexNumber innerProduct(Qubit q1, Qubit q2) {
		return calculateInnerProduct(q1.getQubit(), q2.getQubit());
	}
	
	/**
	 * Performs the inner product of two qubits <q1|q2>
	 * 
	 * @param z1 first qubit
	 * @param z2 second qubit
	 * @return ComplexNumber the inner product of the two qubits.
	 */
	public static ComplexNumber innerProduct(ComplexNumber[] z1, ComplexNumber[] z2) {
		return calculateInnerProduct(z1, z2);
	}

}
