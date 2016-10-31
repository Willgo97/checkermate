public class Dambord {	
	//################################# FIELDS #####################################
	
	//0 = geen steen, 1 = zwart, 2 = wit
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
	
	private int[] geselecteerd = {9,0};
	
	private static final int LEEG = 0;
	private static final int ZWART = 1;
	private static final int WIT = 2;
	private static final int ZWARTEDAM = 3;
	private static final int WITTEDAM = 4;
	
	//Wit begint altijd met de eerste zet.
	private int speler = WIT;
	private int tegenstander = ZWART;
	private int aantalZwarteStenen = 20;
	private int aantalWitteStenen = 20;
	
	private String foutmelding = "";
	private String beurt = "Wit is aan de beurt.";
	
	private ArduinoJavaComms arduino = new ArduinoJavaComms();
	
	//################################# METHODS #####################################
	
	//constructor
	public Dambord(){
		//arduino.initialize();
	}
	
	//stuurt een foutmelding naar de GUI
	public String getFoutmelding(){
		return foutmelding;
	}
	
	//stuurt wie er aan de beurt is naar de GUI
	public String getBeurt(){
		return beurt;
	}
	
	//steenselectiemethode
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
	
	//krijg de kleur van de geselecteerde steen
	public int getGeselecteerd(){
		return stenen[geselecteerd[0]][geselecteerd[1]];
	}
	
	//krijg de x-coordinaat van de geselecteerde steen
	public int getGeselecteerdeX(){
		return geselecteerd[0];
	}
	
	//krijg de y-coordinaat van de geselecteerde steen
	public int getGeselecteerdeY(){
		return geselecteerd[1];
	}
	
	//krijg de kleur van een steen op positie <x,y>
	public int getSoortSteen(int x, int y){
		return stenen[x][y];
	}
	
	//krijg de officiële nummering van het veld waar de steen op ligt
	public int getVeldNummer(){
			return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 1;
	}
	
	//krijg het veldnummer in de richting vanaf de geselecteerde steen
	public int getVeldNummer(String richting){
		if(getGeselecteerdeX() % 2 == 0){
			switch(richting){
			case "linksboven": 
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 4;
			case "rechtsboven":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 3;
			case "linksonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 6;
			case "rechtsonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 7;
			default: return 0;
			}
		}
		else{
			switch(richting){
			case "linksboven": 
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 5;
			case "rechtsboven":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 4;
			case "linksonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 5;
			case "rechtsonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 6;
			default: return 0;
			}
		}
	}
	
	//krijg het veldnummer in de richting 2 plaatsen van de geselecteerde steen vandaan
	public int getVeldNummerNaSlaan(String richting){
		if(getGeselecteerdeX() % 2 == 0){
			switch(richting){
			case "linksboven": 
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 10;
			case "rechtsboven":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 8;
			case "linksonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 10;
			case "rechtsonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 13;
			default: return 0;
			}
		}
		else{
			switch(richting){
			case "linksboven": 
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 10;
			case "rechtsboven":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 - 8;
			case "linksonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 10;
			case "rechtsonder":
				return getGeselecteerdeX() * 5 + getGeselecteerdeY()/2 + 12;
			default: return 0;
			}
		}
	}
	
	//geeft de score weer
	public void printScore(){
		if(aantalWitteStenen == 0)
			System.out.println("Zwart heeft gewonnen.");
		else if(aantalZwarteStenen == 0)
			System.out.println("Wit heeft gewonnen.");
		else{
			System.out.println("Wit heeft " + aantalWitteStenen + " stenen over. Zwart heeft " + aantalZwarteStenen + " stenen over.");
		}
	}
	
	//schuift een steen in een bepaalde richting
	public boolean schuif(String richting){
		if(!kanSlaan()){
			//schuif steen 1 plaats naar boven en 1 plaats naar links
			if(richting.equals("linksboven") && geselecteerd[0] != 0 && geselecteerd[1] != 0 && ((getGeselecteerd() == speler && getGeselecteerd() == WIT) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//als er geen steen links-boven ligt:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == LEEG){
						int temp = getGeselecteerd();
						stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
						stenen[geselecteerd[0]-1][geselecteerd[1]-1] = temp;
						//als de steen naar de rand van de tegenstander is geschoven, wordt het een dam.
						if(geselecteerd[0]-1 == 0 && temp != WITTEDAM && temp != ZWARTEDAM){
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] += 2;
						}
						arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-1, geselecteerd[1]-1);
						beurtVoorbij();
						return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
		
			//schuif steen 1 plaats naar boven en 1 plaats naar rechts
			else if(richting.equals("rechtsboven") && geselecteerd[0] != 0 && geselecteerd[1] != 9 && ((getGeselecteerd() == speler && getGeselecteerd() == WIT) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//als er geen steen rechts-boven ligt:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]-1][geselecteerd[1]+1] = temp;
					//als de steen naar de rand van de tegenstander is geschoven, wordt het een dam.
					if(geselecteerd[0]-1 == 0 && temp != WITTEDAM && temp != ZWARTEDAM){
						stenen[geselecteerd[0]-1][geselecteerd[1]+1] += 2;
					}
					arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-1, geselecteerd[1]+1);
					beurtVoorbij();
					return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
			//schuif steen 1 plaats naar links en 1 plaats naar onder. Alleen bij een dam.
			else if(richting.equals("linksonder") && geselecteerd[0] != 9 && geselecteerd[1] != 0 && ((getGeselecteerd() == speler && getGeselecteerd() == ZWART) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM) ){
				//als er geen steen links-onder ligt:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]-1] = temp;
					arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+1, geselecteerd[1]-1);
					beurtVoorbij();
					return true;
				}
				else{
					foutmelding = "Deze steen kan hier niet heen schuiven.";
					return false;
				}
			}
			//schuif steen 1 plaats naar rechts en 1 plaats naar onder. Alleen bij een dam.
			else if(richting.equals("rechtsonder") && geselecteerd[0] != 9 && geselecteerd[1] != 9 && ((getGeselecteerd() == speler && getGeselecteerd() == ZWART) || getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM)){
				//als er geen steen rechts-onder ligt:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]+1] = temp;
					arduino.robotSchuift(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+1, geselecteerd[1]+1);
					beurtVoorbij();
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
	
	//Als je kan slaan, moet je slaan.
	public boolean kanSlaan(){
		
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				if(stenen[x][y] == speler ){
					//check links-boven
					if(x != 0 && y != 0){
						if(stenen[x-1][y-1] == tegenstander && x-1 != 0 && y-1 != 0){
							if(stenen[x-2][y-2] == LEEG){
								return true;
							}
						}
					}
					//check rechts-boven
					if(x != 0 && y != 9){
						if(stenen[x-1][y+1] == tegenstander && x-1 != 0 && y+1 != 9){
							if(stenen[x-2][y+2] == LEEG){
								return true;
							}
						}
					}
					//check links-onder
					if(x != 9 && y != 0){
						if(stenen[x+1][y-1] == tegenstander && x+1 != 9 && y-1 != 0){
							if(stenen[x+2][y-2] == LEEG){
								return true;
							}
						}
					}
					//check rechts-onder
					if(x != 9 && y != 9){
						if(stenen[x+1][y+1] == tegenstander && x+1 != 9 && y+1 != 9){
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
	
	//slaat in de richting die aangegeven is, als dit mogelijk is.
	public boolean sla(String richting){
		if(kanSlaan()){
			switch(richting){
			case "linksboven": 
				if(geselecteerd[0] != 0 && geselecteerd[1] != 0 && getGeselecteerd() == speler){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == tegenstander && geselecteerd[0]-1 != 0 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-2, geselecteerd[1]-2);
							if(!kanSlaan())
								beurtVoorbij();
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "rechtsboven":
				if(geselecteerd[0] != 0 && geselecteerd[1] != 9 && getGeselecteerd() == speler){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == tegenstander && geselecteerd[0]-1 != 0 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]-2, geselecteerd[1]+2);
							if(!kanSlaan())
								beurtVoorbij();
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "linksonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 0 && getGeselecteerd() == speler){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == tegenstander && geselecteerd[0]+1 != 9 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+2, geselecteerd[1]-2);
							if(!kanSlaan())
								beurtVoorbij();
							else
								foutmelding = "U kunt nog een keer slaan";
							return true;
						}
					}
				}
				break;
			case "rechtsonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 9 && getGeselecteerd() == speler){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == tegenstander && geselecteerd[0]+1 != 9 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							arduino.robotSlaat(getGeselecteerdeX(), getGeselecteerdeY(), geselecteerd[0]+2, geselecteerd[1]+2);
							if(!kanSlaan())
								beurtVoorbij();
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
	
	//draait het bord 90 graden (ondersteboven)
	public void draaiBord(){
		int stenen2[][] = new int[10][10];
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				stenen2[x][y] = stenen[9-x][9-y];
			}
		}
		stenen = stenen2;
	}
	
	//methode om de volgende speler aan de beurt te laten gaan
	public void beurtVoorbij(){
			int temp = speler;
			speler = tegenstander;
			tegenstander = temp;
			String spelerString = "";
			if(speler == 2 || speler == 4)
				spelerString = "Wit";
			if(speler == 1 || speler == 3)
				spelerString = "Zwart";
			beurt = spelerString + " is aan de beurt.";
	}
	
	//geeft het bord weer in de output console
	public void printBord(){
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				System.out.print(stenen[x][y] + " ");
			}
			System.out.println();
		}
	}
}