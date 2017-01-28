package com.ars.quantum.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.qubits.QRegister;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitOne;
import com.ars.qubits.QubitZero;

public class QRegistersTest {
	private QRegister			qRegister;
	private static final int	REGISTER_LENGTH	= 3;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialization() {
		qRegister = new QRegister(REGISTER_LENGTH);
		qRegister = QRegisterOperations.getInstance().fillWith(qRegister, QubitZero::new);
		assertEquals(REGISTER_LENGTH, qRegister.size());
		for (Qubit q : qRegister) {
			assertEquals(new QubitZero(), q);
		}
	}

	@Test
	public void testInit() {
		Qubit q1 = new QubitOne();
		Qubit q0 = new QubitZero();
		try {
			qRegister = QRegisterOperations.getInstance().fillWithPattern("1101");
		} catch (RegisterOverflowException e) {

			e.printStackTrace();
		}
		assertEquals(4, qRegister.size());
		assertEquals(q1, qRegister.get(0));
		assertEquals(q1, qRegister.get(1));
		assertEquals(q0, qRegister.get(2));
		assertEquals(q1, qRegister.get(3));
	}
}
