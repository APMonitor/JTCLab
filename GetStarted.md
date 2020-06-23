
# Getting Started with JTCLab

If you haven't yet imported the ```JTCLab``` library into your project, see the [README.md](https://github.com/CrayonProof/JTCLab/blob/master/README.md) file for instructions.

With the library included in your project, you can now use the built in methods to control the Temperature Control Lab. To use these methods, create a JTCLab object. You can name it anything you want. In this case, I will name it 'myLab'.

```JTCLab myLab = new JTCLab();```

The following are examples of the standard methods you can call from your JTCLab object:

-  ```myLab.LED();``` turns the LED on at 100% power.
-  ```myLab.LED(50);```  turns the LED on at 50% power.
- ```myLab.T1();``` returns the temperature reading of thermometer 1.
- ```myLab.T2();``` returns the temperature reading of thermometer 2.
- ```myLab.Q1(100);``` sets heater 1 to 100% power.
- ```myLab.Q2(50);``` sets heater 2 to 50% power.

The following code example will print out the initial temperatures of the two thermometers. Then it will turn on heater 1 to 80% power and heater 2 to 50% power. After 30 seconds, it will print out the temperatures again and disconnect.

```
import edu.jtclab.*;

public class Main {

	public static void main(String[] args) {
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

```