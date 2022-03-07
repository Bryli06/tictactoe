import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UTTT_GUI implements ActionListener{
	private static JFrame frame;
	private static JPanel panel;
	private JButton[][] facelet = new JButton[9][9];
	private JPanel reddown1,reddown2,redup1,redup2;
	public UTTT_GUI() {
		frame = new JFrame("Tic Tac Toe");
		frame.setBounds(200, 100, 550, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		reddown1 = new JPanel();
		reddown1.setBackground(Color.BLACK);
		reddown1.setBounds(202,100,1,458);
		reddown1.setLayout(null);
		frame.add(reddown1);
		reddown2 = new JPanel();
		reddown2.setBackground(Color.BLACK);
		reddown2.setBounds(355,100,1,458);
		reddown2.setLayout(null);
		frame.add(reddown2);
		redup1 = new JPanel();
		redup1.setBackground(Color.BLACK);
		redup1.setBounds(50,252,458,1);
		redup1.setLayout(null);
		frame.add(redup1);
		redup2 = new JPanel();
		redup2.setBackground(Color.BLACK);
		redup2.setBounds(50,405,458,1);
		redup2.setLayout(null);
		frame.add(redup2);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.add(panel);
		
		for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                facelet[i][j] = new JButton();
                facelet[i][j].setBackground(Color.BLACK);
                facelet[i][j].setRolloverEnabled(false);
                facelet[i][j].setOpaque(true);
                facelet[i][j].setBounds(51*j + 50,51 * i + 100, 50, 50);
                panel.add(facelet[i][j]);
                facelet[i][j].addActionListener(this);
            }
		}
		frame.setVisible(true); 
	}
	public static void main(String[] args) {
		new UTTT_GUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
