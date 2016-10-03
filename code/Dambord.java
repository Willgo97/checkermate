
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
	
	//################################# METHODS #####################################
	
	public void setGeselecteerd(int x, int y){
		if(stenen[x][y] != LEEG){
			geselecteerd[0] = x;
			geselecteerd[1] = y;
		}
		else{
			System.out.println("Op het geselecteerde vakje ligt geen steen.");
		}
	}
	
	public int getGeselecteerd(){
		return stenen[geselecteerd[0]][geselecteerd[1]];
	}
	
	public void printScore(){
		if(aantalWitteStenen == 0)
			System.out.println("Zwart heeft gewonnen.");
		else if(aantalZwarteStenen == 0)
			System.out.println("Wit heeft gewonnen.");
		else{
			System.out.println("Wit heeft " + aantalWitteStenen + " stenen over. Zwart heeft " + aantalZwarteStenen + " stenen over.");
		}
	}
	
	public boolean schuif(String richting){
		if(!kanSlaan()){
			//schuif steen 1 plaats naar boven en 1 plaats naar links
			if(richting.equals("linksboven") && geselecteerd[0] != 0 && geselecteerd[1] != 0){
				//als er geen steen links-boven ligt:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == LEEG){
						int temp = getGeselecteerd();
						stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
						stenen[geselecteerd[0]-1][geselecteerd[1]-1] = temp;
						//als de steen naar de rand van de tegenstander is geschoven, wordt het een dam.
						if(geselecteerd[0]-1 == 0){
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] += 2;
						}
						return true;
				}
				else{
					System.out.println("Deze steen kan hier niet heen schuiven.");
					return false;
				}
			}
		
			//schuif steen 1 plaats naar boven en 1 plaats naar rechts
			else if(richting.equals("rechtsboven") && geselecteerd[0] != 0 && geselecteerd[1] != 9){
				//als er geen steen rechts-boven ligt:
				if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]-1][geselecteerd[1]+1] = temp;
					//als de steen naar de rand van de tegenstander is geschoven, wordt het een dam.
					if(geselecteerd[0]-1 == 0){
						stenen[geselecteerd[0]-1][geselecteerd[1]-1] += 2;
					}
					return true;
				}
				else{
					System.out.println("Deze steen kan hier niet heen schuiven.");
					return false;
				}
			}
			//schuif steen 1 plaats naar links en 1 plaats naar onder. Alleen bij een dam.
			else if(richting.equals("linksonder") && geselecteerd[0] != 9 && geselecteerd[1] != 0 && (getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM)){
				//als er geen steen links-onder ligt:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]-1] = temp;
					return true;
				}
				else{
					System.out.println("Deze steen kan hier niet heen schuiven.");
					return false;
				}
			}
			//schuif steen 1 plaats naar rechts en 1 plaats naar onder. Alleen bij een dam.
			else if(richting.equals("rechtsonder") && geselecteerd[0] != 9 && geselecteerd[1] != 9 && (getGeselecteerd() == WITTEDAM || getGeselecteerd() == ZWARTEDAM)){
				//als er geen steen rechts-onder ligt:
				if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == LEEG){
					int temp = getGeselecteerd();
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]+1][geselecteerd[1]+1] = temp;
					return true;
				}
				else{
					System.out.println("Deze steen kan hier niet heen schuiven.");
					return false;
				}
			}
			else{
				System.out.println("Deze steen kan hier niet heen schuiven.");
				return false;
			}
		}
		else{
			System.out.println("U kan niet schuiven omdat er geslagen kan worden.");
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
	
	public boolean sla(String richting){
		if(kanSlaan()){
			switch(richting){
			case "linksboven": 
				if(geselecteerd[0] != 0 && geselecteerd[1] != 0){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == tegenstander && geselecteerd[0]-1 != 0 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							return true;
						}
					}
				}
				break;
			case "rechtsboven":
				if(geselecteerd[0] != 0 && geselecteerd[1] != 9){
					if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == tegenstander && geselecteerd[0]-1 != 0 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]-2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]-2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]-1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							return true;
						}
					}
				}
				break;
			case "linksonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 0){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]-1] == tegenstander && geselecteerd[0]+1 != 9 && geselecteerd[1]-1 != 0){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]-2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]-2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]-1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							return true;
						}
					}
				}
				break;
			case "rechtsonder":
				if(geselecteerd[0] != 9 && geselecteerd[1] != 9){
					if(stenen[geselecteerd[0]+1][geselecteerd[1]+1] == tegenstander && geselecteerd[0]+1 != 9 && geselecteerd[1]+1 != 9){
						if(stenen[geselecteerd[0]+2][geselecteerd[1]+2] == LEEG){
							if(getGeselecteerd() == WIT)
								aantalZwarteStenen--;
							if(getGeselecteerd() == ZWART)
								aantalWitteStenen--;
							stenen[geselecteerd[0]+2][geselecteerd[1]+2] = getGeselecteerd();
							stenen[geselecteerd[0]+1][geselecteerd[1]+1] = LEEG;
							stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
							return true;
						}
					}
				}
				break;
			default: break;
			}
		}
		else{
			System.out.println("Er kan niet geslagen worden.");
			return false;
		}
		return false;
	}
	
	public void draaiBord(){
		int stenen2[][] = new int[10][10];
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				stenen2[x][y] = stenen[9-x][9-y];
			}
		}
		stenen = stenen2;
		//beurt voorbij, dus wissel speler en tegenstander
		int temp = speler;
		speler = tegenstander;
		tegenstander = temp;
	}
	
	public void printBord(){
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				System.out.print(stenen[x][y] + " ");
			}
			System.out.println();
		}
	}
}