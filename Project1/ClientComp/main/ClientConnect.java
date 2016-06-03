/* ClientConnect.java
 * LAST REVISION: 6/3/2016
 */

package main;

public interface ClientConnect {
	public void startClient();
	public void setHostName(String hostName);
	public void setPortNumber(int portNumber);
	public void setLogChoice(String logChoice);
	public void setLogMethod(String logMethod);
}