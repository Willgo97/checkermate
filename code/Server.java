//code borrowed from http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
//edited by CheckerMate

import java.net.*;
import java.io.*;

public class Server {
	
	private static int portNumber = 13337;
	private static boolean connectionEstablished = false;
	private int chosenColor = 0;
	private int opponentChoseColor = 0;
	
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
                 
                    String inputLine;
                    while (true) { 
                    	if(chosenColor != 0){
                    		out.println("color " + chosenColor);
                    		chosenColor = 0;
						}
                    	else if(Dambord.bord.klaarVoorVerzending()){
    	            		out.println(Dambord.bord.getLaatsteZet());
    	            		Dambord.bord.verzonden();
    	            	}
						else if(in.ready()){
							inputLine = in.readLine();
    	            		processTurn(inputLine);
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
    
    // Processes the String that was received in the InputStream
    public void processTurn(String input){
    	if(input.contains("schuif")){
    		temporarilySwitchPlayers();
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
    		temporarilySwitchPlayers();
    		GUI.gui.updatePanel();
    	}
    	else if(input.contains("sla")){
    		temporarilySwitchPlayers();
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
    		temporarilySwitchPlayers();
    		GUI.gui.updatePanel();
    	}
    	else if(input.contains("color")){
    		if(Integer.parseInt(input.replaceAll("[\\D]", "")) == 1){
    			opponentChoseColor = 1;
    			Dambord.bord.setSpelerKleur(2);
    		}
    		else if(Integer.parseInt(input.replaceAll("[\\D]", "")) == 2){
    			opponentChoseColor = 2;
    			Dambord.bord.setSpelerKleur(1);
    		}
    		System.out.println("Received: " + Integer.parseInt(input.replaceAll("[\\D]", "")));
    	}
    	else{
    		System.out.println("No valid String was sent: " + input);
    	}
    }
    
    //Method to switch players temporarily so that the turns can be sync'ed correctly
  	public void temporarilySwitchPlayers(){
  		if(Dambord.bord.getSpelerKleur() == 1){
  			Dambord.bord.setSpelerKleur(2);
  		}
  		else if(Dambord.bord.getSpelerKleur() == 2){
  			Dambord.bord.setSpelerKleur(1);
  		}
  	}

	public void sendChosenColor(int color) {
		chosenColor = color;
	}
	
	public int getOpponentColor(){
		return opponentChoseColor;
	}
}