import java.util.Scanner;

//Class for Unit Tests and a command line game experience (no graphical interface).

public class Damspel {
	
	//Ends the turn for the current player
	public static void be�indigBeurt(Dambord bord){
		System.out.println("Na uw opdracht ziet het bord er zo uit:");
		bord.printBord();
		System.out.println("De volgende speler is aan de beurt. Van zijn/haar perspectief ziet het bord er zo uit:");
		bord.draaiBord();
		bord.printBord();
		System.out.println("Welke opdracht wilt u uitvoeren?");
	}
	
	//main for unit tests. De-comment a block to test the method.
	public static void main(String[] args){
		Dambord bord = new Dambord();
		/*
		// Capture and move tests
		bord.setGeselecteerd(6, 1);
		bord.schuif("linksboven");
		bord.printBord();
		System.out.println(bord.kanSlaan() ? "Er kan geslagen worden" : "Er kan niet geslagen worden");
		System.out.println();
		
		bord.draaiBord();
		System.out.println(bord.kanSlaan() ? "Er kan geslagen worden" : "Er kan niet geslagen worden");
		System.out.println();
		
		bord.setGeselecteerd(6, 7);
		bord.schuif("rechtsboven");
		bord.printBord();
		
		System.out.println(bord.kanSlaan() ? "Er kan geslagen worden" : "Er kan niet geslagen worden");
		System.out.println();
		bord.draaiBord();
		System.out.println(bord.kanSlaan() ? "Er kan geslagen worden" : "Er kan niet geslagen worden");
		System.out.println();
		
		bord.setGeselecteerd(5, 0);
		bord.sla("rechtsboven");
		bord.printBord();
		System.out.println(bord.kanSlaan() ? "Er kan geslagen worden" : "Er kan niet geslagen worden");
		
		bord.printScore();
		*/
		/*
		bord.testFysiekBord("1 lol ik kan hier alles zetten  1 1 1 1 1 1 1 1 1, "
						  + "1 1 zo 1 1 1 1 1 1 1 1, "
						  + "1 1 1 lang 1 1 1 1 1 1 1, "
						  + "0000000000, er "
						  + "1 100101010,    maar "
						  + "1 1 1 1 1 1 1 1 1 1, tien"
						  + "1 1 1 1 1 1 1 1 1 1,       getallen"
						  + "1919191919191919191,                van nul t/m vier"
						  + "1 1 1 1 1 1 1 1 1 1, 									per rij"
						  + "1 2 3 4 1 2 3 4 1 2											staan.");
		*/
		
		bord.setBord("1 lol ik kan hier alles zetten  1 1 1 1 1 1 1 1 1\n "
						  + "1 1 zo 1 1 1 1 1 1 1 1\n "
						  + "1 1 1 lang 1 1 1 1 1 1 1\n "
						  + "0000000000\n er "
						  + "1 100101010\n    maar "
						  + "1 1 1 1 1 1 1 1 1 1\n tien"
						  + "1 1 1 1 1 1 1 1 1 1\n       getallen"
						  + "1919191919191919191\n                van nul t/m vier"
						  + "1 1 1 1 1 1 1 1 1 1\n 									per rij"
						  + "1 2 3 4 1 2 3 4 1 2											staan.");
		System.out.println(bord.getBord());
		
		/*
		//starting text
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Welkom bij dammen met CheckerMate!");
		System.out.println("Het bord ziet er zo uit:");
		bord.printBord();
		System.out.println("Legenda: 0 = een leeg veld, 1 = een zwarte steen, 2 = een witte steen, 3 = een zwarte dam, 4 = een witte dam.");
		System.out.println("Geldige opdrachten zijn:\n- exit/stop/afsluiten\n- selecteer x y\n- schuif {linksboven, linksonder, rechtsboven, rechtsonder}\n- sla {linksboven, linksonder, rechtsboven, rechtsonder}\n- draai/draaien/draai bord\n- score\nWelke opdracht wilt u nu uitvoeren?");
		
		//inputscanner
        while(sc.hasNextLine() && !flag){
        	
        	String input = sc.nextLine();
        	if(input.equals("exit") || input.equals("stop") || input.equals("afsluiten")){
        		sc.close();
        		System.exit(0);
        	}
        	if(input.equals("help")){
        		System.out.println("Geldige opdrachten zijn:\n- exit/stop/afsluiten\n- selecteer x y\n- schuif {linksboven, linksonder, rechtsboven, rechtsonder}\n- sla {linksboven, linksonder, rechtsboven, rechtsonder}\n- draai/draaien/draai bord\n- score\nWelke opdracht wilt u nu uitvoeren?");
        	}
        	else if(input.startsWith("selecteer")){
        		if(Character.getNumericValue(input.charAt(10)) >= 0 && Character.getNumericValue(input.charAt(10)) <= 9 && Character.getNumericValue(input.charAt(12)) >= 0 && Character.getNumericValue(input.charAt(12)) <= 9){
        			bord.setGeselecteerd(Character.getNumericValue(input.charAt(10)), Character.getNumericValue(input.charAt(12)));
        			System.out.println("U selecteerde: " + input.charAt(10) + ", " + input.charAt(12) + ".");
        		}
        		System.out.println("Welke opdracht wilt u nu uitvoeren?");
        	}
        	else if(input.startsWith("schuif")){
        		if(bord.schuif(input.substring(7, input.length())))
        			be�indigBeurt(bord);
        	}
        	else if(input.startsWith("sla")){
        		if(bord.sla(input.substring(4, input.length())))
        			be�indigBeurt(bord);
        	}
        	else if(input.equals("draai") || input.equals("draaien") || input.equals("draai bord")){
        		System.out.println("Na uw opdracht ziet het bord er zo uit:");
        		bord.draaiBord();
        		bord.printBord();
        		System.out.println("Welke opdracht wilt u uitvoeren?");
        	}
        	else if(input.equals("score")){
        		bord.printScore();
        		System.out.println("Welke opdracht wilt u nu uitvoeren?");
        	}
        	else{
        		System.out.println("Dit is geen geldige invoer.\nVoer een andere opdracht uit.\nTyp help als u alle mogelijke opdrachten wilt bekijken.");
        	}
        }
        sc.close();
        */
	}
}