import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class PointAndScore {

    int score;
    Point point;

    PointAndScore(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

class Board {
 
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[3][3];

    public Board() {
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    } 
    
    Point computersMove; 
    
    public int minimax(int depth, int turn) {  
        if (hasXWon()) return -1; 
        if (hasOWon()) return +1;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0; 
 
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
         
        for (int i = 0; i < pointsAvailable.size(); ++i) {  
            Point point = pointsAvailable.get(i);   
            if (turn == 1) { 
                placeAMove(point, 1); 
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);
                if(currentScore >= 0){ if(depth == 0) computersMove = point;} 
                if(currentScore == 1){board[point.x][point.y] = 0; break;} 
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } else if (turn == 2) {
                placeAMove(point, 2); 
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min); 
                if(min == -1){board[point.x][point.y] = 0; break;}
            }
            board[point.x][point.y] = 0;
        } 
        return turn == 1?max:min;
    }  
}

//---------------------------------------------------------------------------------------------------------
public class TicTacToe implements ActionListener {
	private JButton[][] facelet = new JButton[3][3];
	private char[][] grid= {
			{'.','.','.'},
			{'.','.','.'},
			{'.','.','.'}
	};
	private boolean[][] has= {
			{false,false,false},
			{false,false,false},
			{false,false,false}
	};
	private static JFrame frame;
	private int counter=0;
	private static JPanel panel;
	private String which="",remem;
	private char mode='h';
	private JButton x, o,n,easy,med,hard;
	Board b = new Board();
    Random rand = new Random();
    public boolean if_Iwon(){
    	if(grid[0][0]==grid[1][0]&&grid[2][0]==grid[0][0]&&grid[0][0]==which.charAt(0))return true;
    	if(grid[0][1]==grid[1][1]&&grid[2][1]==grid[0][1]&&grid[0][1]==which.charAt(0))return true;
    	if(grid[0][2]==grid[1][2]&&grid[2][2]==grid[0][2]&&grid[0][2]==which.charAt(0))return true;
    	if(grid[0][0]==grid[0][1]&&grid[0][1]==grid[0][2]&&grid[0][2]==which.charAt(0))return true;
    	if(grid[1][0]==grid[1][1]&&grid[1][1]==grid[1][2]&&grid[1][2]==which.charAt(0))return true;
    	if(grid[2][0]==grid[2][1]&&grid[2][1]==grid[2][2]&&grid[2][2]==which.charAt(0))return true;
    	if(grid[0][0]==grid[1][1]&&grid[1][1]==grid[2][2]&&grid[2][2]==which.charAt(0))return true;
    	if(grid[0][2]==grid[1][1]&&grid[1][1]==grid[2][0]&&grid[0][2]==which.charAt(0))return true;
    	return false;
    }
    public boolean if_Ywon() {
    	char c='O';
    	if(which=="O") c='X';
    	if(grid[0][0]==grid[1][0]&&grid[2][0]==grid[0][0]&&grid[0][0]==c)return true;
    	if(grid[0][1]==grid[1][1]&&grid[2][1]==grid[0][1]&&grid[0][1]==c)return true;
    	if(grid[0][2]==grid[1][2]&&grid[2][2]==grid[0][2]&&grid[0][2]==c)return true;
    	if(grid[0][0]==grid[0][1]&&grid[0][1]==grid[0][2]&&grid[0][2]==c)return true;
    	if(grid[1][0]==grid[1][1]&&grid[1][1]==grid[1][2]&&grid[1][2]==c)return true;
    	if(grid[2][0]==grid[2][1]&&grid[2][1]==grid[2][2]&&grid[2][2]==c)return true;
    	if(grid[0][0]==grid[1][1]&&grid[1][1]==grid[2][2]&&grid[2][2]==c)return true;
    	if(grid[0][2]==grid[1][1]&&grid[1][1]==grid[2][0]&&grid[0][2]==c)return true;
    	return false;
    }
    public boolean if_tie() {
    	for(int i=0;i<3;i++) {
    		for(int j=0;j<3;j++) {
    			if(grid[i][j]=='.')return false;
    		}
    	}
    	return true;
    }
	public TicTacToe() {
		frame = new JFrame("TIC TAC TOE");
		frame.setBounds(200, 100, 240, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.add(panel);
		
		x=new JButton("X");
		x.setBounds(10,10,40,20);
		x.addActionListener(this);
		panel.add(x);
		
		n=new JButton("New");
		n.setBounds(100,10,40,20);
		n.addActionListener(this);
		panel.add(n);
		
		o=new JButton("O");
		o.setBounds(190,10,40,20);
		o.addActionListener(this);
		panel.add(o);
		easy=new JButton("EASY");
		easy.setBounds(10,50,60,20);
		easy.addActionListener(this);
		panel.add(easy);
		
		med=new JButton("MEDIUM");
		med.setBounds(90,50,60,20);
		med.addActionListener(this);
		panel.add(med);
		
		hard=new JButton("HARD");
		hard.setBounds(170,50,60,20);
		hard.addActionListener(this);
		panel.add(hard);
		for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                facelet[i][j] = new JButton();
                facelet[i][j].setBackground(Color.gray);
                facelet[i][j].setRolloverEnabled(false);
                facelet[i][j].setOpaque(true);
                facelet[i][j].setBounds(50*j + 45,50 * i + 100, 50, 50);
                panel.add(facelet[i][j]);
                facelet[i][j].addActionListener(this);
            }
		}
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new TicTacToe();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==x) {
			which="X";
			remem=which;
		}
		else if(e.getSource()==o) {
			which="O";
			remem=which;
			Point p = new Point(rand.nextInt(3), rand.nextInt(3));
            b.placeAMove(p, 1);
            facelet[p.x][p.y].setText("X");
            grid[p.x][p.y]='X';
            has[p.x][p.y]=true;
		}
		else if(e.getSource()==easy) mode='e';
		else if(e.getSource()==med) mode='m';
		else if(e.getSource()==hard) mode='h';
		else if(e.getSource()==n) {
			frame.setTitle("TIC TAC TOE");
			counter=0;
			which=remem;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					facelet[i][j].setText("");
					grid[i][j]='.';
					has[i][j]=false;
					b = new Board();
					rand = new Random();
				}
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(e.getSource()==facelet[i][j]) {
					if(which.length()==1&&mode=='h') {
						if(has[i][j]) return;
						has[i][j]=true;
						facelet[i][j].setText(which);
						grid[i][j]=which.charAt(0);
						Point userMove = new Point(i, j);
						b.placeAMove(userMove, 2);
						if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
						b.minimax(0, 1);
						b.placeAMove(b.computersMove, 1);
						if(which=="X") {
							facelet[b.computersMove.x][b.computersMove.y].setText("O");
							grid[b.computersMove.x][b.computersMove.y]='O';
						}
						else {
							facelet[b.computersMove.x][b.computersMove.y].setText("X");
							grid[b.computersMove.x][b.computersMove.y]='X';
						}
						has[b.computersMove.x][b.computersMove.y]=true;
						if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
					}
					else if(which.length()==1&&mode=='m') {
						if(has[i][j]) return;
						has[i][j]=true;
						facelet[i][j].setText(which);
						grid[i][j]=which.charAt(0);
						Point userMove = new Point(i, j);
						b.placeAMove(userMove, 2);
						if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
						b.minimax(0, 1);
						if(Math.random()*4<3) {
							b.placeAMove(b.computersMove, 1);
							if(which=="X") {
								facelet[b.computersMove.x][b.computersMove.y].setText("O");
								grid[b.computersMove.x][b.computersMove.y]='O';
							}
							else {
								facelet[b.computersMove.x][b.computersMove.y].setText("X");
								grid[b.computersMove.x][b.computersMove.y]='X';
							}
							has[b.computersMove.x][b.computersMove.y]=true;
						}
						else {
							Point p= new Point(rand.nextInt(3), rand.nextInt(3));
							while(has[p.x][p.y]) p= new Point(rand.nextInt(3), rand.nextInt(3));
				            b.placeAMove(p, 1);
				            if(which=="X") {
				            	facelet[p.x][p.y].setText("O");
					            grid[p.x][p.y]='O';
				            }
				            else {
					            facelet[p.x][p.y].setText("X");
					            grid[p.x][p.y]='X';
				            }
				            has[p.x][p.y]=true;
						}
						counter++;
						if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
					}
					else if(which.length()==1&&mode=='e') {
						if(has[i][j]) return;
						has[i][j]=true;
						facelet[i][j].setText(which);
						grid[i][j]=which.charAt(0);
						Point userMove = new Point(i, j);
						b.placeAMove(userMove, 2);
						if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
						b.minimax(0, 1);
						Point p= new Point(rand.nextInt(3), rand.nextInt(3));
						while(has[p.x][p.y]) p= new Point(rand.nextInt(3), rand.nextInt(3));
				           b.placeAMove(p, 1);
				           if(which=="X") {
				           	facelet[p.x][p.y].setText("O");
				            grid[p.x][p.y]='O';
			            }
			            else {
				            facelet[p.x][p.y].setText("X");
				            grid[p.x][p.y]='X';
			            }
			            has[p.x][p.y]=true;
			            if(if_Ywon()) {
							which="";
							frame.setTitle("LOSE");
							break;
						}
						else if(if_Iwon()) {
							which="";
							frame.setTitle("WIN");
							break;
						}
						else if(if_tie()) {
							which="";
							frame.setTitle("TIE");
							break;
						}
					}
				}
			}
		}
	}
}
