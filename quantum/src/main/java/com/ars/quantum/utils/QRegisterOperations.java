package com.ars.quantum.utils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.qubits.QRegister;
import com.ars.qubits.Qubit;
import com.ars.qubits.QubitOne;
import com.ars.qubits.QubitZero;

public class QRegisterOperations {
	private static final QRegisterOperations INSTANCE = new QRegisterOperations();

	private QRegisterOperations() {
	}

	public static QRegisterOperations getInstance() {
		return INSTANCE;
	}

	public List<Qubit> fillWith(List<Qubit> list,
			Supplier<Qubit> qubitSupplier, int size) {
		list = Stream.generate(qubitSupplier).limit(size)
				.collect(Collectors.toList());
		return list;
	}

	public QRegister fillWith(QRegister reg, Supplier<Qubit> qubitSupplier) {
		reg.setQubits(Stream.generate(qubitSupplier).limit(reg.size())
				.collect(Collectors.toList()));
		return reg;
	}

	public QRegister fillWithPattern(String pattern)
			throws RegisterOverflowException {
		QRegister qreg = new QRegister(pattern.length()).initialize();
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '1') {
				qreg.change(i, new QubitOne());
			} else {
				qreg.change(i, new QubitZero());
			}
		}
		return qreg;
	}

	/**
	 * Perform the tensor product between two or more qubits. Example, for three
	 * qubits |0>, |0> and |1>, the result will be |001>.
	 * 
	 * @param quantumRegister
	 * @return qubit the tensor product of the two qubits.
	 */
	public Qubit entangle(QRegister quantumRegister) {
		if (quantumRegister.size() < 2) {
			return null;
		}
		Qubit bufferQubit = quantumRegister.get(0);
		for (int i = 1; i < quantumRegister.size(); i++) {
			bufferQubit = QuantumOperations.entangle(bufferQubit,
					quantumRegister.get(i));

		}
		return bufferQubit;
	}

}
