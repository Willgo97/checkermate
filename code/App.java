import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import static java.nio.file.StandardOpenOption.*;

import java.nio.file.*;
import java.io.*;

public class App {

	//private static ArduinoJavaComms arduino = new ArduinoJavaComms();
	private static Server server = new Server();
	private static Client client = new Client();
	private static Thread arduinoThread;
	private static Thread serverThread;
	private static Thread clientThread;
	
	//Starts the main application.
	public static void main(String[] args) {
		startProgram();
	}
	
	public static JDialog showMessage(String message){
		final JOptionPane optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

		final JDialog dialog = new JDialog();
		dialog.setTitle("CheckerMate");
		dialog.setModal(false);

		dialog.setContentPane(optionPane);

		
		
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static void startProgram(){

		//Creates threads for the Arduino, Server and Client
		arduinoThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        ArduinoJavaComms.arduino.initialize();
		    }
		});
		
		serverThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        server.run();
		    }
		});
		clientThread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        try {
					client.run();
				} catch (IOException e) {
				}
		    }
		});
		
		//Maakt of edit een save file.
		Path p = Paths.get("./names.txt");
		String name = null;
		while(name == null){
		    try (OutputStream out = new BufferedOutputStream(
		    	Files.newOutputStream(p, CREATE, APPEND))) {
		    	name = (String) JOptionPane.showInputDialog(
			    		null,
			    		"Wat is uw naam?", 
			    		"CheckerMate",
			    		JOptionPane.QUESTION_MESSAGE,
			    		new ImageIcon(GUI.class.getResource("/white king.png")),
			    		null,
			    		null);
			    byte data[] = name.getBytes();
		    	out.write(data, 0, data.length);
		    } catch (IOException | NullPointerException x) {
		      		//Try again
		    }
		}
		
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
				JDialog message = showMessage("Wachten tot iemand de uitnodiging accepteert...");
				while(!connected){
					connected = server.hasConnection();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(connected){
					message.setVisible(false);
					message.dispose();
				}
			}
	    	if(response == 2){
	    		String serverAddress = JOptionPane.showInputDialog(
	    	            "Voer IP-adres in van de host op poort 13337");
	    		client.setIP(serverAddress);
	    		clientThread.start();
	    		JDialog message = showMessage("Bezig met zoeken naar een verbinding...");
				while(!connected){
					connected = client.hasConnection();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(connected){
					message.setVisible(false);
					message.dispose();
				}
	    	}
	    	if(response == 0){
	    		Dambord.bord.setAgainstAi(true);
	    	}
	    	if(response == 0 || connected){
	    		if(server.getOpponentColor() == 0 && client.getOpponentColor() == 0){
	    			String[] options2 = new String[] {"Wit", "Zwart"};
		    	    int decision = JOptionPane.showOptionDialog(
		    	    		null, 
		    	    		"Welke kleur stenen wilt u gebruiken?", 
		    	    		"CheckerMate",
		    	    		JOptionPane.DEFAULT_OPTION, 
		    	    		JOptionPane.QUESTION_MESSAGE,
		    	    		null,
		    	    		options2,
		    	    		options2[0]);
		    	    if(decision == 0 && server.getOpponentColor() == 0 && client.getOpponentColor() == 0){
		    	    	Dambord.bord.setSpelerKleur(2); // number 2 is color white
		    	    	if(response == 1 && connected){
		    	    		server.sendChosenColor(2);
		    	    	}
		    	    	if(response == 2 && connected){
		    	    		client.sendChosenColor(2);
		    	    	}
		    	    }
		    	    if(decision == 1 && server.getOpponentColor() == 0 && client.getOpponentColor() == 0){
		    	    	Dambord.bord.setSpelerKleur(1); // number 1 is color black
		    	    	if(response == 1 && connected){
		    	    		server.sendChosenColor(1);
		    	    	}
		    	    	if(response == 2 && connected){
		    	    		client.sendChosenColor(1);
		    	    	}
		    	    }
		    	    if(decision == 2){
		    	    	System.exit(0);
		    	    }
		    	    if(server.getOpponentColor() != 0 || client.getOpponentColor() != 0){
		    	    	JOptionPane.showMessageDialog(null,
		    	    		    "Uw tegenstander heeft al een kleur gekozen.\nUw spel zal nu beginnen.",
		    	    		    "U was net te laat...",
		    	    		    JOptionPane.ERROR_MESSAGE);
		    	    }
		    	    if(response == 0 && decision == 0){
		    	    	Dambord.bord.setSpelerKleur(2);
		    	    }
		    	    if(response == 0 && decision == 1){
		    	    	Dambord.bord.setSpelerKleur(1);
		    	    }
		    	    
	    		}
	    		
	    		//starts GUI and Arduino threads
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUI.gui.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				arduinoThread.start();
				
				//Checks if the game is finished.
				while(true){
					if(GUI.gui.gameFinished()){
						
						Object[] options3 = {"Ja",
						                    "Nee"};
						int response3 = JOptionPane.showOptionDialog(null,
						    "Het spel is afgelopen, wilt u nog een keer spelen?",
						    "CheckerMate",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options3,
						    options3[1]);
						if(response3 == 0){
							//resets the GUI, board, and all Threads.
							GUI.gui.setVisible(false);
							GUI.gui.dispose();
							Dambord.bord = new Dambord();
							GUI.gui = new GUI();
							startProgram();
						}
						if(response3 == 1){
							System.exit(0);
						}
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
	    	}
		}
	
	}
}
