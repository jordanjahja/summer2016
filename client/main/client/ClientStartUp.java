/* ClientStartUp.java
 * LAST REVISION: 6/2/2016
 */

package client;

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
	
	public static void main(String[] args) {
		
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String logChoice = args[2];
		String logMethod = args[3];
		String inputFromClient;
		String responseFromServer;
		String[] logArray = new String[3];	//Array that contains all logging information
		
		// Look for 4 command line arguments: host, port #, logging option, logging method
		if (args.length != 4) {
			System.out.println("Error. Command line arguments needed: Host, Port number, Logging (yes or no), Logging method (command line[cmd] or text file[txt])");
		}
		
		/*
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
			//logArray[0] = "Connection to host established";
			//DataOutputStream dosServer = new DataOutputStream(s.getOutputStream());	//Output to server
			DataInputStream disServer = new DataInputStream(s.getInputStream());	//Input from server
			DataInputStream disClient = new DataInputStream(System.in);	//Input from Client
		
			responseFromServer = (String)disServer.readUTF();
			inputFromClient = (String)disClient.readUTF();
			
			while(responseFromServer != null) {
				System.out.print("Server: " + responseFromServer);
				//logArray[1] = "String recieved from server: " + responseFromServer;
				
				if(responseFromServer.equalsIgnoreCase("end")) {
					//logArray[3] = "Server ended connection.";
					break;
				}
				
				if(inputFromClient != null) {
					System.out.println("Client: " + inputFromClient);
					//logArray[2] = "String recieved from client: " + inputFromClient;
				}
			}
		} catch(UnknownHostException uhe) {
			System.out.println("Error Connecting to " + hostName);
		}
		
	}

}