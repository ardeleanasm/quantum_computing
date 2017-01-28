package com.ars.qubits;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ars.quantum.exception.RegisterOverflowException;
import com.ars.quantum.utils.QRegisterOperations;


public class QRegister implements Iterable<Qubit> {
	private List<Qubit>	qubitRegister;
	private int			registerSize;

	public QRegister(int size) {
		this.registerSize = size;
		qubitRegister = new ArrayList<>(size);

	}

	public QRegister initialize() {
		qubitRegister = QRegisterOperations.getInstance().fillWith(qubitRegister, QubitZero::new, registerSize);
		return this;
	}

	public int size() {
		return registerSize;
	}

	public void add(Qubit e) throws RegisterOverflowException {
		if (qubitRegister.size() >= registerSize) {
			throw new RegisterOverflowException();
		}
		qubitRegister.add(e);
	}

	public QRegister change(int index, Qubit e) {
		qubitRegister.set(index, e);
		return this;
	}

	public Qubit get(int index) {
		return qubitRegister.get(index);
	}

	@Override
	public Iterator<Qubit> iterator() {
		return qubitRegister.iterator();
	}

	public List<Qubit> getQubits() {
		return qubitRegister;
	}

	public void setQubits(List<Qubit> qubits) {
		qubitRegister = qubits;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Qubit q : qubitRegister)
			buffer.append(q);
		return buffer.toString();
	}
}
