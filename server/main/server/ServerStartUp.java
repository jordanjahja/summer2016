/**
 * Last edit: June 2, 2016
 */
package server;

/**
 * starts up the server
 * @author Jordan Jahja
 */
public class ServerStartUp {
	
	public static void main(String[] args) {
		
		//invalid command-line arguments
		if (args[0] == null || args[1] == null){
			System.out.println("Usage: javak on/off port_number");
			System.exit(1);
		}
		
		System.out.println("Server started.");
		
		//initialize the server, change the implementation if a 
		//better one is created.
		SuperAwesomeServer server = JJServerImpl.getServer();
		
		//turns on debugging
		if (args[0].equals("on")){
			server.setDebug();
		}

		//set port number
		server.setPortNumber(Integer.parseInt(args[1]));
		
		//start the server
		server.startServer();
		
		
		//shut off server
		System.exit(0);
	}

}
