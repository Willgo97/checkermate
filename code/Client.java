import java.io.*;
import java.net.*;

public class Client {
	private static int portNumber = 13337;
	private static String hostName = "localhost";
	private static boolean connectionEstablished = false;
	
	//check for connection with this method
	public boolean hasConnection(){
		return connectionEstablished;
	}
	
	//tries to run the client connection
    public void run() throws IOException {
    	System.out.println("Trying to get I/O for the connection to " + hostName);
    	while(!hasConnection()){
    		try (
    	            Socket echoSocket = new Socket(hostName, portNumber);
    	            PrintWriter out =
    	                new PrintWriter(echoSocket.getOutputStream(), true);
    	            BufferedReader in =
    	                new BufferedReader(
    	                    new InputStreamReader(echoSocket.getInputStream()));
    	            BufferedReader stdIn =
    	                new BufferedReader(
    	                    new InputStreamReader(System.in))
    	        ) {
    	        	connectionEstablished = echoSocket.isConnected();
    	        	System.out.println("Connected to " + hostName + " on port " + echoSocket.getPort() + ".");
    	            String userInput;
    	            while ((userInput = stdIn.readLine()) != null) {
    	                out.println(userInput);
    	                System.out.println("echo: " + in.readLine());
    	            }
    	        } catch (UnknownHostException e) {
    	            System.out.println("Don't know about host " + hostName);
    	            System.exit(1);
    	        } catch (IOException e) {
    	            System.out.print(".");
    	        } 
    	}
        
    }

	public void setIP(String serverAddress) {
		hostName = serverAddress;	
	}
}