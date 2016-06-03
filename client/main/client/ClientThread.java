/* ClientThread.java
 * LAST REVISION: 6/3/2016
 */

package client;

import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {

	private boolean debug = true;
	
	private String hostName;
	private int portNumber;
	private Socket s;
	private DataInputStream disClient;
	private DataInputStream disServer;
	private String responseFromServer;
	private String inputFromClient;
	
	public ClientThread(String hostName, int portNumber, Socket s) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			//DataOutputStream dosServer = new DataOutputStream(s.getOutputStream());	//Output to server
			disClient = new DataInputStream(System.in);
			disServer = new DataInputStream(s.getInputStream());
		
			inputFromClient = disClient.readUTF();
			responseFromServer = disServer.readUTF();
			
			while(responseFromServer != null) {
				System.out.print("Server: " + responseFromServer);
				
				if(responseFromServer.equalsIgnoreCase("end")) {
					if(debug) System.out.println("Debug: server ended connection");
					break;
				}
				
				if(inputFromClient != null) {
					System.out.println("Client: " + inputFromClient);
				}
			}
		} catch(IOException ioe) {
			System.out.println("Error with connection.");
			System.exit(1);
		}
	}
	
}