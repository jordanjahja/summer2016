/*
 * Last Edit: June 2, 2016
 */
package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import logger.JLog;
import logger.JLogImpl;

public class JJThread implements Runnable{
	
	private String threadID;
	private Socket sock;
	private boolean debugging;
	private DataInputStream input;
	private DataOutputStream output;
	private JLog log;
	
	
	public JJThread(String threadID, Socket sock, boolean debugging){
		this.sock = sock;
		this.log = JLogImpl.getSLog();
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
			if (debugging) log.sLogDEBUGMessage("thread " + threadID + " initialized input and output streams.");
			
			//read user input
			String word = input.readUTF();
			if (debugging) log.sLogDEBUGMessage("thread " + threadID + " got message from client");
			
			output.writeUTF(word.toUpperCase());
			if (debugging) log.sLogDEBUGMessage("thread " + threadID + " sent message to client");
		
			//close socket
			sock.close();
			if (debugging) log.sLogDEBUGMessage("thread " + threadID + "finishes");
		} catch (IOException e) {
			log.sLogERRORMessage("Something went wrong with thread " + threadID);
		}
	}
	

}
