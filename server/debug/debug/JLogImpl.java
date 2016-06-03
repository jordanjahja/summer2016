/**
 * Last edit: June 3, 2016
 */
package debug;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * My first logging implementation
 * @author jordan jahja
 *
 */
public class JLogImpl implements JLog{
	
	private static JLog log = new JLogImpl();
	
	private PrintStream file;
	
	public JLogImpl(){
		//do nothing
	}

	@Override
	public void setLogFile(String fileName) {
		if (fileName.equals("none")){
			file = System.out;
		} 
		else {
			try {
				file = new PrintStream(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("cannot create log file with name " + fileName);
			}
		}
		
	}

	@Override
	public synchronized void logDEBUGMessage(String message) {
		file.println("Debug: " + message);
	}
	
	@Override
	public synchronized void logWARNMessage(String message) {
		file.println("WARN: " + message);
	}
	
	@Override
	public synchronized void logERRORMessage(String message) {
		file.println("ERROR: " + message);
	}
	
	public static JLog getLog(){
		return log;
	}

}
