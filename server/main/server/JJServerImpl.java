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
		//change all outputs to log.txt if enabled by user
		if (!debugging){
			try {
				out = new PrintStream("log.txt");
			} catch (FileNotFoundException e) {
				System.out.println("Cannot find or write to log.txt.");
				System.out.println("Writing to standard output for debugging.");
			}
		}
		
		threadCount = 0;
		//establish server ports and connections
		try {
			@SuppressWarnings("resource")
			ServerSocket ssock = new ServerSocket(PORT_NUMBER);
			if (!debugging) out.println("created ServerSocket");
			
			//continues looping for infinite connections to server
			while (true){
				Socket sock = ssock.accept();
				if (!debugging) out.println("accepted incoming connection: " + sock.getPort());
				
				Thread newThread = new Thread(new JJThread(Integer.toString(threadCount), sock, out, debugging));
				if (!debugging) out.println("created thread " + threadCount + " succesfully");
				
				threadCount++;
				
				newThread.start();
				if (!debugging) out.println("started thread " + threadCount);
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
}
