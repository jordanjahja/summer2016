/*
 * Last Edit: June 2, 2016
 */
package server;


/**
 * this is the SERVER service.
 * @author jordan jahja
 *
 */
public interface SuperAwesomeServer {	
	/**Starts the server. Call to setPortNumber must be done first*/
	public void startServer();
	
	/**Set the port number of the server*/
	public void setPortNumber(int portNumber);
	
	/**set debugging if enabled*/
	void setDebug();
	public boolean debugEnabled();
	
}
