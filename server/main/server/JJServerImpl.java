/*
 * Last Edit: June 2, 2016
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import debug.JLog;
import debug.JLogImpl;


/**
 * My first server implementation
 * @author jordan jahja
 *
 */
public class JJServerImpl implements SuperAwesomeServer{
	
	private static SuperAwesomeServer server = new JJServerImpl();
	
	private boolean debugging = false;
	private int PORT_NUMBER;
	private int threadCount;
	
	public JJServerImpl(){
		//do nothing
	}
	
	public void startServer(){
		JLog log = JLogImpl.getLog();
		threadCount = 0;
		//establish server ports and connections
		try {
			@SuppressWarnings("resource")
			ServerSocket ssock = new ServerSocket(PORT_NUMBER);
			if (debugging) log.logDEBUGMessage("created ServerSocket...waiting for first connection");
			
			//continues looping for infinite connections to server
			while (true){
				//accept a connection
				Socket sock = ssock.accept();
				if (debugging) log.logDEBUGMessage("accepted incoming connection: " + sock.getPort());
				
				//initialize thread
				Thread newThread = new Thread(new JJThread(Integer.toString(threadCount), sock, debugging));
				if (debugging) log.logDEBUGMessage("created thread " + threadCount + " succesfully");
				
				//start thread
				newThread.start();
				if (debugging) log.logDEBUGMessage("started thread " + threadCount);
				
				threadCount++;
			}
		} catch (IOException e) {
			log.logERRORMessage("Failed to establish socket.");
		}
	}
	
	public static SuperAwesomeServer getServer(){
		return server;
	}
	
	/**
	 * DO NOT CALL
	 */
	public void setDebug(){
		debugging = true;
	}
	
	public void setPortNumber(int portNumber){
		this.PORT_NUMBER = portNumber;
	}
	
	public boolean debugEnabled(){
		return debugging;
	}
}
