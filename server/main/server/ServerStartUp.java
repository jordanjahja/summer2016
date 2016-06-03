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
			System.out.println("Usage: javak on/off port_number optional:log_file/none");
			System.exit(1);
		}
		
		System.out.println("Server started.");
		
		//initialize the server, change the implementation if a 
		//better one is created.
		SuperAwesomeServer server = JJServerImpl.getServer();
		
		//turns on debugging
		if (args[0].equals("on")){
			server.setDebug();
			System.out.println("start-up arugments: " + args[0] + " " + args[1] + " " + args[2]);
		}

		//set port number
		server.setPortNumber(Integer.parseInt(args[1]));
		
		//specify where the output goes
		server.setOutputFile(args[2]);
		
		//start the server
		server.startServer();
		
		//shut off server
		System.exit(0);
	}

}
