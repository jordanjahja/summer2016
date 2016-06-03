/* ClientStartUp.java
 * LAST REVISION: 6/3/2016
 */

package client;

public class ClientStartUp {
	
	public static void main(String[] args) {
		// Look for 4 command line arguments: host, port #, logging option, logging method
		if (args.length != 4) {
			System.out.println("Usage: <host name> <port number> <logging> <logging method>");
			System.exit(1);
			}
		
		ClientConnect client;
		
		client.setHostName(args[0]);
		client.setPortNumber(Integer.parseInt(args[1]));
		client.setLogChoice(args[2]);
		client.setLogMethod(args[3]);
	}
}