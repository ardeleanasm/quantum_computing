package com.ars.gates;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuantumGatesTest {
	private GatesAbstractFactory factory;
	private IGate gate;

	@Before
	public void setUp() throws Exception {
		factory = GateProducer.getGateFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHGate() {
		double[][] expectedMatrix = { { 1 / Math.sqrt(2.0), 1 / Math.sqrt(2.0) },
				{ 1 / Math.sqrt(2.0), -1 / Math.sqrt(2.0) } };
		gate = factory.getGate(EGateTypes.E_HadamardGate);
		for (int i = 0; i < 2; i++) {
			double delta = 0.5e-10;
			assertArrayEquals(expectedMatrix[i], gate.getUnitaryMatrix()[i], delta);
		}
	}

	@Test
	public void testZGate() {
		double[][] expectedMatrix = { { 1, 0 }, { 0, -1 } };
		gate = factory.getGate(EGateTypes.E_ZGate);
		for (int i = 0; i < 2; i++) {
			double delta = 0.5e-10;
			assertArrayEquals(expectedMatrix[i], gate.getUnitaryMatrix()[i], delta);
		}
	}

	@Test
	public void testXGate() {
		double[][] expectedMatrix = { { 0, 1 }, { 1, 0 } };
		gate = factory.getGate(EGateTypes.E_XGate);
		for (int i = 0; i < 2; i++) {
			double delta = 0.5e-10;
			assertArrayEquals(expectedMatrix[i], gate.getUnitaryMatrix()[i], delta);
		}
	}

	@Test
	public void testCNotGate() {
		double[][] expectedMatrix = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 } };
		gate = factory.getGate(EGateTypes.E_CNotGate);
		for (int i = 0; i < 2; i++) {
			double delta = 0.5e-10;
			assertArrayEquals(expectedMatrix[i], gate.getUnitaryMatrix()[i], delta);
		}
	}

	@Test
	public void testSwapGate() {
		double[][] expectedMatrix = { { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 1 } };
		gate = factory.getGate(EGateTypes.E_SwapGate);
		for (int i = 0; i < 2; i++) {
			double delta = 0.5e-10;
			assertArrayEquals(expectedMatrix[i], gate.getUnitaryMatrix()[i], delta);
		}
	}
}
