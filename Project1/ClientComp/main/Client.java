/* Client.java
 * LAST REVISION: 6/4/2016
 */

package main;

import java.io.*;
import java.net.*;
import logger.JLog;
import logger.JLogImpl;

public class Client implements ClientConnect{

	//Variable declarations
	private boolean debug = false;
	private boolean logText = false;
			
	private String hostName;					//IP address of Host
	private int portNumber;						//Port number to connect to
	private String logChoice;					//Logging? (yes or no)
	private String logMethod;					//Log method (Text file or Command line)
	private String inputFromClient;				//String input from client
	private String responseFromServer;			//String output from server
	private String[] logArray = new String[3];	//Array that contains all logging information
	private DataOutputStream dosClient;			//Input from Client, sent to server
	private DataInputStream disServer;			//Input from Server
	private InputStreamReader clientIn;			//String input from command line (written by client)
	
	public static void main(String[] args) throws IOException {
		// Look for 4 command line arguments: host, port #, logging option, logging method
		if (args.length != 4) {
			System.out.println("Usage: <host name> <port number> <logging> <logging method>");
			System.exit(1);
			}
		
		//code to start client??
		ClientConnect client;
		
		client.setHostName(args[0]);
		client.setPortNumber(Integer.parseInt(args[1]));
		client.setLogChoice(args[2]);
		client.setLogMethod(args[3]);
	}
	
	@Override
	public void startClient() {
		if(logChoice.equalsIgnoreCase("yes")) {
			logText = true;
			Jlog log = JlogImpl.getLog();
			//set log text file name??
		} else {
			debug = true;
		}
			
		try {
			//Establish connection with server and open up communication
			Socket s = new Socket(hostName, portNumber);
			if(debug) System.out.println("DEBUG: established connection with server at: " + hostName + portNumber);
			if(logText) log.cLogDEBUGMessage("DEBUG: established connection with server at: " + hostName + portNumber);
				
			dosClient = new DataOutputStream(s.getOutputStream());
			disServer = new DataInputStream(s.getInputStream());
			clientIn = new InputStreamReader(System.in);
		
			inputFromClient = clientIn.toString();	//get string from command line
			if(debug) System.out.println("DEBUG: Acquired client input from command line.");
			if(logText) log.cLogDEBUGMessage("DEBUG: Acquired client input from command line.");
			System.out.println("Client: " + inputFromClient);
			
			dosClient.writeUTF(inputFromClient);	//send string from command line to server
			if(debug) System.out.println("DEBUG: Sent the string - " + inputFromClient);
			if(logText) log.cLogDEBUGMessage("DEBUG: Sent the string - " + inputFromClient);
			
			responseFromServer = disServer.readUTF();	//get server reply
			if(debug) System.out.println("DEBUG: Got the response from the server.");
			if(logText) log.cLogDEBUGMessage("DEBUG: Got the response from the server.");
			System.out.print("Server: " + responseFromServer);
			
			System.exit(1);
		} catch(IOException ioe) {
			System.out.println("Error with connection.");
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