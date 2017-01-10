import java.io.*;
import java.net.*;

public class Client {
	private static int portNumber = 13337;
	private static String hostName = "localhost";
	private static boolean connectionEstablished = false;
	private int chosenColor = 0;
	private int opponentChoseColor = 0;
	
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
    	        ) {
    	        	connectionEstablished = echoSocket.isConnected();
    	        	System.out.println("Connected to " + hostName + " on port " + echoSocket.getPort() + ".");
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
    	        } catch (UnknownHostException e) {
    	            System.out.println("Don't know about host " + hostName);
    	            System.exit(1);
    	        } catch (IOException e) {
    	            System.out.print(".");
    	        } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    	}  
    }

    // Sets the IP-address as retrieved from user input for the connection to ServerSocket.
	public void setIP(String serverAddress) {
		hostName = serverAddress;	
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
    		System.out.println("Received: " + input);
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