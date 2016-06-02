/*
 * Last Edit: June 2, 2016
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class JJThread implements Runnable{
	
	private String threadID;
	private Socket sock;
	private PrintStream out;
	private boolean debugging;
	private DataInputStream input;
	private DataOutputStream output;
	
	
	public JJThread(String threadID, Socket sock, PrintStream out, boolean debugging){
		this.sock = sock;
		this.out = out;
		this.debugging = debugging;
		this.threadID = threadID;
	}

	@Override
	public void run() {
		//initialize input, output streams
		try {
			//setup input and output streams
			input = new DataInputStream(sock.getInputStream());
			output = new DataOutputStream(sock.getOutputStream());
			if (!debugging) out.println("thread " + threadID + " initialized input and output streams.");
			
			//read user input
			String word = input.readUTF();
			if (!debugging) out.println("thread " + threadID + " got message from client");
			
			output.writeUTF(word.toUpperCase());
			if (!debugging) out.println("thread " + threadID + " sent message to client");
		
			//close socket
			sock.close();
			if (!debugging) out.println("thread " + threadID + "finishes");
		} catch (IOException e) {
			out.println("Something went wrong with thread " + threadID);
		}
	}
	

}
