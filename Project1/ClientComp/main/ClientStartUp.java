/* ClientStartUp.java
 * LAST REVISION: 6/3/2016
 */

package main;

import java.io.*;
import java.net.*;
/*
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
*/

public class ClientStartUp{
	
	//Variable declarations
	private static boolean debug = true;
	
	private static String hostName;		//IP address of Host
	private static int portNumber;		//Port number to connect to
	private static String logChoice;	//Logging? (yes or no)
	private static String logMethod;		//Log method (Text file or Command line)
	private static String inputFromClient;		//String input from client
	private static String responseFromServer;	//String output from server
	private String[] logArray = new String[3];	//Array that contains all logging information
	private static DataInputStream disClient;	//Input from Client
	private static DataInputStream disServer;	//Input from Server
	
	public static void main(String[] args) throws IOException {
		
		if(debug) System.out.println("Debug: ClientStartUp started.");
		
		hostName = args[0];
		portNumber = Integer.parseInt(args[1]);
		logChoice = args[2];
		logMethod = args[3];
		
		// Look for 4 command line arguments: host, port #, logging option, logging method
		if (args.length != 4) {
			System.out.println("Error. Command line arguments needed: Host, Port number, Logging (yes or no), Logging method (command line[cmd] or text file[txt])");
		}
		
		/* LOGGING CODE
		if(logChoice.equalsIgnoreCase("yes")) {
			if(logMethod.equalsIgnoreCase("txt")) {
				//Text file logging
				File file = new File("ClientLog.txt");
				FileOutputStream fos = new FileOutputStream(file);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			} else {
				//Command line logging
				//Code for cmd line logging
			}
		}
		*/
		
		try {
			//Establish connection with server and open up communication
			Socket s = new Socket(hostName, portNumber);
			if(debug) System.out.println("Debug: established connection with server at: " + hostName + portNumber);
			//logArray[0] = "Connection to host established";
					
			//DataOutputStream dosServer = new DataOutputStream(s.getOutputStream());	//Output to server
			disServer = new DataInputStream(s.getInputStream());
			disClient = new DataInputStream(System.in);
		
			responseFromServer = disServer.readUTF();
			inputFromClient = disClient.readUTF();
			
			while(responseFromServer != null) {
				System.out.print("Server: " + responseFromServer);
				//logArray[1] = "String recieved from server: " + responseFromServer;
				
				if(responseFromServer.equalsIgnoreCase("end")) {
					if(debug) System.out.println("Debug: server ended connection");
					//logArray[3] = "Server ended connection.";
					break;
				}
				
				if(inputFromClient != null) {
					System.out.println("Client: " + inputFromClient);
					//logArray[2] = "String recieved from client: " + inputFromClient;
				}
			}
		} catch(UnknownHostException uhe) {
			System.out.println("Error connecting to " + hostName);
			System.exit(1);
		}
		
	}

}