/**
 * Last edit: June 3, 2016
 */
package logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * My first logging implementation
 * @author jordan jahja
 *
 */
public class JLogImpl implements JLog{
	
	private static JLog sLog = new JLogImpl();
	private static JLog cLog = new JLogImpl();
	
	private PrintStream sFile;
	private PrintStream cFile;
	
	public JLogImpl(){
		//do nothing
	}

	@Override
	public void setSLogFile(String fileName) {
		if (fileName.equals("none")){
			sFile = System.out;
		} 
		else {
			try {
				sFile = new PrintStream(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("cannot create log file with name " + fileName);
			}
		}
	}
	
	@Override
	public void setCLogFile(String fileName) {
		if (fileName.equals("none")){
			cFile = System.out;
		} 
		else {
			try {
				cFile = new PrintStream(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("cannot create log file with name " + fileName);
			}
		}
	}

	@Override
	public synchronized void sLogDEBUGMessage(String message) {
		sFile.println("Debug: " + message);
	}
	
	@Override
	public synchronized void sLogWARNMessage(String message) {
		sFile.println("WARN: " + message);
	}
	
	@Override
	public synchronized void sLogERRORMessage(String message) {
		sFile.println("ERROR: " + message);
	}
	
	public static JLog getSLog(){
		return sLog;
	}
	
	public static JLog getCLog(){
		return cLog;
	}

	@Override
	public synchronized void cLogDEBUGMessage(String message) {
		cFile.println("DEBUG: " + message);
		
	}

	@Override
	public synchronized void cLogWARNMessage(String message) {
		cFile.println("WARN: " + message);
		
	}

	@Override
	public synchronized void cLogERRORMessage(String message) {
		cFile.println("ERROR: " + message);
		
	}

}
