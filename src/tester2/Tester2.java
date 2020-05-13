package tester2;

import edu.jtclab.JTCLab;

public class Tester2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Connect to the Arduino
		JTCLab myLab = new JTCLab();
		
		//Turn on the LED
		myLab.LED();
		
		//Print out initial temperatures from the two thermometers
		System.out.println("Temperature 1: " + Double.toString(myLab.T1()));
		System.out.println("Temperature 2: " + Double.toString(myLab.T2()));
		
		//Turn on heater 1 to 80% power and heater 2 to 50% power
		myLab.Q1(80);
		myLab.Q2(50);
		
		//Wait 30 seconds
		System.out.println("Waiting 30 seconds...");
		try {Thread.sleep(30000);} catch (InterruptedException e) {}
		
		//Print out final temperatures from the two thermometers
		System.out.println("Temperature 1: " + Double.toString(myLab.T1()));
		System.out.println("Temperature 2: " + Double.toString(myLab.T2()));
		
		//End the program and disconnect the Arduino
		myLab.close();
		
	}

}
