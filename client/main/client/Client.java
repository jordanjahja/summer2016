/* Client.java
 * LAST REVISION: 6/3/2016
 */

package client;

import java.io.*;
import java.net.*;

public class Client implements ClientConnect {

	//Variable declarations
	private boolean debug = true;
	
	private String hostName;
	private int portNumber;
	private String logChoice;
	private String logMethod;
	private String[] logElements;	//Array that has all the logged information to be written
	
	private static ClientConnect client = new Client();
	
	@Override
	public void startClient() {
		
		// LOGGING CODE
		if(logChoice.equalsIgnoreCase("yes")) {
			if(logMethod.equalsIgnoreCase("txt")) {
				//Text file logging
				File file = new File("ClientLog.txt");
				FileOutputStream fos = new FileOutputStream(file);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				if(debug) System.out.println("DEBUG: Sucessfully created log output text file.");
				logElements[0] = "Client.java: Sucessfully created log output text file";
			} else {
				//Command line logging
				if(debug) System.out.println("DEBUG: Command line logging chosen.");
			}
		}
		
		try {
			//Establish connection with server and open up communication
			Socket s = new Socket(hostName, portNumber);
			if(debug) System.out.println("DEBUG: established connection with server at: " + hostName + portNumber);
			logElements[1] = "Client.java: Sucessfully established connection with server at: " + hostName + portNumber;
			
			Thread newClientThread = new Thread(new ClientThread(hostName, portNumber, s));
			if(debug) System.out.println("DEBUG: Created a new client thread."); 
			logElements[2] = "Client.java: Sucessfully created a new client thread";
		} catch(UnknownHostException uhe) {
			System.out.println("Error connecting to " + hostName);
			System.exit(1);
		}
	}

	@Override
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	@Override
	public void setLogChoice(String logChoice) {
		this.logChoice = logChoice;
	}

	@Override
	public void setLogMethod(String logMethod) {
		this.logMethod = logMethod;
	}
}