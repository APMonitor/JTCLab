package edu.jtclab;

import com.fazecast.jSerialComm.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class JTCLab {

	static String[] ARDUINOS = new String[]{
			"Arduino Uno",
			"NHduino",
			"Arduino Leonardo",
			"Arduino.org device",
			"unknown device"
			};
	
	SerialPort arduinoPort;
	double p1Power = 200;
	double p2Power = 100;
	char SEP = ' '; //command or value separator in TCLab firmware
	double LED_DEF = 50;  //default LED power level
	double MIN_DEF = 0;
	double MAX_DEF = 100;
	boolean debug = false;
	
	/**Locates Arduino among the computers usb ports
	 * @return returns the SerialPort object of the port to which the arduino is connected
	 * */
	private static SerialPort findArduino() {
		boolean portFound = false;
		SerialPort foundArduinoPort = SerialPort.getCommPorts()[0];
		while (!portFound) {
			for(SerialPort port : SerialPort.getCommPorts()) {
				if(Arrays.asList(ARDUINOS).contains(port.getPortDescription())) {
					System.out.println(port.getPortDescription() + 
							" connected on " + port.getSystemPortName() +
							" at " + Integer.toString(port.getBaudRate()) + " baud");
					foundArduinoPort = port;
					portFound = true;
				}
			}
		}
		return foundArduinoPort;
	}
	
	/**Limit value to be between lower and upper limits with default values*/
	private double clip(double val) {
		if (val < MIN_DEF) {
			return MIN_DEF;
		}
		else if (val > MAX_DEF) {
			return MAX_DEF;
		}
		else {
			return val;
		}
	}
	
	/**Limit value to be between lower and upper limits*/
	private double clip(double val, double min, double max) {
		if (val < min) {
			return min;
		}
		else if (val > max) {
			return max;
		}
		else {
			return val;
		}
	}
	
	/**Construct command to TCLab-sketch with default clip values*/
	private String command(String name, double argument) {
		return name + SEP + Double.toString(clip(argument, MIN_DEF, MAX_DEF));
	}
	
	/**Construct command to TCLab-sketch*/
	private String command(String name, double argument, double min, double max) {
		return name + SEP + Double.toString(clip(argument, min, max));
	}
	
	/**Send a string message to the TCLab firmware */
	private void send(String msg) {
		PrintWriter output = new PrintWriter(arduinoPort.getOutputStream());
		output.print(msg);
		output.flush();
		if (debug) {System.out.println("Sent: '" + msg + "'");}
	}
	
	/**Return a string message received from the TCLab firmware*/
	private String receive() {
		Scanner data = new Scanner(arduinoPort.getInputStream());
		while (!(data.hasNextLine())) {
		}
		String received = data.nextLine();
		if (debug) {System.out.println("Received: '" + received + "'");}
		data.close();
		return received;
	}
	
	/**Send a string message and return the response*/
	private String sendAndReceive(String msg) {
		this.send(msg);
		return this.receive();
	}
	
	/**Turn on TCLab LED at a default brightness*/
	public double LED() {
		return Double.parseDouble(this.sendAndReceive(command("LED", LED_DEF)));
	}
	
	/**Turn on TCLab LED*/
	public double LED(double val) {
		return Double.parseDouble(this.sendAndReceive(command("LED", val)));
	}
	
	/**Return a float denoting TCLab temperature T1 in degrees C*/
	public double T1() {
		return Double.parseDouble(this.sendAndReceive("T1"));
	}
	
	/**Return a float denoting TCLab temperature T2 in degrees C*/
	public double T2() {
		return Double.parseDouble(this.sendAndReceive("T2"));
	}
	
	/**Return a float denoting maximum power of heater 1 in pwm*/
	public double P1() {
		return this.p1Power;
	}
	
	/**Set maximum power of heater 1 in pwm, range 0 to 255*/
	public void P1(double val) {
		this.p1Power = Double.parseDouble(this.sendAndReceive(command("P1", val, 0, 255)));
	}
	
	/**Return a float denoting maximum power of heater 2 in pwm*/
	public double P2() {
		return this.p2Power;
	}
	
	/**Set maximum power of heater 2 in pwm, range 0 to 255*/
	public void P2(double val) {
		this.p2Power = Double.parseDouble(this.sendAndReceive(command("P2", val, 0, 255)));
	}
	
	/**get TCLab heater power Q1*/
	public String Q1() {
		return "Q1 doesn't work without a parameter between 0 and 100";//(this.sendAndReceive("R1"));
	}
	
	/**set TCLab heater power Q1*/
	public double Q1(double val) {
		return Double.parseDouble(this.sendAndReceive("Q1" + SEP + Double.toString(clip(val))));
	}
	
	/**get TCLab heater power Q2*/
	public String Q2() {
		return "Q2 doesn't work without a parameter between 0 and 100";//(this.sendAndReceive("R2"));
	}
	
	/**set TCLab heater power Q2*/
	public double Q2(double val) {
		return Double.parseDouble(this.sendAndReceive("Q2" + SEP + Double.toString(clip(val))));
	}
	
	public JTCLab() {
		arduinoPort = findArduino();
		arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		if (arduinoPort.openPort()) {
			System.out.println("Port opened successfully");
		}
		else {
			System.out.println("Unable to open port. Reconnect your Arduino and try again.");
		}
	}
	
	public JTCLab(boolean debug) {
		this.debug = debug;
		arduinoPort = findArduino();
		arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		if (arduinoPort.openPort()) {
			System.out.println("Port opened successfully");
		}
		else {
			System.out.println("Unable to open port. Reconnect your Arduino and try again.");
		}
	}
	
	public void close() {
		this.LED(0);
		this.Q1(0);
		this.Q2(0);
		arduinoPort.closePort();
		System.out.println("TCLab disconnected successfully");
	}
}
