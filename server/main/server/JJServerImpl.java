/*
 * Last Edit: June 2, 2016
 */
package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * My first server implementation
 * @author jordan jahja
 *
 */
public class JJServerImpl implements SuperAwesomeServer{
	
	private static SuperAwesomeServer server = new JJServerImpl();
	
	private boolean debugging = false;
	private int PORT_NUMBER;
	private PrintStream out = System.out;
	private int threadCount;
	
	public JJServerImpl(){
		//do nothing
	}
	
	public void startServer(){		
		threadCount = 0;
		//establish server ports and connections
		try {
			@SuppressWarnings("resource")
			ServerSocket ssock = new ServerSocket(PORT_NUMBER);
			if (debugging) out.println("created ServerSocket...waiting for first connection");
			
			//continues looping for infinite connections to server
			while (true){
				//accept a connection
				Socket sock = ssock.accept();
				if (debugging) out.println("accepted incoming connection: " + sock.getPort());
				
				//initialize thread
				Thread newThread = new Thread(new JJThread(Integer.toString(threadCount), sock, out, debugging));
				if (debugging) out.println("created thread " + threadCount + " succesfully");
				
				//start thread
				newThread.start();
				if (debugging) out.println("started thread " + threadCount);
				
				threadCount++;
			}
		} catch (IOException e) {
			out.println("Failed to establish socket.");
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
	
	public PrintStream getOutput(){
		return out;
	}
	
	public void setOutputFile(String fileName){
		if (fileName.equals("none")){
			//do nothing
		}
		else{
			try {
				out = new PrintStream(fileName);
			} catch (FileNotFoundException e) {
				out.println("Cannot create log file with the name: " + fileName);
			}
		}
	}
}
