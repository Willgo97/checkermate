import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.JButton;


public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Dambord bord = new Dambord();
	private JPanel contentPane;
	private JTextField txtSchuiven;
	private JTextField txtSlaan;
	private JLabel[][] stenen = new JLabel[10][10];

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 //Create the frame.
	public GUI() {
		setResizable(false);
		setTitle("CheckerMate dammen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ######## TEXT AREA ########
		
		final JTextArea infoText = new JTextArea();
		infoText.setFont(new Font("Arial", Font.PLAIN, 15));
		infoText.setEditable(false);
		infoText.setWrapStyleWord(true);
		infoText.setLineWrap(true);
		infoText.setText("Welkom bij dammen met CheckerMate! Klik eerst op een damschijf die u wilt gebruiken en maak daarna uw zet door op de knoppen hieronder te klikken.");
		infoText.setBounds(773, 11, 256, 107);
		contentPane.add(infoText);
		
		//  ######### LABELS #########
		
		
		final JLabel zwart1 = new JLabel("");	
		maakFocusListener(zwart1);
		maakMouseListener(zwart1, 0, 1);
		maakLayout(zwart1, "/black circle.png", 83, 6, 0, 1);

		final JLabel zwart2 = new JLabel("");
		maakFocusListener(zwart2);
		maakMouseListener(zwart2, 0, 3);
		maakLayout(zwart2, "/black circle.png", 232, 6, 0, 3);
		
		final JLabel zwart3 = new JLabel("");
		maakFocusListener(zwart3);
		maakMouseListener(zwart3, 0, 5);
		maakLayout(zwart3, "/black circle.png", 380, 6, 0, 5);

		final JLabel zwart4 = new JLabel("");
		maakFocusListener(zwart4);
		maakMouseListener(zwart4, 0, 7);
		maakLayout(zwart4, "/black circle.png", 534, 6, 0, 7);

		final JLabel zwart5 = new JLabel("");
		maakFocusListener(zwart5);
		maakMouseListener(zwart5, 0, 9);
		maakLayout(zwart5, "/black circle.png", 688, 6, 0, 9);

		final JLabel zwart6 = new JLabel("");
		maakFocusListener(zwart6);
		maakMouseListener(zwart6, 1, 0);
		maakLayout(zwart6, "/black circle.png", 5, 81, 1, 0);

		final JLabel zwart7 = new JLabel("");
		maakFocusListener(zwart7);
		maakMouseListener(zwart7, 1, 2);
		maakLayout(zwart7, "/black circle.png", 157, 81, 1, 2);

		final JLabel zwart8 = new JLabel("");
		maakFocusListener(zwart8);
		maakMouseListener(zwart8, 1, 4);
		maakLayout(zwart8, "/black circle.png", 307, 81, 1, 4);

		final JLabel zwart9 = new JLabel("");
		maakFocusListener(zwart9);
		maakMouseListener(zwart9, 1, 6);
		maakLayout(zwart9, "/black circle.png", 457, 81, 1, 6);

		final JLabel zwart10 = new JLabel("");
		maakFocusListener(zwart10);
		maakMouseListener(zwart10, 1, 8);
		maakLayout(zwart10, "/black circle.png", 607, 81, 1, 8);

		final JLabel zwart11 = new JLabel("");
		maakFocusListener(zwart11);
		maakMouseListener(zwart11, 2, 1);
		maakLayout(zwart11, "/black circle.png", 83, 155, 2, 1);

		final JLabel zwart12 = new JLabel("");
		maakFocusListener(zwart12);
		maakMouseListener(zwart12, 2, 3);
		maakLayout(zwart12, "/black circle.png", 232, 155, 2, 3);

		final JLabel zwart13 = new JLabel("");
		maakFocusListener(zwart13);
		maakMouseListener(zwart13, 2, 5);
		maakLayout(zwart13, "/black circle.png", 380, 155, 2, 5);

		final JLabel zwart14 = new JLabel("");
		maakFocusListener(zwart14);
		maakMouseListener(zwart14, 2, 7);
		maakLayout(zwart14, "/black circle.png", 534, 155, 2, 7);

		final JLabel zwart15 = new JLabel("");
		maakFocusListener(zwart15);
		maakMouseListener(zwart15, 2, 9);
		maakLayout(zwart15, "/black circle.png", 688, 155, 2, 9);

		final JLabel zwart16 = new JLabel("");
		maakFocusListener(zwart16);
		maakMouseListener(zwart16, 3, 0);
		maakLayout(zwart16, "/black circle.png", 5, 230, 3, 0);

		final JLabel zwart17 = new JLabel("");
		maakFocusListener(zwart17);
		maakMouseListener(zwart17, 3, 2);
		maakLayout(zwart17, "/black circle.png", 157, 230, 3, 2);

		final JLabel zwart18 = new JLabel("");
		maakFocusListener(zwart18);
		maakMouseListener(zwart18, 3, 4);
		maakLayout(zwart18, "/black circle.png", 307, 230, 3, 4);

		final JLabel zwart19 = new JLabel("");
		maakFocusListener(zwart19);
		maakMouseListener(zwart19, 3, 6);
		maakLayout(zwart19, "/black circle.png", 457, 230, 3, 6);

		final JLabel zwart20 = new JLabel("");
		maakFocusListener(zwart20);
		maakMouseListener(zwart20, 3, 8);
		maakLayout(zwart20, "/black circle.png", 607, 230, 3, 8);

		final JLabel wit1 = new JLabel("");
		maakFocusListener(wit1);
		maakMouseListener(wit1, 6, 1);
		maakLayout(wit1, "white circle.png", 83, 456, 6, 1);

		final JLabel wit2 = new JLabel("");
		maakFocusListener(wit2);
		maakMouseListener(wit2, 6, 3);
		maakLayout(wit2, "/white circle.png", 232, 456, 6, 3);
		
		final JLabel wit3 = new JLabel("");
		maakFocusListener(wit3);
		maakMouseListener(wit3, 6, 5);
		maakLayout(wit3, "/white circle.png", 380, 456, 6, 5);
		
		final JLabel wit4 = new JLabel("");
		maakFocusListener(wit4);
		maakMouseListener(wit4, 6, 7);
		maakLayout(wit4, "/white circle.png", 534, 456, 6, 7);
		
		final JLabel wit5 = new JLabel("");
		maakFocusListener(wit5);
		maakMouseListener(wit5, 6, 9);
		maakLayout(wit5, "/white circle.png", 688, 456, 6, 9);
		
		final JLabel wit6 = new JLabel("");
		maakFocusListener(wit6);
		maakMouseListener(wit6, 7, 0);
		maakLayout(wit6, "/white circle.png", 10, 534, 7, 0);

		final JLabel wit7 = new JLabel("");
		maakFocusListener(wit7);
		maakMouseListener(wit7, 7, 2);
		maakLayout(wit7, "/white circle.png", 157, 534, 7, 2);
			
		final JLabel wit8 = new JLabel("");
		maakFocusListener(wit8);
		maakMouseListener(wit8, 7, 4);
		maakLayout(wit8, "/white circle.png", 307, 534, 7, 4);
		
		final JLabel wit9 = new JLabel("");
		maakFocusListener(wit9);
		maakMouseListener(wit9, 7, 6);
		maakLayout(wit9, "/white circle.png", 457, 534, 7, 6);

		final JLabel wit10 = new JLabel("");
		maakFocusListener(wit10);
		maakMouseListener(wit10, 7, 8);
		maakLayout(wit10, "/white circle.png", 607, 534, 7, 8);

		final JLabel wit11 = new JLabel("");
		maakFocusListener(wit11);
		maakMouseListener(wit11, 8, 1);
		maakLayout(wit11, "/white circle.png", 83, 609, 8, 1);
		
		final JLabel wit12 = new JLabel("");
		maakFocusListener(wit12);
		maakMouseListener(wit12, 8, 3);
		maakLayout(wit12, "/white circle.png", 232, 609, 8, 3);

		final JLabel wit13 = new JLabel("");
		maakFocusListener(wit13);
		maakMouseListener(wit13, 8, 5);
		maakLayout(wit13, "/white circle.png", 380, 609, 8, 5);
		
		final JLabel wit14 = new JLabel("");
		maakFocusListener(wit14);
		maakMouseListener(wit14, 8, 7);
		maakLayout(wit14, "/white circle.png", 534, 609, 8, 7);
		
		final JLabel wit15 = new JLabel("");
		maakFocusListener(wit15);
		maakMouseListener(wit15, 8, 9);
		maakLayout(wit15, "/white circle.png", 688, 609, 8, 9);

		final JLabel wit16 = new JLabel("");
		maakFocusListener(wit16);
		maakMouseListener(wit16, 9, 0);
		maakLayout(wit16, "/white circle.png", 10, 686, 9, 0);

		final JLabel wit17 = new JLabel("");
		maakFocusListener(wit17);
		maakMouseListener(wit17, 9, 2);
		maakLayout(wit17, "/white circle.png", 157, 686, 9, 2);
		
		final JLabel wit18 = new JLabel("");
		maakFocusListener(wit18);
		maakMouseListener(wit18, 9, 4);
		maakLayout(wit18, "/white circle.png", 307, 686, 9, 4);
	
		final JLabel wit19 = new JLabel("");
		maakFocusListener(wit19);
		maakMouseListener(wit19, 9, 6);
		maakLayout(wit19, "/white circle.png", 457, 686, 9, 6);

		final JLabel wit20 = new JLabel("");
		maakFocusListener(wit20);
		maakMouseListener(wit20, 9, 8);
		maakLayout(wit20, "/white circle.png", 607, 686, 9, 8);

		final JLabel leeg1 = new JLabel("");
		maakFocusListener(leeg1);
		maakLayout(leeg1, 83, 306, 4, 1);
		
		leeg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.setGeselecteerd(4, 1)){
					leeg1.requestFocusInWindow();
				}
			}
		});
		
		final JLabel leeg2 = new JLabel("");
		maakFocusListener(leeg2);
		maakMouseListener(leeg2, 4, 3);
		maakLayout(leeg2, 232, 306, 4, 3);
		
		final JLabel leeg3 = new JLabel("");
		maakFocusListener(leeg3);
		maakMouseListener(leeg3, 4, 5);
		maakLayout(leeg3, 380, 306, 4, 5);
		
		final JLabel leeg4 = new JLabel("");
		maakFocusListener(leeg4);
		maakMouseListener(leeg4, 4, 7);
		maakLayout(leeg4, 534, 306, 4, 7);

		final JLabel leeg5 = new JLabel("");
		maakFocusListener(leeg5);
		maakMouseListener(leeg5, 4, 9);
		maakLayout(leeg5, 688, 306, 4, 9);

		final JLabel leeg6 = new JLabel("");
		maakFocusListener(leeg6);
		maakMouseListener(leeg6, 5, 0);
		maakLayout(leeg6, 10, 383, 5, 0);

		final JLabel leeg7 = new JLabel("");
		maakFocusListener(leeg7);
		maakMouseListener(leeg7, 5, 2);
		maakLayout(leeg7, 157, 383, 5, 2);

		final JLabel leeg8 = new JLabel("");
		maakFocusListener(leeg8);
		maakMouseListener(leeg8, 5, 4);
		maakLayout(leeg8, 307, 383, 5, 4);

		final JLabel leeg9 = new JLabel("");
		maakFocusListener(leeg9);
		maakMouseListener(leeg9, 5, 6);
		maakLayout(leeg9, 457, 383, 5, 6);

		final JLabel leeg10 = new JLabel("");
		maakFocusListener(leeg10);
		maakMouseListener(leeg10, 5, 8);
		maakLayout(leeg10, 607, 383, 5, 8);

		final JTextArea statusText = new JTextArea();
		statusText.setWrapStyleWord(true);
		statusText.setLineWrap(true);
		statusText.setEditable(false);
		statusText.setBounds(773, 508, 266, 57);
		contentPane.add(statusText);
		
		txtSchuiven = new JTextField();
		txtSchuiven.setEditable(false);
		txtSchuiven.setHorizontalAlignment(SwingConstants.CENTER);
		txtSchuiven.setText("Schuiven");
		txtSchuiven.setBounds(862, 241, 86, 20);
		contentPane.add(txtSchuiven);
		txtSchuiven.setColumns(10);
		
		JButton schuifLinksboven = new JButton("Linksboven");
		schuifLinksboven.setFont(new Font("Tahoma", Font.PLAIN, 10));
		schuifLinksboven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.schuif("linksboven")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		schuifLinksboven.setBounds(773, 207, 89, 23);
		contentPane.add(schuifLinksboven);
		
		JButton schuifRechtsboven = new JButton("Rechtsboven");
		schuifRechtsboven.setFont(new Font("Tahoma", Font.PLAIN, 10));
		schuifRechtsboven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.schuif("rechtsboven")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		schuifRechtsboven.setBounds(950, 207, 99, 23);
		contentPane.add(schuifRechtsboven);
		
		JButton schuifLinksonder = new JButton("Linksonder");
		schuifLinksonder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		schuifLinksonder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.schuif("linksonder")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		schuifLinksonder.setBounds(773, 273, 89, 23);
		contentPane.add(schuifLinksonder);
		
		JButton schuifRechtsonder = new JButton("Rechtsonder");
		schuifRechtsonder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		schuifRechtsonder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.schuif("rechtsonder")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		schuifRechtsonder.setBounds(950, 273, 99, 23);
		contentPane.add(schuifRechtsonder);
		
		txtSlaan = new JTextField();
		txtSlaan.setEditable(false);
		txtSlaan.setHorizontalAlignment(SwingConstants.CENTER);
		txtSlaan.setText("Slaan");
		txtSlaan.setBounds(862, 391, 86, 20);
		contentPane.add(txtSlaan);
		txtSlaan.setColumns(10);
		
		JButton slaLinksboven = new JButton("Linksboven");
		slaLinksboven.setFont(new Font("Tahoma", Font.PLAIN, 10));
		slaLinksboven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.sla("linksboven")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		slaLinksboven.setBounds(773, 358, 89, 23);
		contentPane.add(slaLinksboven);
		
		JButton slaLinksonder = new JButton("Linksonder");
		slaLinksonder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		slaLinksonder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.sla("linksonder")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		slaLinksonder.setBounds(773, 420, 89, 23);
		contentPane.add(slaLinksonder);
		
		JButton slaRechtsboven = new JButton("Rechtsboven");
		slaRechtsboven.setFont(new Font("Tahoma", Font.PLAIN, 10));
		slaRechtsboven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.sla("rechtsboven")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		slaRechtsboven.setBounds(950, 358, 99, 23);
		contentPane.add(slaRechtsboven);
		
		JButton slaRechtsonder = new JButton("Rechtsonder");
		slaRechtsonder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		slaRechtsonder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.sla("rechtsonder")){
					updatePanel();
				}
				else{
					statusText.setText(bord.getFoutmelding());
				}
			}
		});
		slaRechtsonder.setBounds(950, 422, 99, 23);
		contentPane.add(slaRechtsonder);
		
		JLabel dambord = new JLabel("");
		dambord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dambord.setBounds(5, 0, 758, 761);
		dambord.setIcon(new ImageIcon(GUI.class.getResource("/checkers-board.jpg")));
		contentPane.add(dambord);
		
		JButton btnNewButton = new JButton("Draai bord");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.draaiBord();
				updatePanel();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(849, 609, 139, 63);
		contentPane.add(btnNewButton);
	}
	
	public void updatePanel(){
		for(int i = 0; i <= 9; i++){
			for(int j = 0; j <= 9; j++){
				if(stenen[i][j] != null){
					int kleur = bord.getSoortSteen(i, j);
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
	}
	
	public void getFocusIcon(JLabel label){
		for(int i = 0; i <= 9; i++){
			for(int j = 0; j <= 9; j++){
				if(stenen[i][j] == label){
					int kleur = bord.getSoortSteen(i, j);
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
	
	private void maakFocusListener(JLabel steen){
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
	
	private void maakMouseListener(JLabel steen, int posX, int posY){
		steen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.setGeselecteerd(posX, posY)){
					steen.requestFocusInWindow();
				}
			}
		});		
	}
	
	private void maakLayout(JLabel steen, String picture, int size1, int size2, int posX, int posY){
		steen.setIcon(new ImageIcon(GUI.class.getResource(picture)));
		steen.setHorizontalAlignment(SwingConstants.CENTER);
		steen.setBounds(size1, size2, 75, 75);
		stenen[posX][posY] = steen;
		contentPane.add(steen);
	}
	
	private void maakLayout(JLabel steen, int size1, int size2, int posX, int posY){
		steen.setHorizontalAlignment(SwingConstants.CENTER);
		steen.setBounds(size1, size2, 75, 75);
		stenen[posX][posY] = steen;
		contentPane.add(steen);
		
	}
}