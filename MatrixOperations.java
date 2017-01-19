package com.ars.quantum.utils;

import java.util.Arrays;

import com.ars.complexnumbers.ComplexMath;
import com.ars.complexnumbers.ComplexNumber;

/**
 * Implementations of basic operations with 2D arrays
 * 
 *
 */


public class MatrixOperations {

	/**
	 * Performs the multiplication between 2 2D arrays of double
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of double
	 */
	public static double[][] multiply(double[][] a, double[][] b) {
		
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		int numberOfRowsSecondMatrix = b.length;
		int numberOfCollsSecondMatrix = b[0].length;
		double sum = 0.0;
		double[][] multiply = null;
		try{
		if (numberOfColls == numberOfRowsSecondMatrix) {
			multiply = new double[numberOfRows][numberOfCollsSecondMatrix];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfCollsSecondMatrix; j++) {
					for (int k = 0; k < numberOfRowsSecondMatrix; k++) {
						sum = sum + a[i][k] * b[k][j];
					}

					multiply[i][j] = sum;
					sum = 0;
				}
			}
			
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfColls + " did not match B:Rows " + numberOfRowsSecondMatrix);
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfCollsSecondMatrix + "or/and" + numberOfRowsSecondMatrix +  " is invalid");
		}
		return multiply;
	}

	/**
	 * Performs the multiplication between 2 2D arrays of complex numbers
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] multiply(ComplexNumber[][] a, ComplexNumber[][] b) {
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		int numberOfRowsSecondMatrix = b.length;
		int numberOfCollsSecondMatrix = b[0].length;
		ComplexNumber sum = new ComplexNumber();
		ComplexNumber[][] multiply = null;
		try{
		if (numberOfColls == numberOfRowsSecondMatrix) {
			multiply = new ComplexNumber[numberOfRows][numberOfCollsSecondMatrix];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfCollsSecondMatrix; j++) {
					for (int k = 0; k < numberOfRowsSecondMatrix; k++) {
						sum = ComplexMath.add(sum, ComplexMath.multiply(a[i][k], b[k][j]));
						
					}

					multiply[i][j] = sum;
					sum = new ComplexNumber();
					
				}
			}
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfColls + " did not match B:Rows " + numberOfRowsSecondMatrix);
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfCollsSecondMatrix + "or/and" + numberOfRowsSecondMatrix +  " is invalid");
		}
		return multiply;
	}

	/**
	 * Performs the sum between 2 2D arrays of double
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of double
	 */
	public static double[][] add(double[][] a, double[][] b) {
		double[][] result = null;
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length == b[0].length && a.length == b.length) {
			result = new double[numberOfRows][a.length];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColls; j++) {
					result[i][j] = a[i][j] + b[i][j];
					
				}
			}
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfRows + " did not match B:Rows " + numberOfColls);
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return result;
	}

	/**
	 * Performs the sum between 2 2D arrays of complex numbers
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] add(ComplexNumber[][] a, ComplexNumber[][] b) {
		ComplexNumber[][] result = null;
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length == b[0].length && a.length == b.length) {
			result = new ComplexNumber[numberOfRows][numberOfColls];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColls; j++) {
					result[i][j] = ComplexMath.add(a[i][j], b[i][j]);
				
				}
				
			}
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfRows + " did not match B:Rows " + numberOfColls);
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return result;
	}

	/**
	 * Performs the subtract between 2 2D arrays of double
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of double
	 */
	public static double[][] subtract(double[][] a, double[][] b) {
		double[][] result = null;
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length == b[0].length && a.length == b.length) {
			result = new double[numberOfRows][numberOfColls];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColls; j++) {
					result[i][j] = a[i][j] - b[i][j];
					
				}
			}
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfRows + " did not match B:Rows " + numberOfColls);
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return result;
	}

	/**
	 * Performs the subtract between 2 2D arrays of complex numbers
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] subtract(ComplexNumber[][] a, ComplexNumber[][] b) {
		ComplexNumber[][] result = null;
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length == b[0].length && a.length == b.length) {
			result = new ComplexNumber[numberOfRows][numberOfColls];
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColls; j++) {
					result[i][j] = ComplexMath.subtract(a[i][j], b[i][j]);
					
				}
			}
		}
		else{
			throw new IllegalArgumentException("A:Colls: " + numberOfRows + " did not match B:Rows " + numberOfColls);
		}

		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return result;
	}

	/**
	 * Check if 2 2D arrays of double are equal.
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return boolean true if the two matrices are equal, otherwise false.
	 */
	public static boolean areEqual(double[][] a, double[][] b) {
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length == b[0].length && a.length == b.length) {
			for (int i = 0; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColls; j++) {
					if (a[i][j] != b[i][j]) {
						return false;
						
					}
				}
			}
			return true;
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return false;
	}

	/**
	 * Check if 2 2D arrays of complex numbers are equal.
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return boolean true if the two matrices are equal, otherwise false.
	 */
	public static boolean areEqual(ComplexNumber[][] a, ComplexNumber[][] b) {
		int numberOfRows = a.length;
		int numberOfColls = a[0].length;
		try{
		if (a[0].length != b[0].length || a.length != b.length) {
			return false;
		}

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColls; j++) {
				if (!a[i][j].equals(b[j][j])) {
					return false;
				}
			}
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls + " is invalid");
		}
		return true;
	}

	private static double[][] performMultiplicationWithConstant(double[][] a, double ct, int iPosition, int jPosition,
			double[][] resultMatrix) {
		int numberOfRowsMatrixA = a.length;
		int numberOfCollsMatrixA = a[0].length;
		double[][] result = resultMatrix;
		try{
		for (int i = 0; i < numberOfRowsMatrixA; i++) {
			for (int j = 0; j < numberOfCollsMatrixA; j++) {
				result[i + iPosition][j + jPosition] = ct * a[i][j];
				//throw new NumberFormatException(numberOfRowsMatrixA + "or/and" + "or/and" + numberOfCollsMatrixA + "or/and" + ct +  " is invalid");
			}
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRowsMatrixA + "or/and"  + numberOfCollsMatrixA +  " is invalid");
		}
		return result;
	}

	/**
	 * Performs the tensor product between 2 2D arrays of double
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of double
	 */
	public static double[][] tensorProduct(double[][] a, double[][] b) {
		int k = 0;
		int numberOfRowsMatrixA = a.length;
		int numberOfCollsMatrixA = a[0].length;
		int numberOfRowsMatrixB = b.length;
		int numberOfCollsMatrixB = b[0].length;
		int numberOfRowsResultMatrix = numberOfRowsMatrixA * numberOfRowsMatrixB;
		int numberOfCollsResultMatrix = numberOfCollsMatrixA * +numberOfCollsMatrixB;
		double[][] result = new double[numberOfRowsResultMatrix][numberOfCollsResultMatrix];
		try{
		for (int i = 0; i < numberOfRowsResultMatrix; i++) {
			Arrays.fill(result[i], 0.0);
			//throw new NumberFormatException(numberOfRowsResultMatrix +  " is invalid");
		}
		for (int i = 0; i < numberOfRowsMatrixA; i++) {
			for (int j = 0; j < numberOfCollsMatrixA; j++) {
				result = performMultiplicationWithConstant(b, a[i][j], k, j * 2, result);
				//throw new NumberFormatException(numberOfCollsMatrixA +  " is invalid");
			}
			k += 2;
		}
	}
	catch(NumberFormatException e){
		System.out.println(numberOfRowsMatrixA + "or/and" + numberOfCollsMatrixA +  " is invalid");
	}
		return result;
	}

	/**
	 * Performs the tensor product between 2 2D arrays of complex numbers
	 * 
	 * @param a first 2D array
	 * @param b second 2D array
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] tensorProduct(ComplexNumber[][] a, ComplexNumber[][] b) {
		int k = 0;
		int numberOfRowsMatrixA = a.length;
		int numberOfCollsMatrixA = a[0].length;
		int numberOfRowsMatrixB = b.length;
		int numberOfCollsMatrixB = b[0].length;
		int numberOfRowsResultMatrix = numberOfRowsMatrixA * numberOfRowsMatrixB;
		int numberOfCollsResultMatrix = numberOfCollsMatrixA * +numberOfCollsMatrixB;
		ComplexNumber[][] result = new ComplexNumber[numberOfRowsResultMatrix][numberOfCollsResultMatrix];
		try{
		for (int i = 0; i < numberOfRowsResultMatrix; i++) {
			for (int j = 0; j < numberOfCollsResultMatrix; j++) {
				result[i][j] = new ComplexNumber(0.0, 0.0);
				//throw new NumberFormatException(numberOfRowsResultMatrix + "or/and "+ numberOfCollsResultMatrix +  " is invalid");
			}
		}
		for (int i = 0; i < numberOfRowsMatrixA; i++) {
			for (int j = 0; j < numberOfCollsMatrixA; j++) {
				result = performMultiplicationWithConstant(b, a[i][j], k, j * 2, result);
				//throw new NumberFormatException(numberOfRowsMatrixA + "or/and "+ numberOfCollsMatrixA +  " is invalid");
			}
			k += 2;
		}
		}
	catch(NumberFormatException e){
		System.out.println(numberOfRowsMatrixA + "or/and" + "or/and" + numberOfCollsMatrixA +  " is invalid");
	}
		return result;
	}

	private static ComplexNumber[][] performMultiplicationWithConstant(ComplexNumber[][] a, ComplexNumber ct,
			int iPosition, int jPosition, ComplexNumber[][] resultMatrix) {
		int numberOfRowsMatrixA = a.length;
		int numberOfCollsMatrixA = a[0].length;
		ComplexNumber[][] result = resultMatrix;
		try{
		for (int i = 0; i < numberOfRowsMatrixA; i++) {
			for (int j = 0; j < numberOfCollsMatrixA; j++) {
				result[i + iPosition][j + jPosition] = ComplexMath.multiply(ct, a[i][j]);
				//throw new NumberFormatException(numberOfRowsMatrixA + "or/and" + "or/and" + numberOfCollsMatrixA + "or/and" + ct +  " is invalid");

			}
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRowsMatrixA + "or/and" + "or/and" + numberOfCollsMatrixA + "or/and" + ct +  " is invalid");
		}
		return result;
	}


	/**
	 * Generate an Identity matrix
	 * @param numberOfRows the number of rows of the identity matrix
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] generateIdentityMatrix(int numberOfRows) {
		ComplexNumber[][] identityMatrix = new ComplexNumber[numberOfRows][numberOfRows];
		try{
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfRows; j++) {
				if (i == j) {
					identityMatrix[i][j] = new ComplexNumber(1.0, 0.0);
					continue;
				}

				identityMatrix[i][j] = new ComplexNumber(0.0, 0.0);

			}
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows + " is invalid");
		}
		return identityMatrix;
	}
	
	/**
	 * Perform multiplication between a matrix and a constant.
	 * @param a matrix
	 * @param ct constant
	 * @return 2D array of complex numbers
	 */
	public static ComplexNumber[][] multiplyByConstant(ComplexNumber[][] a,double ct){
		int numberOfRows=a.length;
		int numberOfColls=a[0].length;
		ComplexNumber[][] resultMatrix=new ComplexNumber[numberOfRows][numberOfColls];
		try{
		for(int i=0;i<numberOfRows;i++){
			for(int j=0;j<numberOfColls;j++){
				resultMatrix[i][j]=ComplexMath.multiply(a[i][j], ct);
			}
		}
		}
		catch(NumberFormatException e){
			System.out.println(numberOfRows +  "or/and" + numberOfColls +  " is invalid");
		}
		return resultMatrix;
	}
}
