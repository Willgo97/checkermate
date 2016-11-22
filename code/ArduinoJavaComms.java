import purejavacomm.CommPortIdentifier;
import purejavacomm.SerialPort;
import purejavacomm.SerialPortEvent;
import purejavacomm.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;


public class ArduinoJavaComms implements SerialPortEventListener {
    SerialPort port = null;

    private BufferedReader input = null;
    private static OutputStream output;

    private static final int TIME_OUT = 1000; // Port open timeout
    private static final String PORT_NAMES[] = {  // PORTS
            "COM10","COM9","COM8","COM7", "COM6", "COM5", "COM4", "COM3", "COM2", "COM1" // Windows only
    };

    //main om te testen.
    public static void main(String[] args) {
        ArduinoJavaComms arduino = new ArduinoJavaComms();
        arduino.initialize();
    }

    //begint de communicatie
    public void initialize() {
        try {
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
            while ( true) {
                try { Thread.sleep(100); } catch (Exception ex) { }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //handelt output van de arduino af die in de inputStream van Java staat.
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

    //methode om een reeks codes naar de Arduino te sturen zodat de robotarm kan slaan.
    public void robotSlaat(int oldX, int oldY, int newX, int newY, int geslagenX, int geslagenY){
    	System.out.println("robot slaat.");
    	send(oldX, oldY);
    	send(newX, newY);
    	send(geslagenX, geslagenY);
    	send(10,10);
    }

    //methode om een reeks codes naar de Arduino te sturen zodat de robotarm kan schuiven.
    public void robotSchuift(int oldX, int oldY, int newX, int newY){
    	System.out.println("robot schuift.");
    	send(oldX,oldY);
    	send(newX,newY);
    }
    
    public int naarCoördinaten(int dambordpositie){
    	//binnen het bord: 0 t/m 9
    	if(dambordpositie < 10)
    		return dambordpositie * 40 + 20;
    	//buiten het bord: 10
    	else
    		return 450;
    }
    
    public void send(int x, int y){
    	try{
    		String coords = "c " + naarCoördinaten(x) + " " + naarCoördinaten(y) + " ";
        	output.write(coords.getBytes());
        	output.flush();
    	}
    	catch(IOException|NullPointerException e){
            System.out.println("Er ging iets fout met het sturen van coördinaten " + e);
        }
    }
}
