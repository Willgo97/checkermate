public class Dambord{	
	//################################# FIELDS #####################################
	
	//0 = empty, 1 = black, 2 = white
	private int[][] stenen = 
		   {{0,1,0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,2,0,2,0,2,0,2,0,2},
			{2,0,2,0,2,0,2,0,2,0},
			{0,2,0,2,0,2,0,2,0,2},
			{2,0,2,0,2,0,2,0,2,0}};
	
	private int[][] fysiekStenen = 
		   {{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}};
	

	private int[] geselecteerd = {9,0};
	
	private static final int LEEG = 0;
	private static final int ZWART = 1;
	private static final int WIT = 2;
	private static final int ZWARTEDAM = 3;
	private static final int WITTEDAM = 4;
	
	//White always begins turn one.
	private int spelerKleur = WIT;
	private int aanDeBeurt = WIT;
	private int nietAanDeBeurt = ZWART;
	private int aantalZwarteStenen = 20;
	private int aantalWitteStenen = 20;
	
	private String foutmelding = "";
	private String beurt = "";
	private boolean klaarVoorVerzending = false;
	private String laatsteZet = "";
	
	//private ArduinoJavaComms.arduinoJavaComms ArduinoJavaComms.arduino = new ArduinoJavaComms.arduinoJavaComms();
	
	public static AI ai = new AI();
	private boolean againstAi = false;
	private boolean aiDone = false;
	private String aiMove = "";
	
	//################################# METHODS #####################################
	
	//Constructor. One board is used by one program.
	//Static, so other classes can share this one board.
	public static Dambord bord = new Dambord();
	
	
	public int getAantalWitteStenen(){
		return aantalWitteStenen;
	}
	
	public int getAantalZwarteStenen(){
		return aantalZwarteStenen;
	}
	
	//Prints last turn for online communication.
	public boolean klaarVoorVerzending(){
		return klaarVoorVerzending;
	}
	public void setKlaarVoorVerzending(boolean b){
		klaarVoorVerzending = b;
	}
	//Method to notify a sent message between sockets
	public void verzonden(){
		klaarVoorVerzending = false;
	}
	
	//Returns the 
	public String getScore(){
		String score = "" + aantalWitteStenen + "                  " + aantalZwarteStenen;
		return score;
	}
	
	public void aiSloeg(int verwijderdeKleur){
		if(verwijderdeKleur == ZWART){
			aantalZwarteStenen--;
		}
		if(verwijderdeKleur == WIT){
			aantalWitteStenen--;
		}
	}
	
	//Method to send the last turn to the other player's socket
	public String getLaatsteZet(){
		return laatsteZet;
	}
	
	public int getSpelerKleur(){
		return spelerKleur;
	}
	
	//Method to set the player piece color that was chosen at the start of a match
	public void setSpelerKleur(int kleur){
		spelerKleur = kleur;
		beurt = getSpelerBeurtString();
		if(kleur == ZWART && againstAi){
			ai.makeAMove(WIT, ZWART);
		}
	}
	
	public String getKleurString(){
		if(spelerKleur == 1){
			return "ZWART";
		}
		else if(spelerKleur == 2){
			return "WIT";
		}
		else return "???";
	}
	
	//testing method for the ArduinoJavaComms.arduino to send data of the physical checkers board.
	public void testFysiekBord(){
		fysiekStenen = ArduinoJavaComms.arduino.getFysiekDambord();

		int movedPiece = 0; // a piece that was moved by the player this turn, either a normal piece or a dam
		int newX=0, newY=0, oldX=0, oldY=0;
		int oldstones =0, newstones=0;
		for(int row = 0; row<10; row++){ // this loop is the find out which piece was moved by the player
			for(int column = 0; column<10; column++){
				
				int newPiece = fysiekStenen[row][column]; 				// take the new piece from the physical board
				int oldPiece = stenen[row][column];						// this is the piece that was in the same spot last turn
				
				if(newPiece == 0){ 										// if there is no piece
					if(oldPiece > 0){ 									// if there was a piece there previously
						if(oldPiece == aanDeBeurt || oldPiece == aanDeBeurt+2){ // if that piece belonged to the player who made a move
							movedPiece = oldPiece;						// save which piece was moved, either a normal piece or a dam
							oldX = row;
							oldY = column;
						}
						
					}
				}
			}			
		}
		for(int row = 0; row<10; row++){
			for(int column = 0; column<10; column++){
				
				int newPiece = fysiekStenen[row][column]; 		// take the new piece from the physical board
				int oldPiece = stenen[row][column];				// this is the piece that was in the same spot last turn
						
				if(newPiece == 1){ 								// if there is a piece
					if(oldPiece > 0){ 							// if there also was a piece there last turn
						fysiekStenen[row][column]=oldPiece; 	// that piece should then still be the same piece
					}
					if(oldPiece == 0){							// if there was no piece last turn,
						fysiekStenen[row][column] = movedPiece; // that piece was moved by the player who's turn it was.
						newX = row;
						newY = column;
					}
				}
				if(oldPiece>0){
					oldstones++;
				}
				if(newPiece>0){
					newstones++;
				}
			}
		}
		stenen = fysiekStenen;
		String richting="";
		if(newX>oldX&&newY>oldY){
			richting = "rechtsonder";
		}else if(newX<oldX && newY<oldY){
			richting = "linksboven";
		}else if(newX<oldX && newY>oldY){
			richting = "rechtsboven";
		}else{
			richting = "linksonder";
		}
		if(newstones<oldstones){
			laatsteZet = ""+oldX+""+oldY+"sla"+richting;
		}else{
			laatsteZet = ""+oldX+""+oldY+"schuif"+richting;
		}
		System.out.println(laatsteZet);
	}
	
	
	//Sends an error message to the GUI
	public String getFoutmelding(){
		return foutmelding;
	}
	
	//Sends who's turn it is to the GUI
	public String getBeurt(){
		return beurt;
	}
	
	//Piece selection method
	public boolean setGeselecteerd(int x, int y){
		if(stenen[x][y] != LEEG){
			geselecteerd[0] = x;
			geselecteerd[1] = y;
			return true;
		}
		else{
			foutmelding = "Op het geselecteerde vakje ligt geen steen.";
			return false;
		}
	}
	
	//Returns the kind of piece that is currently selected
	public int getGeselecteerd(){
		return stenen[geselecteerd[0]][geselecteerd[1]];
	}
	
	//Returns the x coordinate of the selected piece.
	public int getGeselecteerdeX(){
		return geselecteerd[0];
	}
	
	//Returns the y coordinate of the selected piece.
	public int getGeselecteerdeY(){
		return geselecteerd[1];
	}
	
	//Returns the kind of piece that is currently on (x,y)
	public int getSoortSteen(int x, int y){
		return stenen[x][y];
	}
	
	//Returns the official field number the selected piece is on.
	public int getVeldNummer(){
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 1;
	}
	
	//Returns the official field number of the piece in a direction from the selected piece.
	public int getVeldNummer(String richting){
		if(getGeselecteerdeX() % 2 == 0){
			return(getFieldDirection(richting, 4, 3, 6, 7));
		}
		else{
			return(getFieldDirection(richting, 5, 4, 5, 6));
		}
	}
	
	//Returns the official field number of the piece in two times a direction from the selected piece.
	public int getVeldNummerNaSlaan(String richting){
		if(getGeselecteerdeX() % 2 == 0){
			return(getFieldDirection(richting, 10, 8, 10, 13));
		}
		else{
			return(getFieldDirection(richting, 10, 8, 10, 12));
		}
	}
	
	//Helper method to get the official field number.
	public int getFieldDirection(String richting, int a, int b, int x, int y){
		switch(richting){
		case "linksboven": 
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - a;
		case "rechtsboven":
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - b;
		case "linksonder":
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + x;
		case "rechtsonder":
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + y;
		default: return 0;
		}
	}
	
	//Prints the current score
	public void printScore(){
		if(aantalWitteStenen == 0)
			System.out.println("Zwart heeft gewonnen.");
		else if(aantalZwarteStenen == 0)
			System.out.println("Wit heeft gewonnen.");
		else{
			System.out.println("Wit heeft " + aantalWitteStenen + " stenen over. Zwart heeft " + aantalZwarteStenen + " stenen over.");
		}
	}
	
	//Moves the selected piece in a certain direction
	public boolean schuif(String richting){
		if(!kanSlaan()){
			//moves a piece 1 up and 1 left
			if(richting.equals("linksboven") && geselecteerd[0] != 0 && geselecteerd[1] != 0 && ((getGeselecteerd() == aanDeBeurt && getGeselecteerd() == WIT && spelerKleur == WIT) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//if no piece is left-up from it:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == LEEG){
						int temp = getGeselecteerd();
						stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
						stenen[geselecteerd[0]-1][geselecteerd[1]-1] = temp;
						//If a piece moved to the side of the opponent, it becomes a king.
						if(geselecteerd[0]-1 == 0 && temp != WITTEDAM && temp != ZWARTEDAM){
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] += 2;
						}
						ArduinoJavaComms.arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-1, geselecteerd[1]-1);
						laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "schuif" + richting;
						beurtVoorbij();
						klaarVoorVerzending = true;
						return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
		
			//moves a piece 1 up and 1 right
			else if(richting.equals("rechtsboven") && geselecteerd[0] != 0 && geselecteerd[1] != 9 && ((getGeselecteerd() == aanDeBeurt && getGeselecteerd() == WIT  && spelerKleur == WIT) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//if no piece is right-up from it:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]-1][geselecteerd[1]+1] = temp;
					//If a piece moved to the side of the opponent, it becomes a king.
					if(geselecteerd[0]-1 == 0 && temp != WITTEDAM && temp != ZWARTEDAM){
						stenen[geselecteerd[0]-1][geselecteerd[1]+1] += 2;
					}
					ArduinoJavaComms.arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-1, geselecteerd[1]+1);
					laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "schuif" + richting;
					beurtVoorbij();
					klaarVoorVerzending = true;
					return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
			//moves a piece 1 down and 1 left. Only usable by a king.
			else if(richting.equals("linksonder") && geselecteerd[0] != 9 && geselecteerd[1] != 0 && ((getGeselecteerd() == aanDeBeurt && getGeselecteerd() == ZWART && spelerKleur == ZWART) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//if no piece is left-down from it:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]-1] = temp;
					ArduinoJavaComms.arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+1, geselecteerd[1]-1);
					laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "schuif" + richting;
					beurtVoorbij();
					klaarVoorVerzending = true;
					return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
			//moves a piece 1 down and 1 right. Only usable by a king.
			else if(richting.equals("rechtsonder") && geselecteerd[0] != 9 && geselecteerd[1] != 9 && ((getGeselecteerd() == aanDeBeurt && getGeselecteerd() == ZWART  && spelerKleur == ZWART) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM)){
				//if no piece is right-down from it:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]+1] = temp;
					ArduinoJavaComms.arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+1, geselecteerd[1]+1);
					laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "schuif" + richting;
					beurtVoorbij();
					klaarVoorVerzending = true;
					return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
			else{
				foutmelding = "Deze steen kan hier niet heen schuiven.";
				return false;
			}
		}
		else{
			foutmelding = "U mag niet schuiven omdat er geslagen kan worden.";
			return false;
		}
	}
	
	//If you are able to capture, you must capture.
	public boolean kanSlaan(){
		
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				if(stenen[x][y] == aanDeBeurt && stenen[x][y] == spelerKleur){
					//check left-up
					if(x != 0 && y != 0){
						if(stenen[x-1][y-1] == nietAanDeBeurt && x-1 != 0 && y-1 != 0){
							if(stenen[x-2][y-2] == LEEG){
								return true;
							}
						}
					}
					//check right-up
					if(x != 0 && y != 9){
						if(stenen[x-1][y+1] == nietAanDeBeurt && x-1 != 0 && y+1 != 9){
							if(stenen[x-2][y+2] == LEEG){
								return true;
							}
						}
					}
					//check left-down
					if(x != 9 && y != 0){
						if(stenen[x+1][y-1] == nietAanDeBeurt && x+1 != 9 && y-1 != 0){
							if(stenen[x+2][y-2] == LEEG){
								return true;
							}
						}
					}
					//check right-down
					if(x != 9 && y != 9){
						if(stenen[x+1][y+1] == nietAanDeBeurt && x+1 != 9 && y+1 != 9){
							if(stenen[x+2][y+2] == LEEG){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	//Captures a piece in a certain direction, if possible.
	public boolean sla(String richting){
		if(kanSlaan()){
			switch(richting){
			case "linksboven": 
				if(geselecteerd[0] != 0 && geselecteerd[1] != 0 && getGeselecteerd() == aanDeBeurt){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == nietAanDeBeurt && geselecteerd[0]-1 != 0 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							ArduinoJavaComms.arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-2, geselecteerd[1]-2, geselecteerd[0]-1, geselecteerd[1]-1);
							laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "sla" + richting;
							klaarVoorVerzending = true;
							if(!kanSlaan()){
								beurtVoorbij();
							}
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "rechtsboven":
				if(geselecteerd[0] != 0 && geselecteerd[1] != 9 && getGeselecteerd() == aanDeBeurt){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == nietAanDeBeurt && geselecteerd[0]-1 != 0 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							ArduinoJavaComms.arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-2, geselecteerd[1]+2, geselecteerd[0]-1, geselecteerd[1]+1);
							laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "sla" + richting;
							klaarVoorVerzending = true;
							if(!kanSlaan()){
								beurtVoorbij();
							}
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "linksonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 0 && getGeselecteerd() == aanDeBeurt){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == nietAanDeBeurt && geselecteerd[0]+1 != 9 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							ArduinoJavaComms.arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+2, geselecteerd[1]-2, geselecteerd[0]+1, geselecteerd[1]-1);
							laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "sla" + richting;
							klaarVoorVerzending = true;
							if(!kanSlaan()){
								beurtVoorbij();
							}
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "rechtsonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 9 && getGeselecteerd() == aanDeBeurt){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == nietAanDeBeurt && geselecteerd[0]+1 != 9 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							ArduinoJavaComms.arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+2, geselecteerd[1]+2, geselecteerd[0]+1, geselecteerd[1]+1);
							laatsteZet = "" + getGeselecteerdeX() + getGeselecteerdeY() + "sla" + richting;
							klaarVoorVerzending = true;
							if(!kanSlaan()){
								beurtVoorbij();
							}
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			default: break;
			}
		}
		else{
			foutmelding = "Er kan niet geslagen worden.";
			return false;
		}
		return false;
	}	
	
	//Rotates the board 90 degrees.
	public void draaiBord(){
		int stenen2[][] = new int[10][10];
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				stenen2[x][y] = stenen[9-x][9-y];
			}
		}
		stenen = stenen2;
	}
	
	//Method to pass the turn to the opponent.
	public void beurtVoorbij(){
			int temp = aanDeBeurt;
			aanDeBeurt = nietAanDeBeurt;
			nietAanDeBeurt = temp;
			beurt = getSpelerBeurtString();
	}
	public void makeAIMove(){
		if(aanDeBeurt!=spelerKleur && againstAi){ //If AI is enabled and its not the players turn.
			try{Thread.sleep(1000);}
			catch(Exception e){
				
			}
			ai.makeAMove(aanDeBeurt, nietAanDeBeurt);
		}
	}
	
	public String getSpelerBeurtString(){
		String spelerString = "";
		if(aanDeBeurt == WIT || aanDeBeurt == WITTEDAM){
			if(spelerKleur == WIT){
				spelerString = "U bent";
			}
			else if(spelerKleur == ZWART){
				spelerString = "Wit is";
			}
		}
		if(aanDeBeurt == ZWART || aanDeBeurt == ZWARTEDAM){
			if(spelerKleur == ZWART){
				spelerString = "U bent";
			}
			else if(spelerKleur == WIT){
				spelerString = "Zwart is";
			}
		}
		return spelerString + " aan de beurt.";
	}
	
	public String getBord(){
		String bord = "";
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				bord += (stenen[x][y] + " ");
			}
			bord += '\n';
		}
		return bord;
	}
	public int[][] getBordArray(){
		return stenen;
	}
	
	public void setBord(String newBord){
		int x = 0;
		int y = 0;
		for(int i = 0; i < newBord.length(); i++){
			if(newBord.charAt(i) == '0' || newBord.charAt(i) == '1' || newBord.charAt(i) == '2' || newBord.charAt(i) == '3' || newBord.charAt(i) == '4'){
				stenen[y][x] = Character.getNumericValue(newBord.charAt(i));
				x = (x + 1) % 10;
			}
			if(newBord.charAt(i) == '\n'){
				y++;	
			}
			else{
				continue;
			}
		}
	}
	public void setBord(int[][] newBord){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				stenen[i][j]= newBord[i][j];
			}
			
		}
	}
	
	//Prints the board in the output console.
	public void printBord(){
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				System.out.print(stenen[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public void endAITurn(String s){
		beurtVoorbij();
		aiDone = true;
		aiMove = s;
		GUI.gui.updatePanel();
	}
	
	public void setAgainstAi(boolean b) {
		againstAi  = b;
	}
	public boolean isAIDone(){
		return aiDone;
	}
	public void setAIDone(boolean b){
		aiDone = b;
	}
	public String getAIMove(){
		return aiMove;
	}
}