/*
 * Last Edit: June 3, 2016
 */
package logger;


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
	public void setSLogFile(String fileName);
	
	
	/**
	 * Debug Message to put into log file
	 * @param message
	 */
	public void sLogDEBUGMessage(String message);
	
	/**
	 * WARN Message to put into log file
	 * @param message
	 */
	public void sLogWARNMessage(String message);
	
	/**
	 * ERROR Message to put into log file
	 * @param message
	 */
	public void sLogERRORMessage(String message);
	
	/**
	 * Specifies the file name to write the message
	 * @param fileName the name of the file
	 * specify "none" if want to standard output instead
	 * !!!DO NOT CALL!!
	 */
	public void setCLogFile(String fileName);
	
	
	/**
	 * Debug Message to put into log file
	 * @param message
	 */
	public void cLogDEBUGMessage(String message);
	
	/**
	 * WARN Message to put into log file
	 * @param message
	 */
	public void cLogWARNMessage(String message);
	
	/**
	 * ERROR Message to put into log file
	 * @param message
	 */
	public void cLogERRORMessage(String message);
}
