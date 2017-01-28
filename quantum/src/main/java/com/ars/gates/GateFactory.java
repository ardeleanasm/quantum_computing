package com.ars.gates;
/**
 * 
 * Implementation of a Gate Factory.
 *
 */
public class GateFactory extends GatesAbstractFactory {

	/**
	 * getGate return a new <code>Gate</code> object
	 * @param id specify the type of gate.
	 * @return gate 
	 *  
	 */
	public IGate getGate(EGateTypes id) {
		IGate gate = null;
		switch (id) {
		case E_HadamardGate:
			gate = new HGate();
			break;
		case E_XGate:
			gate = new XGate();
			break;
		case E_YGate:
			gate=new YGate();
			break;
		case E_ZGate:
			gate = new ZGate();
			break;
		case E_CNotGate:
			gate = new CNotGate();
			break;
		case E_CPhaseShift:
			gate=new E_CPhaseShift();
			break;
		}
		return gate;
	}
}
