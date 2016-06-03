/*
 * Last Edit: June 3, 2016
 */
package debug;


/**
 * this is the LOGGING service for the server
 * @author jordan jahja
 *
 */
public interface JLog {
	
	/**
	 * Specifies the file name to write the message
	 * @param fileName the name of the file
	 * specify "none" if want to standard output instead
	 * !!!DO NOT CALL!!
	 */
	public void setLogFile(String fileName);
	
	
	/**
	 * Debug Message to put into log file
	 * @param message
	 */
	public void logDEBUGMessage(String message);
	
	/**
	 * WARN Message to put into log file
	 * @param message
	 */
	public void logWARNMessage(String message);
	
	/**
	 * ERROR Message to put into log file
	 * @param message
	 */
	public void logERRORMessage(String message);
}
