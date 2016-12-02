//code borrowed from http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
//edited by CheckerMate

import java.net.*;
import java.io.*;

public class Server {
	
	private static int portNumber = 13337;
	private static boolean connectionEstablished = false;
	
	//check for connection with this method
	public boolean isConnected(){
		return connectionEstablished;
	}
	
	//tries to run the server connection
    public void run(){
    	System.out.println("Server hosting connection on port " + portNumber + "...");
    	boolean connected = false;
        while(portNumber < 65000 && !connected){
        	try (
                    ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();    
                    PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);                   
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String inputLine;
                    System.out.println("Connected on port " + portNumber + ".");
                    connected = true;
                    connectionEstablished = true;
                    while ((inputLine = in.readLine()) != null) {
                        out.println("Hoi ik ben de server, je zei: " + inputLine);
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                    portNumber++;
                    connected = false;
                }
        }
    }
}