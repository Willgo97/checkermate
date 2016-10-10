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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dambord bord = new Dambord();
	private JPanel contentPane;
	private JTextField txtSchuiven;
	private JTextField txtSlaan;
	private JLabel[][] stenen = new JLabel[10][10];

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		zwart1.setHorizontalAlignment(SwingConstants.CENTER);
		zwart1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.setGeselecteerd(0, 1)){
					zwart1.requestFocusInWindow();
				}
			}
		});
		zwart1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart1);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart1.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart1.setBounds(83, 6, 75, 75);
		stenen[0][1] = zwart1;
		contentPane.add(zwart1);
		
		final JLabel zwart2 = new JLabel("");
		zwart2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart2);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.setGeselecteerd(0, 3)){
					zwart2.requestFocusInWindow();
				}
			}
		});
		zwart2.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart2.setHorizontalAlignment(SwingConstants.CENTER);
		zwart2.setBounds(232, 6, 75, 75);
		stenen[0][3] = zwart2;
		contentPane.add(zwart2);
		
		final JLabel zwart3 = new JLabel("");
		zwart3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart3);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(0, 5);
				zwart3.requestFocusInWindow();
			}
		});
		zwart3.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart3.setHorizontalAlignment(SwingConstants.CENTER);
		zwart3.setBounds(380, 6, 75, 75);
		stenen[0][5] = zwart3;
		contentPane.add(zwart3);
		
		final JLabel zwart4 = new JLabel("");
		zwart4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart4);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(0, 7);
				zwart4.requestFocusInWindow();
			}
		});
		zwart4.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart4.setHorizontalAlignment(SwingConstants.CENTER);
		zwart4.setBounds(534, 6, 75, 75);
		stenen[0][7] = zwart4;
		contentPane.add(zwart4);
		
		final JLabel zwart5 = new JLabel("");
		zwart5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart5);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(0, 9);
				zwart5.requestFocusInWindow();
			}
		});
		zwart5.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart5.setHorizontalAlignment(SwingConstants.CENTER);
		zwart5.setBounds(688, 6, 75, 75);
		stenen[0][9] = zwart5;
		contentPane.add(zwart5);
		
		final JLabel zwart6 = new JLabel("");
		zwart6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart6);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(1, 0);
				zwart6.requestFocusInWindow();
			}
		});
		zwart6.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart6.setHorizontalAlignment(SwingConstants.CENTER);
		zwart6.setBounds(5, 81, 75, 75);
		stenen[1][0] = zwart6;
		contentPane.add(zwart6);
		
		final JLabel zwart7 = new JLabel("");
		zwart7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart7);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(1, 2);
				zwart7.requestFocusInWindow();
			}
		});
		zwart7.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart7.setHorizontalAlignment(SwingConstants.CENTER);
		zwart7.setBounds(157, 81, 75, 75);
		stenen[1][2] = zwart7;
		contentPane.add(zwart7);
		
		final JLabel zwart8 = new JLabel("");
		zwart8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart8);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(1, 4);
				zwart8.requestFocusInWindow();
			}
		});
		zwart8.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart8.setHorizontalAlignment(SwingConstants.CENTER);
		zwart8.setBounds(307, 81, 75, 75);
		stenen[1][4] = zwart8;
		contentPane.add(zwart8);
		
		final JLabel zwart9 = new JLabel("");
		zwart9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart9);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(1, 6);
				zwart9.requestFocusInWindow();
			}
		});
		zwart9.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart9.setHorizontalAlignment(SwingConstants.CENTER);
		zwart9.setBounds(457, 81, 75, 75);
		stenen[1][6] = zwart9;
		contentPane.add(zwart9);
		
		final JLabel zwart10 = new JLabel("");
		zwart10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart10);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(1, 8);
				zwart10.requestFocusInWindow();
			}
		});
		zwart10.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart10.setHorizontalAlignment(SwingConstants.CENTER);
		zwart10.setBounds(607, 81, 75, 75);
		stenen[1][8] = zwart10;
		contentPane.add(zwart10);
		
		final JLabel zwart11 = new JLabel("");
		zwart11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart11);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(2, 1);
				zwart11.requestFocusInWindow();
			}
		});
		zwart11.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart11.setHorizontalAlignment(SwingConstants.CENTER);
		zwart11.setBounds(83, 155, 75, 75);
		stenen[2][1] = zwart11;
		contentPane.add(zwart11);
		
		final JLabel zwart12 = new JLabel("");
		zwart12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart12);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(2, 3);
				zwart12.requestFocusInWindow();
			}
		});
		zwart12.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart12.setHorizontalAlignment(SwingConstants.CENTER);
		zwart12.setBounds(232, 155, 75, 75);
		stenen[2][3] = zwart12;
		contentPane.add(zwart12);
		
		final JLabel zwart13 = new JLabel("");
		zwart13.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart13);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(2, 5);
				zwart13.requestFocusInWindow();
			}
		});
		zwart13.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart13.setHorizontalAlignment(SwingConstants.CENTER);
		zwart13.setBounds(380, 155, 75, 75);
		stenen[2][5] = zwart13;
		contentPane.add(zwart13);
		
		final JLabel zwart14 = new JLabel("");
		zwart14.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart14);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(2, 7);
				zwart14.requestFocusInWindow();
			}
		});
		zwart14.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart14.setHorizontalAlignment(SwingConstants.CENTER);
		zwart14.setBounds(534, 155, 75, 75);
		stenen[2][7] = zwart14;
		contentPane.add(zwart14);
		
		final JLabel zwart15 = new JLabel("");
		zwart15.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart15);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(2, 9);
				zwart15.requestFocusInWindow();
			}
		});
		zwart15.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart15.setHorizontalAlignment(SwingConstants.CENTER);
		zwart15.setBounds(688, 155, 75, 75);
		stenen[2][9] = zwart15;
		contentPane.add(zwart15);
		
		final JLabel zwart16 = new JLabel("");
		zwart16.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart16);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(3, 0);
				zwart16.requestFocusInWindow();
			}
		});
		zwart16.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart16.setHorizontalAlignment(SwingConstants.CENTER);
		zwart16.setBounds(5, 230, 75, 75);
		stenen[3][0] = zwart16;
		contentPane.add(zwart16);
		
		final JLabel zwart17 = new JLabel("");
		zwart17.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart17);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(3, 2);
				zwart17.requestFocusInWindow();
			}
		});
		zwart17.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart17.setHorizontalAlignment(SwingConstants.CENTER);
		zwart17.setBounds(157, 230, 75, 75);
		stenen[3][2] = zwart17;
		contentPane.add(zwart17);
		
		final JLabel zwart18 = new JLabel("");
		zwart18.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart18);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(3, 4);
				zwart18.requestFocusInWindow();
			}
		});
		zwart18.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart18.setHorizontalAlignment(SwingConstants.CENTER);
		zwart18.setBounds(307, 230, 75, 75);
		stenen[3][4] = zwart18;
		contentPane.add(zwart18);
		
		final JLabel zwart19 = new JLabel("");
		zwart19.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart19);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(3, 6);
				zwart19.requestFocusInWindow();
			}
		});
		zwart19.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart19.setHorizontalAlignment(SwingConstants.CENTER);
		zwart19.setBounds(457, 230, 75, 75);
		stenen[3][6] = zwart19;
		contentPane.add(zwart19);
		
		final JLabel zwart20 = new JLabel("");
		zwart20.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(zwart20);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		zwart20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(3, 8);
				zwart20.requestFocusInWindow();
			}
		});
		zwart20.setIcon(new ImageIcon(GUI.class.getResource("/black circle.png")));
		zwart20.setHorizontalAlignment(SwingConstants.CENTER);
		zwart20.setBounds(607, 230, 75, 75);
		stenen[3][8] = zwart20;
		contentPane.add(zwart20);
		
		final JLabel wit1 = new JLabel("");
		wit1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit1);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(6, 1);
				wit1.requestFocusInWindow();
			}
		});
		wit1.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit1.setHorizontalAlignment(SwingConstants.CENTER);
		wit1.setBounds(83, 456, 75, 75);
		stenen[6][1] = wit1;
		contentPane.add(wit1);
		
		final JLabel wit2 = new JLabel("");
		wit2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit2);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit2.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(6, 3);
				wit2.requestFocusInWindow();
			}
		});
		wit2.setHorizontalAlignment(SwingConstants.CENTER);
		wit2.setBounds(232, 456, 75, 75);
		stenen[6][3] = wit2;
		contentPane.add(wit2);
		
		final JLabel wit3 = new JLabel("");
		wit3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit3);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit3.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(6, 5);
				wit3.requestFocusInWindow();
			}
		});
		wit3.setHorizontalAlignment(SwingConstants.CENTER);
		wit3.setBounds(380, 456, 75, 75);
		stenen[6][5] = wit3;
		contentPane.add(wit3);
		
		final JLabel wit4 = new JLabel("");
		wit4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit4);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit4.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(6, 7);
				wit4.requestFocusInWindow();
			}
		});
		wit4.setHorizontalAlignment(SwingConstants.CENTER);
		wit4.setBounds(534, 456, 75, 75);
		stenen[6][7] = wit4;
		contentPane.add(wit4);
		
		final JLabel wit5 = new JLabel("");
		wit5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit5);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit5.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(6, 9);
				wit5.requestFocusInWindow();
			}
		});
		wit5.setHorizontalAlignment(SwingConstants.CENTER);
		wit5.setBounds(688, 456, 75, 75);
		stenen[6][9] = wit5;
		contentPane.add(wit5);
		
		final JLabel wit6 = new JLabel("");
		wit6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit6);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit6.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(7, 0);
				wit6.requestFocusInWindow();
			}
		});
		wit6.setHorizontalAlignment(SwingConstants.CENTER);
		wit6.setBounds(10, 534, 75, 75);
		stenen[7][0] = wit6;
		contentPane.add(wit6);
		
		final JLabel wit7 = new JLabel("");
		wit7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit7);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit7.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(7, 2);
				wit7.requestFocusInWindow();
			}
		});
		wit7.setHorizontalAlignment(SwingConstants.CENTER);
		wit7.setBounds(157, 534, 75, 75);
		stenen[7][2] = wit7;
		contentPane.add(wit7);
		
		final JLabel wit8 = new JLabel("");
		wit8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit8);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit8.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(7, 4);
				wit8.requestFocusInWindow();
			}
		});
		wit8.setHorizontalAlignment(SwingConstants.CENTER);
		wit8.setBounds(307, 534, 75, 75);
		stenen[7][4] = wit8;
		contentPane.add(wit8);
		
		final JLabel wit9 = new JLabel("");
		wit9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit9);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit9.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(7, 6);
				wit9.requestFocusInWindow();
			}
		});
		wit9.setHorizontalAlignment(SwingConstants.CENTER);
		wit9.setBounds(457, 534, 75, 75);
		stenen[7][6] = wit9;
		contentPane.add(wit9);
		
		final JLabel wit10 = new JLabel("");
		wit10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit10);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit10.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(7, 8);
				wit10.requestFocusInWindow();
			}
		});
		wit10.setHorizontalAlignment(SwingConstants.CENTER);
		wit10.setBounds(607, 534, 75, 75);
		stenen[7][8] = wit10;
		contentPane.add(wit10);
		
		final JLabel wit11 = new JLabel("");
		wit11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit11);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit11.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(8, 1);
				wit11.requestFocusInWindow();
			}
		});
		wit11.setHorizontalAlignment(SwingConstants.CENTER);
		wit11.setBounds(83, 609, 75, 75);
		stenen[8][1] = wit11;
		contentPane.add(wit11);
		
		final JLabel wit12 = new JLabel("");
		wit12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit12);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit12.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(8, 3);
				wit12.requestFocusInWindow();
			}
		});
		wit12.setHorizontalAlignment(SwingConstants.CENTER);
		wit12.setBounds(232, 609, 75, 75);
		stenen[8][3] = wit12;
		contentPane.add(wit12);
		
		final JLabel wit13 = new JLabel("");
		wit13.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit13);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit13.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(8, 5);
				wit13.requestFocusInWindow();
			}
		});
		wit13.setHorizontalAlignment(SwingConstants.CENTER);
		wit13.setBounds(380, 609, 75, 75);
		stenen[8][5] = wit13;
		contentPane.add(wit13);
		
		final JLabel wit14 = new JLabel("");
		wit14.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit14);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit14.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(8, 7);
				wit14.requestFocusInWindow();
			}
		});
		wit14.setHorizontalAlignment(SwingConstants.CENTER);
		wit14.setBounds(534, 609, 75, 75);
		stenen[8][7] = wit14;
		contentPane.add(wit14);
		
		final JLabel wit15 = new JLabel("");
		wit15.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit15);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit15.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(8, 9);
				wit15.requestFocusInWindow();
			}
		});
		wit15.setHorizontalAlignment(SwingConstants.CENTER);
		wit15.setBounds(688, 609, 75, 75);
		stenen[8][9] = wit15;
		contentPane.add(wit15);
		
		final JLabel wit16 = new JLabel("");
		wit16.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit16);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit16.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(9, 0);
				wit16.requestFocusInWindow();
			}
		});
		wit16.setHorizontalAlignment(SwingConstants.CENTER);
		wit16.setBounds(10, 686, 75, 75);
		stenen[9][0] = wit16;
		contentPane.add(wit16);
		
		final JLabel wit17 = new JLabel("");
		wit17.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit17);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit17.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(9, 2);
				wit17.requestFocusInWindow();
			}
		});
		wit17.setHorizontalAlignment(SwingConstants.CENTER);
		wit17.setBounds(157, 686, 75, 75);
		stenen[9][2] = wit17;
		contentPane.add(wit17);
		
		final JLabel wit18 = new JLabel("");
		wit18.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit18);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit18.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(9, 4);
				wit18.requestFocusInWindow();
			}
		});
		wit18.setHorizontalAlignment(SwingConstants.CENTER);
		wit18.setBounds(307, 686, 75, 75);
		stenen[9][4] = wit18;
		contentPane.add(wit18);
		
		final JLabel wit19 = new JLabel("");
		wit19.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit19);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit19.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(9, 6);
				wit19.requestFocusInWindow();
			}
		});
		wit19.setHorizontalAlignment(SwingConstants.CENTER);
		wit19.setBounds(457, 686, 75, 75);
		stenen[9][6] = wit19;
		contentPane.add(wit19);
		
		final JLabel wit20 = new JLabel("");
		wit20.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(wit20);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		wit20.setIcon(new ImageIcon(GUI.class.getResource("/white circle.png")));
		wit20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(9, 8);
				wit20.requestFocusInWindow();
			}
		});
		wit20.setHorizontalAlignment(SwingConstants.CENTER);
		wit20.setBounds(607, 686, 75, 75);
		stenen[9][8] = wit20;
		contentPane.add(wit20);
		
		final JLabel leeg1 = new JLabel("");
		leeg1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg1);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(bord.setGeselecteerd(4, 1)){
					leeg1.requestFocusInWindow();
				}
			}
		});
		leeg1.setHorizontalAlignment(SwingConstants.CENTER);
		leeg1.setBounds(83, 306, 75, 75);
		stenen[4][1] = leeg1;
		contentPane.add(leeg1);
		
		final JLabel leeg2 = new JLabel("");
		leeg2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg2);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(4, 3);
				leeg2.requestFocusInWindow();
			}
		});
		leeg2.setHorizontalAlignment(SwingConstants.CENTER);
		leeg2.setBounds(232, 306, 75, 75);
		stenen[4][3] = leeg2;
		contentPane.add(leeg2);
		
		final JLabel leeg3 = new JLabel("");
		leeg3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg3);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(4, 5);
				leeg3.requestFocusInWindow();
			}
		});
		leeg3.setHorizontalAlignment(SwingConstants.CENTER);
		leeg3.setBounds(380, 306, 75, 75);
		stenen[4][5] = leeg3;
		contentPane.add(leeg3);
		
		final JLabel leeg4 = new JLabel("");
		leeg4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg4);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(4, 7);
				leeg4.requestFocusInWindow();
			}
		});
		leeg4.setHorizontalAlignment(SwingConstants.CENTER);
		leeg4.setBounds(534, 306, 75, 75);
		stenen[4][7] = leeg4;
		contentPane.add(leeg4);
		
		final JLabel leeg5 = new JLabel("");
		leeg5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg5);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(4, 9);
				leeg5.requestFocusInWindow();
			}
		});
		leeg5.setHorizontalAlignment(SwingConstants.CENTER);
		leeg5.setBounds(688, 306, 75, 75);
		stenen[4][9] = leeg5;
		contentPane.add(leeg5);
		
		final JLabel leeg6 = new JLabel("");
		leeg6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg6);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(5, 0);
				leeg6.requestFocusInWindow();
			}
		});
		leeg6.setHorizontalAlignment(SwingConstants.CENTER);
		leeg6.setBounds(10, 383, 75, 75);
		stenen[5][0] = leeg6;
		contentPane.add(leeg6);
		
		final JLabel leeg7 = new JLabel("");
		leeg7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg7);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(5, 2);
				leeg7.requestFocusInWindow();
			}
		});
		leeg7.setHorizontalAlignment(SwingConstants.CENTER);
		leeg7.setBounds(157, 383, 75, 75);
		stenen[5][2] = leeg7;
		contentPane.add(leeg7);
		
		final JLabel leeg8 = new JLabel("");
		leeg8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg8);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(5, 4);
				leeg8.requestFocusInWindow();
			}
		});
		leeg8.setHorizontalAlignment(SwingConstants.CENTER);
		leeg8.setBounds(307, 383, 75, 75);
		stenen[5][4] = leeg8;
		contentPane.add(leeg8);
		
		final JLabel leeg9 = new JLabel("");
		leeg9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg9);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(5, 6);
				leeg9.requestFocusInWindow();
			}
		});
		leeg9.setHorizontalAlignment(SwingConstants.CENTER);
		leeg9.setBounds(457, 383, 75, 75);
		stenen[5][6] = leeg9;
		contentPane.add(leeg9);
		
		final JLabel leeg10 = new JLabel("");
		leeg10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				getFocusIcon(leeg10);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				updatePanel();
			}
		});
		leeg10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				bord.setGeselecteerd(5, 8);
				leeg10.requestFocusInWindow();
			}
		});
		leeg10.setHorizontalAlignment(SwingConstants.CENTER);
		leeg10.setBounds(607, 383, 75, 75);
		stenen[5][8] = leeg10;
		contentPane.add(leeg10);
		
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
}