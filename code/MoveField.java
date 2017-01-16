import java.util.ArrayList;
import java.util.List;

public class MoveField {
	private static final int ZWART = 1;
	private static final int WIT = 2;
	private int whitePieces = 0;
	private int blackPieces = 0;
	
	private AI ai;
	private int depth;
	private int[][] field;
	private List<int[][]> possibleOutComes = new ArrayList<int[][]>();
	
	public MoveField(int depth, int[][]field, AI ai){
		this.depth = depth;
		this.field = field;
		this.ai = ai;
		
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				int square = field[i][j];
				if(square == WIT || square == WIT+2){
					whitePieces++;
				}else if(square == ZWART || square == ZWART+2){
					blackPieces++;
				}
			}
		}
		
		if(depth < ai.getDifficulty()){
			
		}
	}
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int[][] getField() {
		return field;
	}
	public void setField(int[][] field) {
		this.field = field;
	}
	public List<int[][]> getPossibleOutComes() {
		return possibleOutComes;
	}
	public void setPossibleOutComes(List<int[][]> possibleOutComes) {
		this.possibleOutComes = possibleOutComes;
	}
	
}
