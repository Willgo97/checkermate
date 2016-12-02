import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class App {

	private static ArduinoJavaComms arduino = new ArduinoJavaComms();
	private static Server server = new Server();
	private static Client client = new Client();

	//Starts the main application.
	public static void main(String[] args) {
		//Creates threads for the Arduino, Server and Client
		Thread arduinoThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        arduino.initialize();
		    }
		});
		
		Thread serverThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        server.run();
		    }
		});
		Thread clientThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        try {
					client.run();
				} catch (IOException e) {
				}
		    }
		});
		
		//Creates a dialog to choose the game mode (1P/host/client).
		String[] options = new String[] {"Tegen de computer", "Iemand uitnodigen", "Uitnodiging accepteren", "Afsluiten"};
	    int response = JOptionPane.showOptionDialog(
	    		null, 
	    		"Welkom bij dammen met CheckerMate!\nWilt u tegen de computer spelen, iemand uitnodigen voor een spel of een uitnodiging accepteren?", 
	    		"CheckerMate",
	    		JOptionPane.DEFAULT_OPTION, 
	    		JOptionPane.QUESTION_MESSAGE,
	    		new ImageIcon(GUI.class.getResource("/white king.png")),
	    		options,
	    		options[0]);
	    
	    if(response == 3 || response == -1){
	    	System.exit(0);
	    }
	    else{
	    	boolean connected = false;
	    	if(response == 1){
				serverThread.start();
				while(!connected){
					connected = server.isConnected();
				}
			}
	    	if(response == 2){
	    		clientThread.start();
				while(!connected){
					connected = client.isConnected();
				}
	    	}
	    	if(response == 0 || connected){
	    		//starts GUI and Arduino threads
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUI frame = new GUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				arduinoThread.start();
	    	}
		}
	}
}
