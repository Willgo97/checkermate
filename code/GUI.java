import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

//Graphical User Interface of the checkers game
public class GUI extends JFrame {
	//Fields
	private static final long serialVersionUID = 1L;
	public static GUI gui = new GUI();
	private JPanel contentPane;
	private JTextField txtSchuiven;
	private JTextField txtSlaan;
	private JLabel[][] stenen = new JLabel[10][10];
	private JTextArea statusText;
	private JTextArea geschiedenisTekst;
	private JTextField aanDeBeurtTekst;
	private JLabel scoreLabel;
	private boolean gameFinished = false;
	private boolean testField = false;
	
	 //creates the graphical user interface.
	public GUI() {
		setResizable(false);
		setTitle("CheckerMate dammen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Kleurlabel, must be ABOVE (code-wise) infoText in order to appear on top of infoText.
		JLabel colorLabel = new JLabel(Dambord.bord.getKleurString());
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		if(Dambord.bord.getKleurString().equals("WIT")){
			colorLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
			colorLabel.setForeground(Color.WHITE);
			colorLabel.setBackground(Color.BLACK);
			colorLabel.setOpaque(true);
		}
		colorLabel.setBounds(943, 97, 86, 23);
		contentPane.add(colorLabel);
		
		// ######## TEXT AREA ########
		final JTextArea infoText = new JTextArea();
		infoText.setFont(new Font("Arial", Font.PLAIN, 15));
		infoText.setEditable(false);
		infoText.setWrapStyleWord(true);
		infoText.setLineWrap(true);
		infoText.setText("Welkom bij dammen met CheckerMate! Klik eerst op een damschijf die u wilt gebruiken en maak daarna uw zet door op de knoppen hieronder te klikken.\nUw gekozen steenkleur is");
		infoText.setBounds(773, 11, 256, 120);
		contentPane.add(infoText);
		
		//  ######### LABELS #########
		final JLabel zwart1 = new JLabel("");	
		maakSteenFocusListener(zwart1);
		maakSteenMouseListener(zwart1, 0, 1);
		maakSteenLayout(zwart1, "/black circle.png", 83, 6, 0, 1);

		final JLabel zwart2 = new JLabel("");
		maakSteenFocusListener(zwart2);
		maakSteenMouseListener(zwart2, 0, 3);
		maakSteenLayout(zwart2, "/black circle.png", 232, 6, 0, 3);
		
		final JLabel zwart3 = new JLabel("");
		maakSteenFocusListener(zwart3);
		maakSteenMouseListener(zwart3, 0, 5);
		maakSteenLayout(zwart3, "/black circle.png", 380, 6, 0, 5);

		final JLabel zwart4 = new JLabel("");
		maakSteenFocusListener(zwart4);
		maakSteenMouseListener(zwart4, 0, 7);
		maakSteenLayout(zwart4, "/black circle.png", 534, 6, 0, 7);

		final JLabel zwart5 = new JLabel("");
		maakSteenFocusListener(zwart5);
		maakSteenMouseListener(zwart5, 0, 9);
		maakSteenLayout(zwart5, "/black circle.png", 688, 6, 0, 9);

		final JLabel zwart6 = new JLabel("");
		maakSteenFocusListener(zwart6);
		maakSteenMouseListener(zwart6, 1, 0);
		maakSteenLayout(zwart6, "/black circle.png", 5, 81, 1, 0);

		final JLabel zwart7 = new JLabel("");
		maakSteenFocusListener(zwart7);
		maakSteenMouseListener(zwart7, 1, 2);
		maakSteenLayout(zwart7, "/black circle.png", 157, 81, 1, 2);

		final JLabel zwart8 = new JLabel("");
		maakSteenFocusListener(zwart8);
		maakSteenMouseListener(zwart8, 1, 4);
		maakSteenLayout(zwart8, "/black circle.png", 307, 81, 1, 4);

		final JLabel zwart9 = new JLabel("");
		maakSteenFocusListener(zwart9);
		maakSteenMouseListener(zwart9, 1, 6);
		maakSteenLayout(zwart9, "/black circle.png", 457, 81, 1, 6);

		final JLabel zwart10 = new JLabel("");
		maakSteenFocusListener(zwart10);
		maakSteenMouseListener(zwart10, 1, 8);
		maakSteenLayout(zwart10, "/black circle.png", 607, 81, 1, 8);

		final JLabel zwart11 = new JLabel("");
		maakSteenFocusListener(zwart11);
		maakSteenMouseListener(zwart11, 2, 1);
		maakSteenLayout(zwart11, "/black circle.png", 83, 155, 2, 1);

		final JLabel zwart12 = new JLabel("");
		maakSteenFocusListener(zwart12);
		maakSteenMouseListener(zwart12, 2, 3);
		maakSteenLayout(zwart12, "/black circle.png", 232, 155, 2, 3);

		final JLabel zwart13 = new JLabel("");
		maakSteenFocusListener(zwart13);
		maakSteenMouseListener(zwart13, 2, 5);
		maakSteenLayout(zwart13, "/black circle.png", 380, 155, 2, 5);

		final JLabel zwart14 = new JLabel("");
		maakSteenFocusListener(zwart14);
		maakSteenMouseListener(zwart14, 2, 7);
		maakSteenLayout(zwart14, "/black circle.png", 534, 155, 2, 7);

		final JLabel zwart15 = new JLabel("");
		maakSteenFocusListener(zwart15);
		maakSteenMouseListener(zwart15, 2, 9);
		maakSteenLayout(zwart15, "/black circle.png", 688, 155, 2, 9);

		final JLabel zwart16 = new JLabel("");
		maakSteenFocusListener(zwart16);
		maakSteenMouseListener(zwart16, 3, 0);
		maakSteenLayout(zwart16, "/black circle.png", 5, 230, 3, 0);

		final JLabel zwart17 = new JLabel("");
		maakSteenFocusListener(zwart17);
		maakSteenMouseListener(zwart17, 3, 2);
		maakSteenLayout(zwart17, "/black circle.png", 157, 230, 3, 2);

		final JLabel zwart18 = new JLabel("");
		maakSteenFocusListener(zwart18);
		maakSteenMouseListener(zwart18, 3, 4);
		maakSteenLayout(zwart18, "/black circle.png", 307, 230, 3, 4);

		final JLabel zwart19 = new JLabel("");
		maakSteenFocusListener(zwart19);
		maakSteenMouseListener(zwart19, 3, 6);
		maakSteenLayout(zwart19, "/black circle.png", 457, 230, 3, 6);

		final JLabel zwart20 = new JLabel("");
		maakSteenFocusListener(zwart20);
		maakSteenMouseListener(zwart20, 3, 8);
		maakSteenLayout(zwart20, "/black circle.png", 607, 230, 3, 8);

		final JLabel wit1 = new JLabel("");
		maakSteenFocusListener(wit1);
		maakSteenMouseListener(wit1, 6, 1);
		maakSteenLayout(wit1, "white circle.png", 83, 456, 6, 1);

		final JLabel wit2 = new JLabel("");
		maakSteenFocusListener(wit2);
		maakSteenMouseListener(wit2, 6, 3);
		maakSteenLayout(wit2, "/white circle.png", 232, 456, 6, 3);
		
		final JLabel wit3 = new JLabel("");
		maakSteenFocusListener(wit3);
		maakSteenMouseListener(wit3, 6, 5);
		maakSteenLayout(wit3, "/white circle.png", 380, 456, 6, 5);
		
		final JLabel wit4 = new JLabel("");
		maakSteenFocusListener(wit4);
		maakSteenMouseListener(wit4, 6, 7);
		maakSteenLayout(wit4, "/white circle.png", 534, 456, 6, 7);
		
		final JLabel wit5 = new JLabel("");
		maakSteenFocusListener(wit5);
		maakSteenMouseListener(wit5, 6, 9);
		maakSteenLayout(wit5, "/white circle.png", 688, 456, 6, 9);
		
		final JLabel wit6 = new JLabel("");
		maakSteenFocusListener(wit6);
		maakSteenMouseListener(wit6, 7, 0);
		maakSteenLayout(wit6, "/white circle.png", 10, 534, 7, 0);

		final JLabel wit7 = new JLabel("");
		maakSteenFocusListener(wit7);
		maakSteenMouseListener(wit7, 7, 2);
		maakSteenLayout(wit7, "/white circle.png", 157, 534, 7, 2);
			
		final JLabel wit8 = new JLabel("");
		maakSteenFocusListener(wit8);
		maakSteenMouseListener(wit8, 7, 4);
		maakSteenLayout(wit8, "/white circle.png", 307, 534, 7, 4);
		
		final JLabel wit9 = new JLabel("");
		maakSteenFocusListener(wit9);
		maakSteenMouseListener(wit9, 7, 6);
		maakSteenLayout(wit9, "/white circle.png", 457, 534, 7, 6);

		final JLabel wit10 = new JLabel("");
		maakSteenFocusListener(wit10);
		maakSteenMouseListener(wit10, 7, 8);
		maakSteenLayout(wit10, "/white circle.png", 607, 534, 7, 8);

		final JLabel wit11 = new JLabel("");
		maakSteenFocusListener(wit11);
		maakSteenMouseListener(wit11, 8, 1);
		maakSteenLayout(wit11, "/white circle.png", 83, 609, 8, 1);
		
		final JLabel wit12 = new JLabel("");
		maakSteenFocusListener(wit12);
		maakSteenMouseListener(wit12, 8, 3);
		maakSteenLayout(wit12, "/white circle.png", 232, 609, 8, 3);

		final JLabel wit13 = new JLabel("");
		maakSteenFocusListener(wit13);
		maakSteenMouseListener(wit13, 8, 5);
		maakSteenLayout(wit13, "/white circle.png", 380, 609, 8, 5);
		
		final JLabel wit14 = new JLabel("");
		maakSteenFocusListener(wit14);
		maakSteenMouseListener(wit14, 8, 7);
		maakSteenLayout(wit14, "/white circle.png", 534, 609, 8, 7);
		
		final JLabel wit15 = new JLabel("");
		maakSteenFocusListener(wit15);
		maakSteenMouseListener(wit15, 8, 9);
		maakSteenLayout(wit15, "/white circle.png", 688, 609, 8, 9);

		final JLabel wit16 = new JLabel("");
		maakSteenFocusListener(wit16);
		maakSteenMouseListener(wit16, 9, 0);
		maakSteenLayout(wit16, "/white circle.png", 10, 686, 9, 0);

		final JLabel wit17 = new JLabel("");
		maakSteenFocusListener(wit17);
		maakSteenMouseListener(wit17, 9, 2);
		maakSteenLayout(wit17, "/white circle.png", 157, 686, 9, 2);
		
		final JLabel wit18 = new JLabel("");
		maakSteenFocusListener(wit18);
		maakSteenMouseListener(wit18, 9, 4);
		maakSteenLayout(wit18, "/white circle.png", 307, 686, 9, 4);
	
		final JLabel wit19 = new JLabel("");
		maakSteenFocusListener(wit19);
		maakSteenMouseListener(wit19, 9, 6);
		maakSteenLayout(wit19, "/white circle.png", 457, 686, 9, 6);

		final JLabel wit20 = new JLabel("");
		maakSteenFocusListener(wit20);
		maakSteenMouseListener(wit20, 9, 8);
		maakSteenLayout(wit20, "/white circle.png", 607, 686, 9, 8);

		final JLabel leeg1 = new JLabel("");
		maakSteenFocusListener(leeg1);
		maakSteenLayout(leeg1, "", 83, 306, 4, 1);
		
		leeg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Dambord.bord.setGeselecteerd(4, 1)){
					leeg1.requestFocusInWindow();
				}
			}
		});
		
		final JLabel leeg2 = new JLabel("");
		maakSteenFocusListener(leeg2);
		maakSteenMouseListener(leeg2, 4, 3);
		maakSteenLayout(leeg2, "", 232, 306, 4, 3);
		
		final JLabel leeg3 = new JLabel("");
		maakSteenFocusListener(leeg3);
		maakSteenMouseListener(leeg3, 4, 5);
		maakSteenLayout(leeg3, "", 380, 306, 4, 5);
		
		final JLabel leeg4 = new JLabel("");
		maakSteenFocusListener(leeg4);
		maakSteenMouseListener(leeg4, 4, 7);
		maakSteenLayout(leeg4, "", 534, 306, 4, 7);

		final JLabel leeg5 = new JLabel("");
		maakSteenFocusListener(leeg5);
		maakSteenMouseListener(leeg5, 4, 9);
		maakSteenLayout(leeg5, "", 688, 306, 4, 9);

		final JLabel leeg6 = new JLabel("");
		maakSteenFocusListener(leeg6);
		maakSteenMouseListener(leeg6, 5, 0);
		maakSteenLayout(leeg6, "", 10, 383, 5, 0);

		final JLabel leeg7 = new JLabel("");
		maakSteenFocusListener(leeg7);
		maakSteenMouseListener(leeg7, 5, 2);
		maakSteenLayout(leeg7, "", 157, 383, 5, 2);

		final JLabel leeg8 = new JLabel("");
		maakSteenFocusListener(leeg8);
		maakSteenMouseListener(leeg8, 5, 4);
		maakSteenLayout(leeg8, "", 307, 383, 5, 4);

		final JLabel leeg9 = new JLabel("");
		maakSteenFocusListener(leeg9);
		maakSteenMouseListener(leeg9, 5, 6);
		maakSteenLayout(leeg9, "", 457, 383, 5, 6);

		final JLabel leeg10 = new JLabel("");
		maakSteenFocusListener(leeg10);
		maakSteenMouseListener(leeg10, 5, 8);
		maakSteenLayout(leeg10, "", 607, 383, 5, 8);
		
		//statustext
		statusText = new JTextArea();
		statusText.setWrapStyleWord(true);
		statusText.setLineWrap(true);
		statusText.setEditable(false);
		statusText.setBounds(773, 466, 266, 57);
		contentPane.add(statusText);
		
		//movetext
		txtSchuiven = new JTextField();
		txtSchuiven.setEditable(false);
		txtSchuiven.setHorizontalAlignment(SwingConstants.CENTER);
		txtSchuiven.setText("Schuiven");
		txtSchuiven.setBounds(862, 241, 86, 20);
		contentPane.add(txtSchuiven);
		txtSchuiven.setColumns(10);
		
		//button to end turn
		JButton endTurn = new JButton("Beëindig beurt");
		endTurn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				testField = false;
				Dambord.bord.testFysiekBord(false);
				//Dambord.bord.beurtVoorbij();
				updatePanel();
				Dambord.bord.setKlaarVoorVerzending(true);
			}
		});
		
		JButton refreshBoard = new JButton("Check Stenen");
		refreshBoard.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent arg0){
				Dambord.bord.testFysiekBord(true);
				testField = !testField;
				if(testField){
					refreshBoard.setText("show huidig Board");
				}else{
					refreshBoard.setText("Check Stenen");
				}
				updatePanel();
			}
		});
		
		maakButtonLayout(endTurn, 920, 316, 100);
		maakButtonLayout(refreshBoard, 810, 316, 100);
		
		//buttons to move
		JButton schuifLinksboven = new JButton("Linksboven");
		maakButtonMouseListener(schuifLinksboven, "linksboven", "schuif");
		maakButtonLayout(schuifLinksboven, 773, 207, 89);
	
		JButton schuifRechtsboven = new JButton("Rechtsboven");
		maakButtonMouseListener(schuifRechtsboven, "rechtsboven", "schuif");
		maakButtonLayout(schuifRechtsboven, 950, 207, 99);

		JButton schuifLinksonder = new JButton("Linksonder");
		schuifLinksonder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		maakButtonMouseListener(schuifLinksonder, "linksonder", "schuif");
		maakButtonLayout(schuifLinksonder, 773, 273, 89);
		
		JButton schuifRechtsonder = new JButton("Rechtsonder");
		maakButtonMouseListener(schuifRechtsonder, "rechtsonder", "schuif");
		maakButtonLayout(schuifRechtsonder, 950, 273, 99);
		
		//capturetext
		txtSlaan = new JTextField();
		txtSlaan.setEditable(false);
		txtSlaan.setHorizontalAlignment(SwingConstants.CENTER);
		txtSlaan.setText("Slaan");
		txtSlaan.setBounds(862, 391, 86, 20);
		contentPane.add(txtSlaan);
		txtSlaan.setColumns(10);
		
		//buttons to capture
		JButton slaLinksboven = new JButton("Linksboven");
		maakButtonMouseListener(slaLinksboven, "linksboven", "sla");
		maakButtonLayout(slaLinksboven, 773, 358, 89);

		JButton slaLinksonder = new JButton("Linksonder");
		maakButtonMouseListener(slaLinksonder, "linksonder", "sla");
		maakButtonLayout(slaLinksonder, 773, 420, 89);

		JButton slaRechtsboven = new JButton("Rechtsboven");
		maakButtonMouseListener(slaRechtsboven, "rechtsboven", "sla");
		maakButtonLayout(slaRechtsboven, 950, 358, 99);
		
		JButton slaRechtsonder = new JButton("Rechtsonder");
		maakButtonMouseListener(slaRechtsonder, "rechtsonder", "sla");
		maakButtonLayout(slaRechtsonder, 950, 422, 99);

		//background image
		JLabel dambord = new JLabel("");
		dambord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dambord.setBounds(5, 0, 758, 761);
		dambord.setIcon(new ImageIcon(GUI.class.getResource("/checkers-board.jpg")));
		contentPane.add(dambord);
		
		//move history
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(773, 553, 266, 120);
		contentPane.add(scrollPane);
		
		geschiedenisTekst = new JTextArea();
		geschiedenisTekst.setFont(new Font("Monospaced", Font.PLAIN, 12));
		geschiedenisTekst.setEditable(false);
		geschiedenisTekst.setWrapStyleWord(true);
		geschiedenisTekst.setLineWrap(true);
		scrollPane.setViewportView(geschiedenisTekst);
		
		//text to show who's turn it is.
		aanDeBeurtTekst = new JTextField();
		aanDeBeurtTekst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aanDeBeurtTekst.setHorizontalAlignment(SwingConstants.CENTER);
		aanDeBeurtTekst.setText(Dambord.bord.getSpelerBeurtString());
		aanDeBeurtTekst.setEditable(false);
		aanDeBeurtTekst.setBounds(804, 148, 204, 30);
		contentPane.add(aanDeBeurtTekst);
		aanDeBeurtTekst.setColumns(10);
		
		scoreLabel = new JLabel(Dambord.bord.getScore());
		scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		scoreLabel.setBounds(812, 718, 200, 37);
		contentPane.add(scoreLabel);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblScore.setBounds(884, 684, 46, 14);
		contentPane.add(lblScore);
		
		JLabel scoreLabel2 = new JLabel("Wit                                Zwart");
		scoreLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
		scoreLabel2.setBounds(814, 699, 194, 14);
		contentPane.add(scoreLabel2);
	}
	
	//updates the interface
	public void updatePanel(){
		scoreLabel.setText(Dambord.bord.getScore());
		aanDeBeurtTekst.setText(Dambord.bord.getBeurt());
		for(int i = 0; i <= 9; i++){
			for(int j = 0; j <= 9; j++){
				if(stenen[i][j] != null){
					int kleur = Dambord.bord.getSoortSteen(i, j,testField);
					switch(kleur){
						case 0: stenen[i][j].setIcon(null);
								break;
						case 1: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
								break;
						case 2: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
								break;
						case 3: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/black king.png")));
						break;
						case 4: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/white king.png")));
						break;
						default: stenen[i][j].setIcon(null);
						break;
					}
				}
			}
		}
		Dambord.bord.makeAIMove();
		if(Dambord.bord.isAIDone()){
			geschiedenisTekst.append(Dambord.bord.getAIMove());
			Dambord.bord.setAIDone(false);
		}
		if(Dambord.bord.getAantalWitteStenen() == 0 || Dambord.bord.getAantalZwarteStenen() == 0){
			gameFinished = true;
		}
	}
	
	//Focuses the icon that is clicked on.
	public void getFocusIcon(JLabel label){
		for(int i = 0; i <= 9; i++){
			for(int j = 0; j <= 9; j++){
				if(stenen[i][j] == label){
					int kleur = Dambord.bord.getSoortSteen(i, j,testField);
					switch(kleur){
						case 0: stenen[i][j].setIcon(null);
								break;
						case 1: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/black circle focused.png")));
								break;
						case 2: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/white circle focused.png")));
								break;
						case 3: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/black king focused.png")));
						break;
						case 4: stenen[i][j].setIcon(new ImageIcon(GUI.class.getResource("/white king focused.png")));
						break;
						default: stenen[i][j].setIcon(null);
						break;
					}
				}
			}
		}
	}
	
	//helper method for focus listeners for pieces
	private void maakSteenFocusListener(final JLabel steen){
		steen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(steen);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
	}
	
	//helper method for mouse listeners for pieces
	private void maakSteenMouseListener(final JLabel steen, final int posX, final int posY){
		steen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Dambord.bord.setGeselecteerd(posX, posY)){
					steen.requestFocusInWindow();
				}
			}
		});		
	}
	
	//helper method for mouse listeners for buttons
	private void maakButtonMouseListener(JButton button, final String richting, final String soort){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (soort == "schuif"){
					if(Dambord.bord.schuif(richting)){
						geschiedenisTekst.append("Steen " + Dambord.bord.getVeldNummer() + " schoof naar " + Dambord.bord.getVeldNummer(richting) + ".\n");
						updatePanel();
					}
					else{
						statusText.setText(Dambord.bord.getFoutmelding());
					}
				}
				else if (soort == "sla"){
					if(Dambord.bord.sla(richting)){
						geschiedenisTekst.append("Steen " + Dambord.bord.getVeldNummer() + " sloeg " + Dambord.bord.getVeldNummer(richting) + " en belandde op " + Dambord.bord.getVeldNummerNaSlaan(richting) +".\n");
						updatePanel();
					}
					else{
						statusText.setText(Dambord.bord.getFoutmelding());
					}
				}
			}
		});
	}
	
	//helper method for layout of the pieces
	private void maakSteenLayout(JLabel steen, String picture, int size1, int size2, int posX, int posY){
		if (picture != null){
			steen.setIcon(new ImageIcon(GUI.class.getResource(picture)));
		}
		steen.setHorizontalAlignment(SwingConstants.CENTER);
		steen.setBounds(size1, size2, 75, 75);
		stenen[posX][posY] = steen;
		contentPane.add(steen);
	}
	
	//helper method for layout of the buttons
	private void maakButtonLayout(JButton button, int size1, int size2, int size3){
		button.setBounds(size1, size2, size3, 23);
		contentPane.add(button);
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
	}
	
	public boolean gameFinished(){
		return gameFinished;
	}
}