package tester;

import java.util.Scanner;
import edu.jtclab.JTCLab;
import java.util.HashMap;
import java.lang.Thread;

public class Tester {

	public static void main(String[] args) {
		
		HashMap<String, String> commands = new HashMap<String, String>();
		commands.put("help", "help");
		commands.put("temp1", "temp1");
		commands.put("temp2", "temp2");
		commands.put("set1", "set1");
		commands.put("set2", "set2");
		commands.put("LED", "LED");
		commands.put("close", "close");
		commands.put("clear", "clear");
		
		JTCLab myLab = new JTCLab();
		
		
		myLab.LED();
		
		Scanner userCtl = new Scanner(System.in);
		String uInput;
		
		while (true) {
			System.out.print(">");
			uInput = userCtl.nextLine();
			if (uInput.equals(commands.get("help"))){
				System.out.println("The following are valid commands: ");
				for (String commandText : commands.values()) {
					System.out.println("'" + commandText +"'");
				}
			}
			else if (uInput.equals(commands.get("temp1"))) {
				System.out.println("T1 reads: " + Double.toString(myLab.T1()));
			}
			else if (uInput.equals(commands.get("temp2"))) {
				System.out.println("T2 reads: " + Double.toString(myLab.T2()));
			}
			else if (uInput.split(" ")[0].equals(commands.get("set1"))) {
				System.out.println("Heater 1 set to " + Double.toString(myLab.Q1(Double.parseDouble(uInput.split(" ")[1]))));
			}
			else if (uInput.split(" ")[0].equals(commands.get("set2"))) {
				System.out.println("Heater 1 set to " + Double.toString(myLab.Q2(Double.parseDouble(uInput.split(" ")[1]))));
			}
			else if (uInput.split(" ")[0].equals(commands.get("LED"))) {
				System.out.println("LED set to " + Double.toString(myLab.LED(Double.parseDouble(uInput.split(" ")[1]))));
			}
			else if (uInput.equals(commands.get("clear"))) {
				System.out.println("this command clearly doesn't work");
			}
			else if (uInput.equals(commands.get("close"))) {
				System.out.println("Closing lab");
				myLab.close();
				break;
			} 
			else {
				System.out.println("The command you entered was not a valid command."); 
				System.out.println("Type 'help' for a list of valid commands.");
			}
		}
	}

}