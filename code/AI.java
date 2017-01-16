import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AI {
	public AI(){}
	
	// FIELDS
	private static final int EMPTY = 0;
	private static final int ZWART = 1;
	private static final int WIT = 2;
	private int currentField[][];
	private List<int[][]> possibleOutComes = new ArrayList<int[][]>();
	private List<MoveField> outComes = new ArrayList<MoveField>();
	private int color;
	private int opponent;
	private int[][] newField;
	private int difficulty = 2;
	
	
	// METHODS
	public void makeAMove(int c, int o){
		System.out.println("The AI is making a move..");
		possibleOutComes.clear(); // clear the list of possible moves so there are no old moves stored.
		color = c;		// this is for which color the AI is taking a turn.
		opponent = o;	// this is the color of the opponent
		
		int [][] tempField = Dambord.bord.getBordArray(); // get the current field layout.
		currentField = cloneArray(tempField); 	 // copy the field into a new array so we can manipulate without manipulating the live playing field.
		
		if(takePiece(currentField)){
			calcBestMove();
			if(possibleOutComes.size()>1){
				for(int i =0; i<possibleOutComes.size(); i++){
					outComes.add(new MoveField(1,possibleOutComes.get(i),this));
					
				}
				newField = possibleOutComes.get(1);
				Dambord.bord.setBord(newField);
			}else{
				newField = possibleOutComes.get(0);
				Dambord.bord.setBord(newField);
			}
		}else{	// if it's not possible to take a piece.
			movePiece(currentField);
			if(possibleOutComes.size()>0){
				newField = possibleOutComes.get(ThreadLocalRandom.current().nextInt(0,possibleOutComes.size()));
				Dambord.bord.setBord(newField);
			}
			else{
				System.out.println("U heeft gewonnen!");
			}
		}
		
		Dambord.bord.endAITurn(createMoveString()); // end the AI's turn
	}// end of makeAMove method
	
	public String createMoveString(){
		int movedPiece = 0; // a piece that was moved by the player this turn, either a normal piece or a dam
		int oldPos[] ={0,0,0};
		int newPos[] ={0,0,0};
		int removedPos[] = {0,0,0};
		boolean hit = false;
		int hits = 0;
		int[][] hitPos = new int[10][2];
		for(int row = 0; row<10; row++){ // this loop is the find out which piece was moved by the player
			for(int column = 0; column<10; column++){
				
				int newPiece = newField[row][column]; 				// take the new piece new board layout
				int oldPiece = currentField[row][column];			// this is the piece that was in the same spot last turn
				
				if(newPiece == EMPTY){ 									// if there is no piece
					if(oldPiece != EMPTY){ 								// if there was a piece there previously
						if(oldPiece == color || oldPiece == color+2){ 	// if that piece belonged to the player who made a move
							oldPos[0] = row*5 + column/2+1; 				// save the location of the moved piece.
							oldPos[2] = column;
							oldPos[1] = row;
							
						}else{											// if it doesnt belong to the player who made it move it means it was removed.
							removedPos[0]=row*5+column/2+1;				// save the location of the hit piece
							removedPos[2] = column;
							removedPos[1] = row;
							hit = true;
							hits++;
							hitPos[hits][0] = row;
							hitPos[hits][1] = column;
						}
					}
				}else if(newPiece != EMPTY){		// if there is a piece
					if(oldPiece == EMPTY){			// but there was no piece there previously
						newPos[0] = row*5+column/2+1; 	// it's been moved there
						newPos[2] = column;
						newPos[1] = row;
					}	
				}
				
				
			}			
		}
		String result;
		
		if(hit){
			result = "Steen " + oldPos[0] + " sloeg " + removedPos[0] + " en belandde op " + newPos[0] +".\n";
			ArduinoJavaComms.arduino.robotSlaat(oldPos[1], oldPos[2], newPos[1], newPos[2], removedPos[1], removedPos[2]);
			Dambord.bord.aiSloeg(opponent);
			if(hits>1){
				for(int i=1;i<hits;i++){
					ArduinoJavaComms.arduino.robotSlaStuk(hitPos[i][0], hitPos[i][1]);
					Dambord.bord.aiSloeg(opponent);
					System.out.println("Robot slaat x: "+hitPos[i][0]+"y: "+hitPos[i][1]);
				}
			}
			
		}else{
			ArduinoJavaComms.arduino.robotSchuift(oldPos[1], oldPos[2], newPos[1], newPos[2]);
			result = "Steen " + oldPos[0] + " schoof naar " + newPos[0] + ".\n";
		}
		
		return result;
	}
	
	private int[][] cloneArray(int[][] source){ // this clones the array and puts it in a new array 
		int destArray[][] = new int[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				destArray[i][j]= source[i][j];
			}
			
		}
		return destArray; 
	}
	
	public void movePiece(int[][] field){
		int square;
		int direction;
		if(color == ZWART){ //Each color can move their pieces only in 1 direction
			direction = 1;
		}else {
			direction = -1;
		}
		for(int i=0;i<10;i++){		// loop through the rows.
			for(int j=0;j<10;j++){	// loop through the columns.
				square = field[i][j];
				if(square == color){ // if the square belongs to the AI it can use it.
					if(i+direction <=9 && i+direction >= 0){ 	// don't want out of bounds exception.
						if(j-1>=0){ 							// don't want out of bounds exception.
							if(field[i+direction][j-1] == EMPTY){	// if it's empty we can put the piece there.
								int[][] newField = cloneArray(field);
								newField[i][j] = EMPTY;					// remove the piece making a move.
								newField[i+direction][j-1] = square;	// put the piece in the new position.
								possibleOutComes.add(newField); 		// add the new field to the possible outcomes.					
							}
						}
						if(j+1<=9){								// don't want out of bounds exception.
							if(field[i+direction][j+1] == EMPTY){ // if it's empty we can put the piece there.
								int[][] newField = cloneArray(field);
								newField[i][j] = EMPTY;					// remove the piece making a move.
								newField[i+direction][j+1] = square;	// put the piece in the new position.
								possibleOutComes.add(newField); 		// add the new field to the possible outcomes.	
							}
						}
					}
				}
			}
		}
	}//end of movePiece method
	
	public boolean takePiece(int[][] field){
		boolean hit = false;
		for(int i=0;i<10;i++){		// loop through the rows.
			for(int j=0;j<10;j++){	// loop through the columns.
				if(hit(i,j,field)){
					hit = true;
				}
			}
		}
		return hit;
	}// End method takePiece
	
	
	public boolean hit(int i, int j, int[][] field){
		boolean hit = false;
		int square = field[i][j];
		//System.out.print(square);
		if(square == color || square == (color+2)){ // if the square belongs to the AI it can use it.
			if(i > 1 && j > 1){						// if the piece is to close to the border it can't take a piece anyway.
				if(field[i-1][j-1] == opponent || field[i-1][j-1] == opponent+2){ 	// if the piece top left belongs to the opponent.
					if(field[i-2][j-2] == EMPTY){ 	// if the field behind is empty.
						hit = true;
						int[][] newField = cloneArray(field);
						newField[i][j] = EMPTY;			// remove the piece making a move.
						newField[i-1][j-1] = EMPTY;		// remove the piece getting taken.
						newField[i-2][j-2] = square;	// place your piece behind the taken piece.
						if(hit(i-2,j-2,newField)){
							
						}else{
							possibleOutComes.add(newField); // add the new field to the possible outcomes.
						}
					}
				}
			}
			if(i > 1 && j <8){
				if(field[i-1][j+1] == opponent || field[i-1][j+1] == opponent+2){ 	// if the piece top right belongs to the opponent.
					if(field[i-2][j+2] == EMPTY){ 	// if the field behind is empty
						hit = true;
						int[][] newField = cloneArray(field);
						newField[i][j] = EMPTY;			// remove the piece making a move.
						newField[i-1][j+1] = EMPTY;		// remove the piece getting taken.
						newField[i-2][j+2] = square;	// place your piece behind the taken piece.
						if(hit(i-2,j+2,newField)){
							
						}else{
							possibleOutComes.add(newField); // add the new field to the possible outcomes.
						}
					}
				}
			}
			if(i<8 && j>1){
				if(field[i+1][j-1] == opponent|| field[i+1][j-1] == opponent+2){ 	// if the piece down left belongs to the opponent.
					if(field[i+2][j-2] == EMPTY){ 	// if the field behind is empty
						hit = true;
						int[][] newField = cloneArray(field);
						newField[i][j] = EMPTY;			// remove the piece making a move.
						newField[i+1][j-1] = EMPTY;		// remove the piece getting taken.
						newField[i+2][j-2] = square;	// place your piece behind the taken piece.
						if(hit(i+2,j-2,newField)){
							
						}else{
							possibleOutComes.add(newField); // add the new field to the possible outcomes.
						}
					}
				}
			}
			if(i<8 && j<8){
				if(field[i+1][j+1] == opponent || field[i+1][j+1] == opponent+2){ 	// if the piece down right belongs to the opponent.
					if(field[i+2][j+2] == EMPTY){ 	// if the field behind is empty
						hit = true;
						int[][] newField = cloneArray(field);
						newField[i][j] = EMPTY;			// remove the piece making a move.
						newField[i+1][j+1] = EMPTY;		// remove the piece getting taken.
						newField[i+2][j+2] = square;	// place your piece behind the taken piece.
						if(hit(i+2,j+2,newField)){
							
						}else{
							possibleOutComes.add(newField); // add the new field to the possible outcomes.
						}
					}
				}
			}
		}
		return hit;
	}// End method hit
	
	public void calcBestMove(){
		List<int[][]> legitOutComes = new ArrayList<int[][]>();
		int pieces =0;
		for(int row =0;row<10;row++){
			for(int col =0;col<10;col++){
				if(currentField[row][col] == opponent || currentField[row][col] == opponent+2){
					pieces++;
				}
			}
		}
		int hits =0;
		for(int i =0; i<possibleOutComes.size();i++){
			int[][] tempfield = possibleOutComes.get(i);
			int newPieces=0;
			for(int row =0;row<10;row++){
				for(int col =0;col<10;col++){
					if(tempfield[row][col] == opponent || tempfield[row][col] == opponent+2){
						newPieces++;
					}
				}
			}
			if(pieces-newPieces > hits){
				hits = pieces - newPieces;
			}
		}
		for(int i =0; i<possibleOutComes.size();i++){
			int[][] tempfield = possibleOutComes.get(i);
			int newPieces =0;
			for(int row =0;row<10;row++){
				for(int col =0;col<10;col++){
					if(tempfield[row][col] == opponent || tempfield[row][col] == opponent+2){
						newPieces++;
					}
				}
			}
			if(pieces-newPieces == hits){
				legitOutComes.add(possibleOutComes.get(i));
			}
			possibleOutComes = legitOutComes;
		}
		
	}// end method calcBestMove
	public void calcBestMoves(){
		
	}//end method calcBestMoves

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
