import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class App {

	private static ArduinoJavaComms arduino = new ArduinoJavaComms();
	private static Server server = new Server();
	private static Client client = new Client();
	private static GUI frame = new GUI();

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
	    	if(response == 0 || connected){
	    		//starts GUI and Arduino threads
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				arduinoThread.start();
	    	}
	    	while(connected){
	    		try{
	    			frame.updatePanel();
	    			Thread.sleep(100);
	    		}
	    		catch(InterruptedException e){
	    			e.printStackTrace();
	    		}
	    	}
		}
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
}
