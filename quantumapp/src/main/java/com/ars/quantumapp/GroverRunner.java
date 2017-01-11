package com.ars.quantumapp;

import java.io.IOException;

import com.ars.algorithms.grover.GroversAlgorithm;

public class GroverRunner implements Runnable{

	@Override
	public void run() {
		GroversAlgorithm ga = null;
		try{
			ga = new GroversAlgorithm();
			for (int i = 0; i < 1000000; i++) {
				ga.init();
				ga.run();
				ga.measure();
			}
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ga != null)
				ga.close();
		}
	}

}
