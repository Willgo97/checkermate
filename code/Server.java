//code borrowed from http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
//edited by CheckerMate

import java.net.*;
import java.io.*;

public class Server {
	
	private static int portNumber = 13337;
	private static boolean connectionEstablished = false;
	
	//check for connection with this method
	public boolean hasConnection(){
		return connectionEstablished;
	}
	
	//tries to run the server connection
    public void run(){
    	System.out.println("Server hosting connection on port " + portNumber + "...");
        while(!hasConnection()){
        	try (
                    ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();   
        			
                    PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);                   
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                ) {
        			connectionEstablished = true;
              
                    
                    System.out.println("Connected to client on port " + portNumber + ".");
                 
                   //System.out.println("Dambord is ready: " + Dambord.bord.klaarVoorVerzending());
                    while (true) { 
                    	System.out.println("test");
						if(Dambord.bord.klaarVoorVerzending()){
    	            		out.println(Dambord.bord.getLaatsteZet());
    	            		Dambord.bord.verzonden();
    	            	}
						else if(in.ready()){
							processTurn(in.readLine());
							Dambord.bord.verzonden();
						}
						else{
							Thread.sleep(1000);
							continue;
						}
                    }
                    
                } catch (IOException | InterruptedException e) {
                    System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
        }
    }
    
    public void processTurn(String input){
    	if(input.contains("schuif")){
    		if(input.contains("linksboven")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.schuif("linksboven");
    		}
    		if(input.contains("rechtsboven")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.schuif("rechtsboven");
    		}
    		if(input.contains("linksonder")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.schuif("linksonder");
    		}
    		if(input.contains("rechtsonder")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.schuif("rechtsonder");
    		}
    	}
    	else if(input.contains("sla")){
    		if(input.contains("linksboven")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.sla("linksboven");
    		}
    		if(input.contains("rechtsboven")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.sla("rechtsboven");
    		}
    		if(input.contains("linksonder")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.sla("linksonder");
    		}
    		if(input.contains("rechtsonder")){
    			Dambord.bord.setGeselecteerd(Character.getNumericValue(input.charAt(0)), Character.getNumericValue(input.charAt(1)));
    			Dambord.bord.sla("rechtsonder");
    		}
    	}
    	else{
    		System.out.println("No valid String was sent: " + input);
    	}
    }
}