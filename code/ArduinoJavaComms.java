import purejavacomm.CommPortIdentifier;
import purejavacomm.SerialPort;
import purejavacomm.SerialPortEvent;
import purejavacomm.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

//Class that makes communication channels with the Arduino.

public class ArduinoJavaComms implements SerialPortEventListener {
	public static ArduinoJavaComms arduino = new ArduinoJavaComms();
    SerialPort port = null;

    private BufferedReader input = null;
    private static OutputStream output;

    private static final int TIME_OUT = 1000; // Port open timeout
    private static final String PORT_NAMES[] = {  // PORTS
            "COM10","COM9","COM8","COM7", "COM6", "COM5", "COM4", "COM3", "COM2", "COM1" // Windows only
    };
    
    public static int[][] fysiekDambord = new int[10][10];
    
    public static void main(String[] args){
    	System.out.println("making object.");
    	ArduinoJavaComms arduino = new ArduinoJavaComms();
    	
    	System.out.println("initialising");
    	arduino.initialize();
    	arduino.robotSchuift(6, 1, 7, 2);
    	System.out.println("sending r");
    	arduino.send("d");
    	System.out.println("hiu");
    }

    //Starts the communication.
    public void initialize() {
        try {
        	System.out.println("Trying to connect to Arduino...");
            CommPortIdentifier portid = null;
            Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();

            while (portid == null && portEnum.hasMoreElements()) {
                portid = (CommPortIdentifier)portEnum.nextElement();
                if ( portid == null )
                    continue;

                System.out.println("Trying: " + portid.getName());
                for ( String portName: PORT_NAMES ) {
                    if ( portid.getName().equals(portName)
                            || portid.getName().contains(portName)) {  // CONTAINS
                        port = (SerialPort) portid.open("ArduinoJavaComms", TIME_OUT);
                        port.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN+SerialPort.FLOWCONTROL_XONXOFF_OUT); // FLOW-CONTROL
                        
                        port.setSerialPortParams(57600, 8, 1, 0);
                        //open streams
                        input = new BufferedReader(
                                new InputStreamReader( port.getInputStream() ));
                        output = port.getOutputStream();

                        System.out.println( "Connected on port: " + portid.getName() );
                        
                        port.addEventListener(this);
                        port.notifyOnDataAvailable(true);
                    }
                }
            }
            /*while ( true) {
                try { Thread.sleep(100); } catch (Exception ex) { }
            }*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Handles the Arduino output that comes in Java's InputStream
    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            switch (event.getEventType() ) {
                case SerialPortEvent.DATA_AVAILABLE:
                    String inputLine = input.readLine();
                    if(inputLine.length()==1){
                        System.out.println(inputLine);
                    }
                    else if(inputLine.length() > 1){
                        System.out.println(inputLine);
                        if(inputLine.startsWith("f")){
                        	System.out.println("getting dambord layout");
                        	setFysiekDambord(inputLine);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to send codes to the Arduino so that the robot can capture a piece.
    public void robotSlaat(int oldX, int oldY, int newX, int newY, int geslagenX, int geslagenY){
    	System.out.println("Trying to capture a piece with the robot...");
    	send(oldX, oldY);
    	send("d");
    	send("a");
    	send("p");
    	send(newX, newY);
    	send("d");
    	send("u");
    	send("p");
    	send(geslagenX, geslagenY);
    	send("d");
    	send("a");
    	send("p");
    	send("p");
    	send(10,10);
    	send("d");
    	send("u");
    	send("p");
    	//send("r");
    }

    //Method to send codes to the Arduino so that the robot can capture a piece.
    public void robotSchuift(int oldX, int oldY, int newX, int newY){
    	System.out.println("Trying to move a piece with the robot...");
    	send(oldX,oldY);
    	send("d");
    	send("a");
    	send("p");
    	send(newX,newY);
    	send("d");
    	send("u");
    	send("p");
    	send("r");    	
    }
    
    public int naarCoördinaten(int dambordpositie, String axis){
    	//Within range of the board: 0 t/m 9.
    	
    	if(dambordpositie < 10){
    		if (axis == "x"){
    			dambordpositie = dambordpositie * 40 + 40;
    		}
    		else if (axis == "y"){
    			dambordpositie = dambordpositie * 40 ;//The y-axis has a blank spot to put the dead stones 0_o
    		}
    		return (dambordpositie);
    	}
    	//Outside range of the board: 10
    	else{
    		return (0);
    	}
    }
    
    //Helper method to send coordinates to the Arduino.
    public void send(int x, int y){
    	try{
    		String coords = "c" + naarCoördinaten(x, "x") + " " + naarCoördinaten(y, "y");
        	output.write(coords.getBytes());
        	output.flush();
        	System.out.println("Gelukt!");
    	}
    	catch(IOException|NullPointerException e){
            System.out.println("Er ging iets fout met het sturen van coördinaten, namelijk " + e);
        }
    }
    //Helper method to send magnet control to Arduino.
    public void send(String s){
    	try{
    		String out = s;
    		output.write(out.getBytes());
            output.flush();
            System.out.println("Gelukt!");	
    	}
    	catch(IOException | NullPointerException e){
    		System.out.println("Er ging iets fout met het sturen van de opdracht, namelijk " + e);
    	}
    }
    
    public void printBoard(){
    	try{
        	output.write('b');
        	//output.flush();
        	System.out.println("Gelukt!");
    	}
    	catch(IOException|NullPointerException e){
            System.out.println("Er ging iets fout met het sturen van een printverzoek namelijk: " + e);
        }
    }

    //Returns data retrieved from physical board.
	public int[][] getFysiekDambord() {
		/*System.out.println("Print de return shit");
		for(int i = 0; i <= 9; i++){
			for(int j = 0; j <= 9; j++){
				System.out.print(fysiekDambord[i][j] + " ");
			}
			System.out.println();
		}*/
		return fysiekDambord;
	}

	//Sets physical board data from a given String.
	//Inputstring format: "f <numbers 0 and 1, after every 10 digits follows a comma>" 

	public void setFysiekDambord(String rauweInput) {
		int[][] updatedBord = new int[10][10];
		int x = 0;
		int y = 0;
		for(int i = 0; i < rauweInput.length(); i++){
			if(rauweInput.charAt(i) == '0' || rauweInput.charAt(i) == '1'){
				updatedBord[y][x] = Character.getNumericValue(rauweInput.charAt(i));
				x = (x + 1) % 10;
			}
			if(rauweInput.charAt(i) == ','){
				y++;	
			}
			else{
				continue;
			}
		}
		this.fysiekDambord = updatedBord;	
	}
}
