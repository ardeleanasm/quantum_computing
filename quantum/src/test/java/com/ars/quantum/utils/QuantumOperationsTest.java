package com.ars.quantum.utils;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ars.complexnumbers.ComplexNumber;
import com.ars.qubits.QRegister;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitOne;
import com.ars.qubits.QubitZero;

public class QuantumOperationsTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEntangleWithTwoQubitsAllCombinations() {
		ComplexNumber[] expectedResult = new ComplexNumber[4];
		Qubit[] q = new Qubit[2];
		q[0] = new QubitZero();
		q[1] = new QubitOne();
		Qubit[] realResults = new Qubit[4];
		// prepare results
		int k = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				realResults[k++] = QuantumOperations.entangle(q[i], q[j]);
			}
		}
		// prepare expected results
		for (int i = 0; i < 4; i++) {
			// fill expected result for each testcase
			for (int j = 0; j < 4; j++) {
				if (i == j) {
					expectedResult[j] = new ComplexNumber(1, 0);
				} else {
					expectedResult[j] = new ComplexNumber(0, 0);
				}

			}
			assertEquals(true, realResults[i].equals(new Qubit(expectedResult)));

		}
	}

	@Test
	public void testEntangleWithThreeQubitsAllCombinations() {
		QRegister qRegister = new QRegister(3).initialize();
		Qubit qubit = new QubitZero();
		qubit = QuantumOperations.entangle(qubit, new QubitZero());
		qubit = QuantumOperations.entangle(qubit, new QubitZero());
		assertEquals(qubit, QuantumOperations.entangle(qRegister));

	}



	@Test
	public void testCalculateOuterProduct() {
		ComplexNumber[][] expected = new ComplexNumber[][] {
		        { new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0) },
		        { new ComplexNumber(0.0, 0.0), new ComplexNumber(0.0, 0.0) } };
		ComplexNumber[][] actual = QuantumOperations.outerProduct(new QubitZero(), new QubitZero());
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[0].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
		actual = QuantumOperations.outerProduct(new QubitZero().getQubit(), new QubitZero().getQubit());
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[0].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
	}

	@Test
	public void testInnerProduct() {
		ComplexNumber[] a = new ComplexNumber[] { new ComplexNumber(1.0, 0.0), new ComplexNumber(2.0, 0.0) };
		ComplexNumber[] b = new ComplexNumber[] { new ComplexNumber(3.0, 0.0), new ComplexNumber(4.0, 0.0) };
		ComplexNumber expected = new ComplexNumber(11.0, 0.0);
		assertEquals(expected, QuantumOperations.innerProduct(a, b));
		assertEquals(expected, QuantumOperations.innerProduct(new Qubit(a), new Qubit(b)));
	}

	@Test
	public void testTranspose() {
		ComplexNumber[][] expected = new ComplexNumber[][] {
		        { new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0) } };
		ComplexNumber[][] actual = QuantumOperations.transpose(new QubitZero());
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[0].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
		actual = QuantumOperations.transpose(new QubitZero().getQubit());
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[0].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
	}

}
