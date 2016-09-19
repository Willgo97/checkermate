
public class Damspel {
	
	public static void main(String[] args){
		Dambord bord = new Dambord();
		
		
		// Slaan- en schuiftest
		bord.setGeselecteerd(6, 1);
		bord.schuif("links");
		bord.printBord();
		
		System.out.println();
		
		bord.setGeselecteerd(5, 0);
		bord.schuif("rechts");
		bord.printBord();
	}

}
