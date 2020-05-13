# JTCLab

JTCLab is a Java package for controlling the [Temperature Control Lab](http://apmonitor.com/pdc/index.php/Main/ArduinoTemperatureControl).

It performs the same functions as the original [Python package](https://github.com/jckantor/TCLab/blob/master/tclab/tclab.py).

## Usage

To get started with ```JTCLab```, you will need to include two Java libraries in your project. **First**:

- Download the ```JTCLab```  library <a href="https://github.com/CrayonProof/JTCLab/releases/download/1.0/JTCLab.jar" download>JAR file</a>.
- Download the ```jSerialComm```  library <a href="https://fazecast.github.io/jSerialComm/binaries/jSerialComm-2.6.2.jar" download>JAR file</a>.
>JTCLab is the library that allows you to interface with the Temperature Control lab Arduino. jSerialComm is a library that allows Java to send and receive commands over your USB port.

**Next** you will need to include the two files you just downloaded in your project's build path. If you are using Eclipse, right click on your project and go to Build 'Path>Configure Build Path'.

<img src="https://i.imgur.com/oflDuMM.png" alt="configureBuildPathImage" width="350"/>

When the Java Build Path window opens, click 'Modulepath' and then 'Add External Jars'.

<img src="https://i.imgur.com/3xuORlx.png" alt="javaBuildPathImage" width="350"/>

Brows to the location in which you downloaded the above JAR files and add both files to the build path. It should look like the below image when you finish. Select 'Apply and Close'.

<img src="https://i.imgur.com/adtTih6.png" alt="javaBuildPathImage" width="350"/>


Once the two libraries are included in your project, you can use JTCLab in any class of your project by importing it with ```import edu.jtclab.*;```.

## Getting Started

With the library included in your project, you can now use the built in methods to control the Temperature Control Lab. You can use the following code to test the lab by turning on it's LED for one second.

```
import edu.jtclab.*;

public class Main {

	public static void main(String[] args) {
		//Connect to the Arduino
		JTCLab myLab = new JTCLab();
		
		//Turn on the LED
		myLab.LED();
		
		//Wait one second
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		//End the program and disconnect the Arduino
		myLab.close();
		
	}
}
```

For more information on getting started with ```JTCLab```, see the [GetStarted.md](https://github.com/CrayonProof/JTCLab/blob/master/GetStarted.md) file.