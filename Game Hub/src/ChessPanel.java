import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


//import GamePanel.MyKeyAdapter;

import javax.swing.AbstractAction;

/**
 * The purpose of this class it to create a chess panel, to add to a JFrame in order to create a game of chess
 * initialize threads to: 
 * check if the either king is dead
 * check if any castling can no longer be performed
 * color the tiles and pieces
 * @author Noah Bradley
 *
 */
public class ChessPanel extends JPanel implements ActionListener{	
	
//	declaring & initializing a 2d array for the tile values (key values) 
	private int[][] tileVals = {
			{ 4, 1, 0, 0, 0, 0, -1, -4 },
			{ 2, 1, 0, 0, 0, 0, -1, -2 },
			{ 3, 1, 0, 0, 0, 0, -1, -3 },
			{ 6, 1, 0, 0, 0, 0, -1, -6 },
			{ 5, 1, 0, 0, 0, 0, -1, -5 },
			{ 3, 1, 0, 0, 0, 0, -1, -3 },
			{ 2, 1, 0, 0, 0, 0, -1, -2 },
			{ 4, 1, 0, 0, 0, 0, -1, -4 },
	};
//	declaring, initializing, and adding actionListeners to each button tile 
	@SuppressWarnings("serial")
	private JButton A8 = new JButton(new AbstractAction(valToString(tileVals[0][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 7;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				if(castling) {
					castling = false;
					tileVals[col][row] = 0;
					A8.setText("");
					tileVals[1][7] = holderValArr[0];
					B8.setText(valToString(tileVals[1][7]));
					tileVals[2][7] = -4;
					C8.setText(valToString(tileVals[2][7]));
					myTurn = myTurn * -1;
					if(checkCheck()) {
						tileVals[col][row] = -4;
						A8.setText(valToString(tileVals[col][row]));
						tileVals[3][7] = -6;
						D8.setText(valToString(tileVals[3][7]));
						tileVals[1][7] = 0;
						B8.setText(valToString(tileVals[1][7]));
						tileVals[2][7] = 0;
						C8.setText(valToString(tileVals[2][7]));
					}
					else myTurn = myTurn * -1;
				}
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B8 = new JButton(new AbstractAction(valToString(tileVals[1][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 7;	
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton C8 = new JButton(new AbstractAction(valToString(tileVals[2][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 7;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton D8 = new JButton(new AbstractAction(valToString(tileVals[3][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 7;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton E8 = new JButton(new AbstractAction(valToString(tileVals[4][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 7;	
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();

			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton F8 = new JButton(new AbstractAction(valToString(tileVals[5][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 7;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton G8 = new JButton(new AbstractAction(valToString(tileVals[6][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 7;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton H8 = new JButton(new AbstractAction(valToString(tileVals[7][7])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 7;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H8.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H8.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H8.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				if(castling) {
					castling = false;
					tileVals[col][row] = 0;
					H8.setText("");
					tileVals[5][7] = holderValArr[0];
					F8.setText(valToString(tileVals[5][7]));
					tileVals[4][7] = -4;
					E8.setText(valToString(tileVals[4][7]));
					myTurn = myTurn * -1;
					if(checkCheck()) {
						tileVals[col][row] = -4;
						H8.setText(valToString(tileVals[col][row]));
						tileVals[3][7] = -6;
						D8.setText(valToString(tileVals[3][7]));
						tileVals[4][7] = 0;
						E8.setText(valToString(tileVals[4][7]));
						tileVals[5][7] = 0;
						F8.setText(valToString(tileVals[5][7]));
						tileVals[6][7] = 0;
						G8.setText(valToString(tileVals[6][7]));
					}
					else myTurn = myTurn * -1;
				}
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H8.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton A7 = new JButton(new AbstractAction(valToString(tileVals[0][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 6;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton B7 = new JButton(new AbstractAction(valToString(tileVals[1][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton C7 = new JButton(new AbstractAction(valToString(tileVals[2][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});		
	@SuppressWarnings("serial")
	private JButton D7 = new JButton(new AbstractAction(valToString(tileVals[3][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});		
	@SuppressWarnings("serial")
	private JButton E7 = new JButton(new AbstractAction(valToString(tileVals[4][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton F7 = new JButton(new AbstractAction(valToString(tileVals[5][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});		
	@SuppressWarnings("serial")
	private JButton G7 = new JButton(new AbstractAction(valToString(tileVals[6][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 6;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});		
	@SuppressWarnings("serial")
	private JButton H7 = new JButton(new AbstractAction(valToString(tileVals[7][6])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 6;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H7.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H7.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H7.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H7.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton A6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					A5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					A6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					B5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					B6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton C6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					C5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					C6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton D6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					D5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					D6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});		
	@SuppressWarnings("serial")
	private JButton E6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					E5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					E6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton F6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 5;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					F5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					F6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton G6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 5;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G6.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					G5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					G6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton H6 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 5;		
			//System.out.println("myTurn: " + myTurn);
			//System.out.println("ghost count: " + ghostCount);
			//System.out.println("just did a double jump: " + doubleJump);
			//System.out.println("ghost pawn location: " + "(" + ghostPawnArr[0] + ", " + ghostPawnArr[1] + ")");
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {	//checking if selecting a piece and that it is your turn
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H6.setText("");
			}
			else if(!first && checkMove(col, row)) {	//checking if !selecting and the move is valid
				checkCount = 0;
				H6.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				//System.out.println("myTurn: " + myTurn);
				//System.out.println("ghost count: " + ghostCount);
				//System.out.println("just did a double jump: " + doubleJump);
				//System.out.println("ghost pawn location: " + "(" + ghostPawnArr[0] + ", " + ghostPawnArr[1] + ")");
				//System.out.println("1 tile value: " + tileVals[col][row]);
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					//System.out.println("got inside if");
					H5.setText("");
					tileVals[col][row - 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
					//System.out.println("2 tile value: " + tileVals[col][row]);
				}
				if(checkCheck()) {	//checking if in check
					H6.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
					//System.out.println("3 tile value: " + tileVals[col][row]);
				}
				else {
				myTurn = myTurn * -1;
				}
				//System.out.println("4 tile value: " + tileVals[col][row]);
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {	//checking if replacing piece
				H6.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
				//System.out.println("5 tile value: " + tileVals[col][row]);
			}
			//System.out.println("6 tile value: " + tileVals[col][row]);
		}
	});	
	@SuppressWarnings("serial")
	private JButton A5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton C5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton D5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton E5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton F5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton G5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 4;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton H5 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 4;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H5.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H5.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H5.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H5.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton A4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton C4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton D4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton E4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 3;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton F4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton G4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton H4 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 3;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H4.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H4.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H4.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H4.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton A3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					A4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					A3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					B4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					B3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton C3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					C4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					C3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton D3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					D4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					D3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton E3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					E4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					E3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton F3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 2;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					F4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					F3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton G3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 2;		
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G3.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					G4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
				}
				if(checkCheck()) {
					G3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton H3 = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 2;	
			//System.out.println("myTurn: " + myTurn);
			//System.out.println("ghost count: " + ghostCount);
			//System.out.println("just did a double jump: " + doubleJump);
			//System.out.println("ghost pawn location: " + "(" + ghostPawnArr[0] + ", " + ghostPawnArr[1] + ")");
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {	//checking if selecting a piece and that it is your turn
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H3.setText("");
			}
			else if(!first && checkMove(col, row)) {	//checking if !selecting and the move is valid
				checkCount = 0;
				H3.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				//System.out.println("myTurn: " + myTurn);
				//System.out.println("ghost count: " + ghostCount);
				//System.out.println("just did a double jump: " + doubleJump);
				//System.out.println("ghost pawn location: " + "(" + ghostPawnArr[0] + ", " + ghostPawnArr[1] + ")");
				//System.out.println("1 tile value: " + tileVals[col][row]);
				if(doubleJump && col == ghostPawnArr[0] && row == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1) {	//checking for un passant
					//System.out.println("got inside if");
					H4.setText("");
					tileVals[col][row + 1] = 0;
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = holderValArr[0];
					doubleJump = false;
					ghostCount = 0;
					//System.out.println("2 tile value: " + tileVals[col][row]);
				}
				if(checkCheck()) {	//checking if in check
					H3.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
					//System.out.println("3 tile value: " + tileVals[col][row]);
				}
				else {
				myTurn = myTurn * -1;
				}
				//System.out.println("4 tile value: " + tileVals[col][row]);
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {	//checking if replacing piece
				H3.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
				lowerGhost();
				//System.out.println("5 tile value: " + tileVals[col][row]);
			}
			//System.out.println("6 tile value: " + tileVals[col][row]);
		}
	});		
	@SuppressWarnings("serial")
	private JButton A2 = new JButton(new AbstractAction(valToString(tileVals[0][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B2 = new JButton(new AbstractAction(valToString(tileVals[1][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton C2 = new JButton(new AbstractAction(valToString(tileVals[2][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton D2 = new JButton(new AbstractAction(valToString(tileVals[3][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton E2 = new JButton(new AbstractAction(valToString(tileVals[4][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton F2 = new JButton(new AbstractAction(valToString(tileVals[5][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton G2 = new JButton(new AbstractAction(valToString(tileVals[6][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton H2 = new JButton(new AbstractAction(valToString(tileVals[7][1])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 1;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H2.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H2.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H2.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H2.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton A1 = new JButton(new AbstractAction(valToString(tileVals[0][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = 0;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				A1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				A1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					A1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				if(castling) {
					castling = false;
					tileVals[col][row] = 0;
					A1.setText("");
					tileVals[1][0] = holderValArr[0];
					B1.setText(valToString(tileVals[1][0]));
					tileVals[2][0] = 4;
					C1.setText(valToString(tileVals[2][0]));
					myTurn = myTurn * -1;
					if(checkCheck()) {
						tileVals[col][row] = 4;
						A1.setText(valToString(tileVals[col][row]));
						tileVals[3][0] = 6;
						D1.setText(valToString(tileVals[3][0]));
						tileVals[1][0] = 0;
						B1.setText(valToString(tileVals[1][0]));
						tileVals[2][0] = 0;
						C1.setText(valToString(tileVals[2][0]));
					}
					else myTurn = myTurn * -1;
				}
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				A1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton B1 = new JButton(new AbstractAction(valToString(tileVals[1][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 1;
			int row = 0;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				B1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				B1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					B1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				B1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton C1 = new JButton(new AbstractAction(valToString(tileVals[2][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 2;
			int row = 0;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				C1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				C1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					C1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				C1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton D1 = new JButton(new AbstractAction(valToString(tileVals[3][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 3;
			int row = 0;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				D1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				D1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					D1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				D1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});	
	@SuppressWarnings("serial")
	private JButton E1 = new JButton(new AbstractAction(valToString(tileVals[4][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 4;
			int row = 0;	
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				E1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				E1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					E1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				E1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton F1 = new JButton(new AbstractAction(valToString(tileVals[5][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 5;
			int row = 0;			
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				F1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				F1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					F1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				F1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton G1 = new JButton(new AbstractAction(valToString(tileVals[6][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 6;
			int row = 0;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				G1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				G1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					G1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				G1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	@SuppressWarnings("serial")
	private JButton H1 = new JButton(new AbstractAction(valToString(tileVals[7][0])) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int col = 7;
			int row = 0;
			if(first && tileVals[col][row] != 0 && tileVals[col][row] * myTurn >= 1) {
				holderValArr[0] = tileVals[col][row];
				holderValArr[1] = col;
				holderValArr[2] = row;
				first = false;
				H1.setText("");
			}
			else if(!first && checkMove(col, row)) {
				checkCount = 0;
				H1.setText(valToString(holderValArr[0]));
				first = true;
				int holderVal = tileVals[col][row];
				tileVals[col][row] = holderValArr[0];
				tileVals[holderValArr[1]][holderValArr[2]] = 0;
				if(checkCheck()) {
					H1.setText(valToString(holderVal));
					tileVals[col][row] = holderVal;
					tileVals[holderValArr[1]][holderValArr[2]] = holderValArr[0];
					buttonArr[holderValArr[1]][holderValArr[2]].setText(valToString(holderValArr[0]));
				}
				else {
				myTurn = myTurn * -1;
				if(castling) {
					castling = false;
					tileVals[col][row] = 0;
					H1.setText("");
					tileVals[5][0] = holderValArr[0];
					F1.setText(valToString(tileVals[5][0]));
					tileVals[4][0] = 4;
					E1.setText(valToString(tileVals[4][0]));
					myTurn = myTurn * -1;
					if(checkCheck()) {
						tileVals[col][row] = 4;
						H1.setText(valToString(tileVals[col][row]));
						tileVals[3][0] = 6;
						D1.setText(valToString(tileVals[3][0]));
						tileVals[4][0] = 0;
						E1.setText(valToString(tileVals[4][0]));
						tileVals[5][0] = 0;
						F1.setText(valToString(tileVals[5][0]));
						tileVals[6][0] = 0;
						G1.setText(valToString(tileVals[6][0]));
					}
					else myTurn = myTurn * -1;
				}
				}
				doubleJump = false;
				if(ghostPawnArr[0] != -1) {
					tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = 0;
				}
			}
			else if(!first && col == holderValArr[1] && row == holderValArr[2]) {
				H1.setText(valToString(holderValArr[0]));
				first = true;
				checkForSurrender();
			}
		}
	});
	
	
/**
 * 	lowers the ghost count in the case of it raising from a replacement of a piece
 */
	public void lowerGhost() {
		if(ghostCount > 0) {
			ghostCount--;
			}
	}
//	declaring and initializing a 2d array for all the button tiles
	private JButton[][] buttonArr = {
				{ A1, A2, A3, A4, A5, A6, A7, A8 },
				{ B1, B2, B3, B4, B5, B6, B7, B8 },
				{ C1, C2, C3, C4, C5, C6, C7, C8 },
				{ D1, D2, D3, D4, D5, D6, D7, D8 },
				{ E1, E2, E3, E4, E5, E6, E7, E8 },
				{ F1, F2, F3, F4, F5, F6, F7, F8 },
				{ G1, G2, G3, G4, G5, G6, G7, G8 },
				{ H1, H2, H3, H4, H5, H6, H7, H8 },
		};
//	value for indicating turn (1 = p1 & -1 = p2)
	private int myTurn = 1;
//	key values for all piece varieties
	private static final int emptyVal = 0;
	private static final int pawnVal = 1;
	private static final int knightVal = 2;
	private static final int bishopVal = 3;
	private static final int rookVal = 4;
	private static final int queenVal = 5;
	private static final int kingVal = 6;
	private static final int evilPawnVal = -1;
	private static final int evilKnightVal = -2;
	private static final int evilBishopVal = -3;
	private static final int evilRookVal = -4;
	private static final int evilQueenVal = -5;
	private static final int evilKingVal = -6;
//	holder array where 0 is the moving pieces value, old x coordinate, and old y coordinate 
	private int[] holderValArr = {0, 0, 0};
//	array for x and y coordinates for the ghost pawn
	private int[] ghostPawnArr = {-1, 0};
//	value for the turn value at the time of the assignment of the ghost pawn coordinates
	private int ghostCount = 0;
//	value for counting repeated replaces during check to indicate surrender
	private int checkCount = 0;
//	value to indicate if one is selecting or moving a piece
	private boolean first = true;
//	value to indicate if a pawn just double jumped
	private boolean doubleJump = false;
//	value determining if its okay to castle the good king to the left based on if they have moved or not.
	private boolean okayToCastleL;
//	value determining if its okay to castle the good king to the right based on if they have moved or not.
	private boolean okayToCastleR;
//	value determining if its okay to castle the evil king to the left based on if they have moved or not.
	private boolean okayToCastleEL;
//	value determining if its okay to castle the evil king to the right based on if they have moved or not.
	private boolean okayToCastleER;
//	value determining if a castling move should be done now
	private boolean castling = false;
//	creating frame and panel of buttons for chess board
	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();
/**
 * 	returns the 2d int array for piece values
 * 	@return
 */
	private int[][] getTileVals() {
		return tileVals;
	}
/**
 * 	sets the value of a piece from the 2d array at a specified location
 * 	@param x - col
 * 	@param y - row
 * 	@param num for piece value at col & row
 */
	private void setTileVals(int x, int y, int num) {
		tileVals[x][y] = num;
	}
/**
 * 	sets the boolean value for if castling is okay
 * 	@param tempC
 */
	public void setCastle(boolean tempC, int key) {
		if(key == 1) {
			okayToCastleL = tempC;
		}
		else if(key == 2) {
			okayToCastleR = tempC;
		}
		else if(key == 3) {
			okayToCastleEL = tempC;
		}
		else if(key == 4) {
			okayToCastleER = tempC;
		}
	}
/**
 * 	returns the JFrame the chess board is added to
 * 	@return JFrame
 */
	public JFrame getFrame() {
		return frame;
	}
/**
 * 	returns the 2d button array full of tile buttons
 * 	@return 2d array of tile buttons
 */	
	public JButton[][] getButtonArr(){
		return buttonArr;
	}
/**	
 * 	loops through the board and returns the coordinates of the inputed key value
 * 	@param numKey
 * 	@return int[] for coords
 */
	private int[] getTileLoc(int numKey) {
		int[] coords = new int[2];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tileVals[i][j] == numKey) {
					coords[0] = i;
					coords[1] = j;
				}
			}
		}
		return coords;
	}
/**	
 * 	loops through the board backwards and returns the coordinates of the inputed key value
 * 	@param numKey
 * 	@return int[] for coords
 */	
	private int[] getTileLocBackwards(int numKey) {
		int[] coords = new int[2];
		for(int i = 7; i >= 0; i--) {
			for(int j = 7; j >= 0; j--) {
				if(tileVals[i][j] == numKey) {
					coords[0] = i;
					coords[1] = j;
				}
			}
		}
		return coords;
	}
/**
 * 	method that increases a counter when called and at 4 surrenders the current players king
 */
	private void checkForSurrender() {
		if(checkCheck()) {
			checkCount++;
		}
		if(checkCount >= 4) {
			tileVals[getTileLoc(myTurn * kingVal)[0]][getTileLoc(myTurn * kingVal)[1]] = 0;
		}
	}
/**
 * 	checks if the current players king is in check
 * 	@return boolean value for if the king is in check
 */
	private boolean checkCheck() {
		
		
		
		
		int xVal = getTileLoc(myTurn * 6)[0];
		int yVal = getTileLoc(myTurn * 6)[1];
		
		//System.out.println("X coordinate: " + xVal);
		//System.out.println("Y coordinate: " + yVal);
		
		//looking for a check to the right of the king
		int tempX = xVal;
		int tempY = yVal;
		int dist = 0;
		boolean empty = true;
		if(xVal != 7) {
			while(empty && tempX < 7) {
				tempX++;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}	
			}
		//System.out.println("Right Checking Coordinate: (" + tempX + ", " + tempY + ")");
		if(tileVals[tempX][tempY] == myTurn * evilRookVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		//looking for a check to the left of the king
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(xVal != 0) {
			while(empty && tempX > 0) {
				tempX--;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("Left Checking Coordinate: (" + tempX + ", " + tempY + ")");
		if(tileVals[tempX][tempY] == myTurn * evilRookVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		//looking for a check upwards of the king
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(yVal != 7) {
			while(empty && tempY < 7) {
				tempY++;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("Up Checking Coordinate: (" + tempX + ", " + tempY + ")");
		if(tileVals[tempX][tempY] == myTurn * evilRookVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		//looking for a check downwards of the king
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(yVal != 0) {
			while(empty && tempY > 0) {
				tempY--;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("Down Checking Coordinate: (" + tempX + ", " + tempY + ")");
		if(tileVals[tempX][tempY] == myTurn * evilRookVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		//looking to the upper right
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(xVal != 7 && yVal != 7) {
			while(empty && tempX < 7 && tempY < 7) {
				tempX++;
				tempY++;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("NE Checking Coordinate: (" + tempX + ", " + tempY + ")");	
		if(tileVals[tempX][tempY] == myTurn * evilBishopVal ) {
			return true;
		}
		else if( tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == evilPawnVal && dist == 1 && myTurn == 1) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		//looking to the upper left
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(xVal != 0 && yVal != 7) {
			while(empty && tempX > 0 && tempY < 7) {
				tempX--;
				tempY++;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("NW Checking Coordinate: (" + tempX + ", " + tempY + ")");	
		if(tileVals[tempX][tempY] == myTurn * evilBishopVal ) {
			return true;
		}
		else if( tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == evilPawnVal && dist == 1 && myTurn == 1) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
		
		
		//looking to the bottom right
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(xVal != 7 && yVal != 0) {
			while(empty && tempX < 7 && tempY > 0) {
				tempX++;
				tempY--;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("SE Checking Coordinate: (" + tempX + ", " + tempY + ")");	
		if(tileVals[tempX][tempY] == myTurn * evilBishopVal ) {
			return true;
		}
		else if( tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == pawnVal && dist == 1 && myTurn == -1) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
				
		//looking to the bottom left
		tempX = xVal;
		tempY = yVal;
		dist = 0;
		empty = true;
		if(xVal != 0 && yVal != 0) {
			while(empty && tempX > 0 && tempY > 0) {
				tempX--;
				tempY--;
				dist++;
				if(tileVals[tempX][tempY] != 0) {
					empty = false;
				}
			}
		//System.out.println("SW Checking Coordinate: (" + tempX + ", " + tempY + ")");	
		if(tileVals[tempX][tempY] == myTurn * evilBishopVal ) {
			return true;
		}
		else if( tileVals[tempX][tempY] == myTurn * evilQueenVal) {
			return true;
		}
		else if(tileVals[tempX][tempY] == pawnVal && dist == 1 && myTurn == -1) {
			return true;
		}
		else if(tileVals[tempX][tempY] == myTurn * evilKingVal && dist == 1) {
			return true;
		}
		}
				
		//looking for knights		
		int firstKnightX = getTileLoc(myTurn * -2)[0];
		int firstKnightY = getTileLoc(myTurn * -2)[1];
		int secondKnightX = getTileLocBackwards(myTurn * -2)[0];
		int secondKnightY = getTileLocBackwards(myTurn * -2)[1];
		
		//System.out.println("First Knight Coordinate: (" + firstKnightX + ", " + firstKnightY + ")");
		//System.out.println("Second Knight Coordinate: (" + secondKnightX + ", " + secondKnightY + ")");
		
		if(Math.abs(xVal - firstKnightX) == 2 && Math.abs(yVal - firstKnightY) == 1) {
			return true;
		}
		else if(Math.abs(xVal - firstKnightX) == 1 && Math.abs(yVal - firstKnightY) == 2) {
			return true;
		}
		else if(Math.abs(xVal - secondKnightX) == 2 && Math.abs(yVal - secondKnightY) == 1) {
			return true;
		}
		else if(Math.abs(xVal - secondKnightX) == 1 && Math.abs(yVal - secondKnightY) == 2) {
			return true;
		}
		
		//if nothing found at all
		return false;
	}
/**
 * 	checks if the selected tile can be moved to considering the selected piece
 * 	@param newX
 * 	@param newY
 * 	@return boolean value for if the tile is available
 */
	public boolean checkMove(int newX, int newY) {
		
		if(ghostCount == 1) {
			ghostCount = 0;
			doubleJump = false;
			//removeGhostPawn();
			//System.out.println("chance for french move passed");
		}
		
		if(doubleJump) {
			ghostCount++;
		}
		
		//check if its un passant
		if(newX == ghostPawnArr[0] && newY == ghostPawnArr[1] && Math.abs(holderValArr[0]) == 1 && Math.abs(newX - holderValArr[1]) == 1 && newY == holderValArr[2] + myTurn) {
			//System.out.println("(" + holderValArr[1] + ", " + holderValArr[2] + myTurn + ")");
			return true;
		}
		
		
		//pawn move set
		if(holderValArr[0] == pawnVal) {
			if(newX == holderValArr[1] && newY == holderValArr[2] + 1 && tileVals[newX][newY] == 0) {
				return true;
			}
			else if(newX == holderValArr[1] && newY == holderValArr[2] + 2 && tileVals[newX][newY] == 0 && tileVals[newX][newY - 1] == 0 && holderValArr[2] == 1) {
				ghostPawnArr[0] = holderValArr[1];
				ghostPawnArr[1] = holderValArr[2] + 1;
				//tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = 1;
				//System.out.println("ghost pawn updated");
				doubleJump = true;
				ghostCount = 0;
				return true;
			}
			else if(Math.abs(newX - holderValArr[1]) == 1 && newY == holderValArr[2] + 1 && tileVals[newX][newY] < 0) {
				return true;
			}
		}
		//knight move set
		else if(holderValArr[0] == 2) {
			if(((Math.abs(newX - holderValArr[1]) == 1 && Math.abs(newY - holderValArr[2]) == 2) || 
				(Math.abs(newX - holderValArr[1]) == 2 && Math.abs(newY - holderValArr[2]) == 1) )&& tileVals[newX][newY] <= 0) {
				return true;
			}
		}
		//bishop move set
		else if(holderValArr[0] == 3) {
			if(Math.abs(newX - holderValArr[1]) == Math.abs(newY - holderValArr[2]) && tileVals[newX][newY] <= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist != 0 && yDist != 0) {
				int xInc = (xDist)/(Math.abs(xDist));
				int yInc = (yDist)/(Math.abs(yDist));
				int count = 1;
				int xVal = 0;
				int yVal = 0;
				while(count < Math.abs(xDist)) {
					xVal = xVal + xInc;
					yVal = yVal + yInc;
					if(tileVals[newX - xVal][newY - yVal] != 0) {
						inTheWay = true;
					}
					count++;
				}
				}
				if(!inTheWay) {
					return true;
				}
			}
		}
		//rook move set
		else if(holderValArr[0] == 4) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) {
				return false;
			}
			if(newX - holderValArr[1] == 0 || newY - holderValArr[2] == 0 && tileVals[newX][newY] <= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist == 0) {
					int yInc = (yDist)/(Math.abs(yDist));
					int count = 1;
					int yVal = 0;
					while(count < Math.abs(yDist)) {
						yVal = yVal + yInc;
						if(tileVals[newX][newY - yVal] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				else {
					int xInc = (xDist)/(Math.abs(xDist));
					int count = 1;
					int xVal = 0;
					while(count < Math.abs(xDist)) {
						xVal = xVal + xInc;
						if(tileVals[newX - xVal][newY] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				if(!inTheWay) {
					return true;
				}
			}
		}
		//queen move set
		else if(holderValArr[0] == 5) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) { //replacing queen
				return false;
			}
			if(newX - holderValArr[1] == 0 || newY - holderValArr[2] == 0 && tileVals[newX][newY] <= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist == 0) {
					int yInc = (yDist)/(Math.abs(yDist));
					int count = 1;
					int yVal = 0;
					while(count < Math.abs(yDist)) {
						yVal = yVal + yInc;
						if(tileVals[newX][newY - yVal] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				else {
					int xInc = (xDist)/(Math.abs(xDist));
					int count = 1;
					int xVal = 0;
					while(count < Math.abs(xDist)) {
						xVal = xVal + xInc;
						if(tileVals[newX - xVal][newY] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				if(!inTheWay) {
					return true;
				}
			}
			else if(Math.abs(newX - holderValArr[1]) == Math.abs(newY - holderValArr[2]) && tileVals[newX][newY] <= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist != 0 && yDist != 0) {
				int xInc = (xDist)/(Math.abs(xDist));
				int yInc = (yDist)/(Math.abs(yDist));
				int count = 1;
				int xVal = 0;
				int yVal = 0;
				while(count < Math.abs(xDist)) {
					xVal = xVal + xInc;
					yVal = yVal + yInc;
					if(tileVals[newX - xVal][newY - yVal] != 0) {
						inTheWay = true;
					}
					count++;
				}
				}
				if(!inTheWay) {
					return true;
				}
			}
			
		}
		//king move set
		else if(holderValArr[0] == 6) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) {
				return false;
			}
			else if(Math.abs(newX - holderValArr[1]) <= 1 && Math.abs(newY - holderValArr[2]) <= 1 && tileVals[newX][newY] <= 0) {
				return true;
			}
			else if((newX == 7 || newX == 0) && newY == 0) {
				if(okayToCastleR && newX == 7 && tileVals[4][0] == 0 && tileVals[5][0] == 0 && tileVals[6][0] == 0 && tileVals[7][0] == 4) {
					castling = true;
					return true;
				}
				else if(okayToCastleL && newX == 0 && tileVals[2][0] == 0 && tileVals[1][0] == 0 && tileVals[0][0] == 4) {
					castling = true;
					return true;
				}
			}
		}
		//evil pawn move set
		else if(holderValArr[0] == evilPawnVal) {
			if(newX == holderValArr[1] && newY == holderValArr[2] - 1 && tileVals[newX][newY] == 0) {
				return true;
			}
			else if(newX == holderValArr[1] && newY == holderValArr[2] - 2 && tileVals[newX][newY] == 0 && tileVals[newX][newY + 1] == 0 && holderValArr[2] == 6) {
				ghostPawnArr[0] = holderValArr[1];
				ghostPawnArr[1] = holderValArr[2] - 1;
				//tileVals[ghostPawnArr[0]][ghostPawnArr[1]] = 1;
				//System.out.println("ghost pawn updated");
				doubleJump = true;
				ghostCount = 0;
				return true;
			}
			else if(Math.abs(newX - holderValArr[1]) == 1 && newY == holderValArr[2] - 1 && tileVals[newX][newY] > 0) {
				return true;
			}
		}
		//evil knight move set
		else if(holderValArr[0] == evilKnightVal) {
			if(((Math.abs(newX - holderValArr[1]) == 1 && Math.abs(newY - holderValArr[2]) == 2) || 
				(Math.abs(newX - holderValArr[1]) == 2 && Math.abs(newY - holderValArr[2]) == 1) )&& tileVals[newX][newY] >= 0) {
				return true;
			}
		}
		//evil bishop move set
		else if(holderValArr[0] == evilBishopVal) {
			if(Math.abs(newX - holderValArr[1]) == Math.abs(newY - holderValArr[2]) && tileVals[newX][newY] >= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist != 0 && yDist != 0) {
				int xInc = (xDist)/(Math.abs(xDist));
				int yInc = (yDist)/(Math.abs(yDist));
				int count = 1;
				int xVal = 0;
				int yVal = 0;
				while(count < Math.abs(xDist)) {
					xVal = xVal + xInc;
					yVal = yVal + yInc;
					if(tileVals[newX - xVal][newY - yVal] != 0) {
						inTheWay = true;
					}
					//System.out.println("xDist: " + xDist);
					//System.out.println("yDist: " + yDist);
					//System.out.println("This is how much x is shifting: " + xInc);
					//System.out.println("This is how much y is shifting: " + yInc);
					//System.out.println("Checking x index of: " + (newX - xVal));
					//System.out.println("Checking y index of: " + (newY - yVal));
					//xVal = xVal - xInc;
					//yVal = yVal - yInc;
					count++;
					//System.out.println("This is how much x is shifting: " + xInc);
					//System.out.println("This is how much y is shifting: " + yInc);
				}
				}
				if(!inTheWay) {
					return true;
				}
			}
		}
		//evil rook move set
		else if(holderValArr[0] == evilRookVal) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) {
				return false;
			}
			if(newX - holderValArr[1] == 0 || newY - holderValArr[2] == 0 && tileVals[newX][newY] >= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist == 0) {
					int yInc = (yDist)/(Math.abs(yDist));
					int count = 1;
					int yVal = 0;
					while(count < Math.abs(yDist)) {
						yVal = yVal + yInc;
						if(tileVals[newX][newY - yVal] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				else {
					int xInc = (xDist)/(Math.abs(xDist));
					int count = 1;
					int xVal = 0;
					while(count < Math.abs(xDist)) {
						xVal = xVal + xInc;
						if(tileVals[newX - xVal][newY] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				if(!inTheWay) {
					return true;
				}
			}
		}
		//evil queen move set
		else if(holderValArr[0] == evilQueenVal) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) {
				return false;
			}
			if(newX - holderValArr[1] == 0 || newY - holderValArr[2] == 0 && tileVals[newX][newY] >= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist == 0) {
					int yInc = (yDist)/(Math.abs(yDist));
					int count = 1;
					int yVal = 0;
					while(count < Math.abs(yDist)) {
						yVal = yVal + yInc;
						if(tileVals[newX][newY - yVal] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				else {
					int xInc = (xDist)/(Math.abs(xDist));
					int count = 1;
					int xVal = 0;
					while(count < Math.abs(xDist)) {
						xVal = xVal + xInc;
						if(tileVals[newX - xVal][newY] != 0) {
							inTheWay = true;
						}
						count++;
					}
				}
				if(!inTheWay) {
					return true;
				}
			}
			else if(Math.abs(newX - holderValArr[1]) == Math.abs(newY - holderValArr[2]) && tileVals[newX][newY] >= 0) {
				boolean inTheWay = false;
				int xDist = newX - holderValArr[1];
				int yDist = newY - holderValArr[2];
				if(xDist != 0 && yDist != 0) {
				int xInc = (xDist)/(Math.abs(xDist));
				int yInc = (yDist)/(Math.abs(yDist));
				int count = 1;
				int xVal = 0;
				int yVal = 0;
				while(count < Math.abs(xDist)) {
					xVal = xVal + xInc;
					yVal = yVal + yInc;
					if(tileVals[newX - xVal][newY - yVal] != 0) {
						inTheWay = true;
					}
					count++;
				}
				}
				if(!inTheWay) {
					return true;
				}
			}
			
		}
		//evil king move set
		else if(holderValArr[0] == evilKingVal) {
			if(newX - holderValArr[1] == 0 && newY - holderValArr[2] == 0) {
				return false;
			}
			else if(Math.abs(newX - holderValArr[1]) <= 1 && Math.abs(newY - holderValArr[2]) <= 1 && tileVals[newX][newY] >= 0) {
				return true;
			}
			else if((newX == 7 || newX == 0) && newY == 7) {
				//System.out.println(okayToCastleER);
				if(okayToCastleER && newX == 7 && tileVals[4][7] == 0 && tileVals[5][7] == 0 && tileVals[6][7] == 0 && tileVals[7][7] == -4) {
					castling = true;
					return true;
				}
				else if(okayToCastleEL && newX == 0 && tileVals[2][7] == 0 && tileVals[1][7] == 0 && tileVals[0][7] == -4) {
					castling = true;
					return true;
				}
			}
		}
		return false;
	}
/**
 * 	takes the key value for each piece and converts into its string element	
 * 	@param val
 * 	@return string value for each key value
 */
	public String valToString(int val) {
		if(val == emptyVal) {
			return "";
		}
		else if(val == pawnVal || val == evilPawnVal) {
			return "♙";
		}
		else if(val == knightVal || val == evilKnightVal) {
			return "♞";
		}
		else if(val == bishopVal || val == evilBishopVal) {
			return "♗";
		}
		else if(val == rookVal || val == evilRookVal) {
			return "♖";
		}
		else if(val == queenVal || val == evilQueenVal) {
			return "♛";
		}
		else return "♔";
	}
/**
 * 	constructor method for making a chess panel	
 */
	public ChessPanel() {
		
		
		
		//changing look and feel of UIManager (changes style)
		try{
			UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
			String lookAndFeelClassName = looks[2].getClassName();
			UIManager.setLookAndFeel(lookAndFeelClassName);	//UIManager.getCrossPlatformLookAndFeelClassName());
			}catch(Exception e){
			  e.printStackTrace(); 
			 }
		
			
		//configuring panel
		buttonPanel.setLayout(new GridLayout(8, 8, 0, 0));
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setVisible(true);
		
		//creating colors for button tiles
		Color darkTilePurp = new Color(116, 89, 126);			//darkPurple (116, 89, 126);			//(140, 70, 20);
		Color lightTilePurp = new Color(172, 153, 193).brighter();		//lightPurple (172, 153, 193);			//(153, 150, 165);			//(222, 184, 135);
		//Color darkTileBlack = new Color(5, 5, 5);
		//Color darkTilePink = new Color(219, 112, 147);
		//Color lightTilePink = new Color(255, 192, 203);
		//Color slateBlue = new Color(106, 90, 205);
		//Color chocolate = new Color(210, 105, 30);
		//Color azure = new Color(240, 255, 255);
		//Color cactus = new Color(23, 133, 18);
		//Color sand = new Color(248, 235, 214);
		
		//setting button font
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				buttonArr[i][j].setFont(new Font("Courier", Font.BOLD, 75));
			}
		}	
		//coloring buttons
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j = j + 2) {
				buttonArr[i][j + (i%2)].setBackground(lightTilePurp);
				buttonArr[i][j + (i%2)].setOpaque(true);
				buttonArr[i][j + 1 - (i%2)].setBackground(darkTilePurp);
				buttonArr[i][j + 1 - (i%2)].setOpaque(true);
			}
		}
		//adding buttons to panel
		for(int i = 7; i >=0; i--) {
			for(int j = 0; j < 8; j++) {
		buttonPanel.add(buttonArr[j][i]);
			}
		}

		//configuring frame
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setTitle("Chess Board");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		
		
		//creating thread to check if the king is on the board
		KingCheck checkKing = new KingCheck(this.getTileVals());
		checkKing.start();
				
		//creating thread to check if any pawn needs to be promoted
		PromotionCheck pCheck = new PromotionCheck(this.getTileVals(), this);
		pCheck.start();
				
		//creating colors for good and bad pieces
		Color goodFontColor = new Color(125, 135, 150);
		Color badFontColor = new Color(88, 0, 0);
				
		//creating thread to color pieces on board
		ColorThread colCheck = new ColorThread(this.getTileVals(), this.getButtonArr(), goodFontColor, badFontColor);
		colCheck.start();
				
		//creating thread to check if the king and two rooks have moved at all during the game
		CastleThread castCheck = new CastleThread(this.getTileVals());
		castCheck.start();
		
		
		
		
		//loop that main thread stays in until a king is killed or someone surrenders
		while(checkKing.isAlive() && frame.isVisible()) { //&& checkEvilKing.isAlive()) { //&& x.getCheckMate() == false) {
			//System.out.println("in loop");
			this.setCastle(castCheck.okayToCastleL(), 1);
			this.setCastle(castCheck.okayToCastleR(), 2);
			this.setCastle(castCheck.okayToCastleEL(), 3);
			this.setCastle(castCheck.okayToCastleER(), 4);
			boolean needsPromotion = pCheck.getPromo();
				if(needsPromotion) {
					this.setTileVals(pCheck.getXCoord(), pCheck.getYCoord(), pCheck.getVal());
					this.getButtonArr()[pCheck.getXCoord()][pCheck.getYCoord()].setText(this.valToString(pCheck.getVal()));
					pCheck.setPromo(false);
				}
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
		}
				
		//setting the winner number for the legend panel
		int winNum = checkKing.getWinner();
		//checking if frame was closed or if the game ended
		if(winNum != 0) {
		//displaying the winner on a panel before closing the program after a set time
		LegendFrame f = new LegendFrame(winNum);
		f.setLocation(this.getFrame().getX() + - f.getWidth(), this.getFrame().getY() + (this.getFrame().getHeight() / 2) - (f.getHeight() / 2));
		try {
			TimeUnit.MILLISECONDS.sleep(3500);
		} catch (InterruptedException e) {
		}
		this.getFrame().dispose();
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
		}
		f.dispose();
		}
		//stopping threads
		colCheck.halt();
		castCheck.halt();
		pCheck.halt();
		checkKing.halt();
		
	
	}
	/*
	private static JButton snakeButton = new JButton(new AbstractAction("S") {
		@Override
		public void actionPerformed(ActionEvent e) {
			LegendFrame f = new LegendFrame(1);
		}
	});
	private static JButton chessButton = new JButton(new AbstractAction("♛") {
		@Override
		public void actionPerformed(ActionEvent e) {
			ChessPanel p = new ChessPanel();
			holderChessPanel = p;
		}
	});
	private static JButton game1Button = new JButton(new AbstractAction("0") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	private static JButton game2Button = new JButton(new AbstractAction("0") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	
	private static JButton getButt(int key) {
		if(key == 1) {
			return snakeButton;
		}
		else if(key == 2) {
			return chessButton;
		}
		else if(key == 3) {
			return game1Button;
		}
		else return game2Button;
	}
	*/
	
	public static void main (String[] args) {
		
		new ChessPanel();
		/*
		JPanel gamePanel = new JPanel();
		JFrame gameFrame = new JFrame();
		
		gamePanel.setLayout(new GridLayout(1, 4, 0, 0));
		gamePanel.setBackground(Color.BLACK);
		gamePanel.add(getButt(1));
		gamePanel.add(chessButton);
		gamePanel.add(game1Button);
		gamePanel.add(game2Button);
		
		gameFrame.add(gamePanel);
		gameFrame.setTitle("");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);
		*/
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
	
} 
