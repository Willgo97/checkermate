
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
	
	//Wit begint het spel altijd.
	private int speler = WIT;
	private int tegenstander = ZWART;
	
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
	
	public void schuif(String richting){
		
		//schuif steen 1 plaats naar boven en 1 plaats naar links
		if(richting.equals("links") && geselecteerd[0] != 0 && geselecteerd[1] != 0){
			//als er geen steen links-boven ligt:
			if(stenen[geselecteerd[0]-1][geselecteerd[1]-1] == LEEG){
					int temp = stenen[geselecteerd[0]][geselecteerd[1]];
					stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
					stenen[geselecteerd[0]-1][geselecteerd[1]-1] = temp;
			}
		}
	
		//schuif steen 1 plaats naar boven en 1 plaats naar rechts
		else if(richting.equals("rechts") && geselecteerd[0] != 0 && geselecteerd[1] != 9){
			//als er geen steen rechts-boven ligt:
			if(stenen[geselecteerd[0]-1][geselecteerd[1]+1] == LEEG){
				int temp = stenen[geselecteerd[0]][geselecteerd[1]];
				stenen[geselecteerd[0]][geselecteerd[1]] = LEEG;
				stenen[geselecteerd[0]-1][geselecteerd[1]+1] = temp;
			}
		}
		else{
			System.out.println("Deze steen kan hier niet heen schuiven.");
		}
	}
	
	//Als je kan slaan, moet je slaan.
	public boolean kanSlaan(){
		
		for(int x = 0; x <= 9; x++){
			for(int y = 0; y <= 9; y++){
				if(stenen[x][y] == speler && x != 0){
					//check links-boven
					if(y != 0){
						if(stenen[x-1][y-1] == tegenstander && x-1 != 0 && y-1 != 0){
							if(stenen[x-2][y-2] == LEEG){
								return true;
							}
						}
					}
					//check rechts-boven
					if(y != 9){
						if(stenen[x-1][y+1] == tegenstander && x-1 != 0 && y+1 != 0){
							if(stenen[x-2][y+2] == LEEG){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
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
