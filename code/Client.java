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
    	        ) {
    	        	connectionEstablished = echoSocket.isConnected();
    	        	System.out.println("Connected to " + hostName + " on port " + echoSocket.getPort() + ".");
    	           String inputLine;
    	            while (true) {
    	            	System.out.println("test");
    	            	if(Dambord.bord.klaarVoorVerzending()){
    	            		out.println(Dambord.bord.getLaatsteZet());
    	            		Dambord.bord.verzonden();
    	            	}
    	            	else if(in.ready()){
    	            		System.out.println("Input received:");
    	            		inputLine = in.readLine();
    	            		System.out.println(inputLine);
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

	public void setIP(String serverAddress) {
		hostName = serverAddress;	
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