import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game_OX extends JFrame implements ActionListener{
	private ImageIcon icons[] = {new ImageIcon("O.gif"), new ImageIcon("X.gif")};
	int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
	int player = 0;
	JPanel panel = new JPanel();
	
	public static void main(String[] args){
		new Game_OX();
	}
	
	public Game_OX(){
		JFrame frame = new JFrame("圈圈叉叉");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);

		restart(panel);	
		frame.add(panel);
		frame.setVisible(true);
	}
	
	//restart panel
	//將panel內容移除再重放button，並將棋盤數值歸零
	public void restart(JPanel panel){
		panel.removeAll();
		player = 0;
		panel.setLayout(new GridLayout(3, 3, 3, 3));
		for(int i = 0; i < 9; i++){
			JButton btn = new JButton();
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(this);
			panel.add(btn);
			board[i/3][i%3] = 0;
		}
		panel.updateUI();
	}
	
	//CHECK,who is winner
	//判斷輸贏，各有八種連線方式，九格下滿沒輸贏就是平手
	public int checkWin(int board[][]){
		if((board[0][0] == -1 && board[0][1] == -1 && board[0][2] == -1) ||
		   (board[1][0] == -1 && board[1][1] == -1 && board[1][2] == -1) ||
		   (board[2][0] == -1 && board[2][1] == -1 && board[2][2] == -1) ||
		   (board[0][0] == -1 && board[1][0] == -1 && board[2][0] == -1) ||
		   (board[0][1] == -1 && board[1][1] == -1 && board[2][1] == -1) ||
		   (board[0][2] == -1 && board[1][2] == -1 && board[2][2] == -1) ||
		   (board[0][0] == -1 && board[1][1] == -1 && board[2][2] == -1) ||
		   (board[0][2] == -1 && board[1][1] == -1 && board[2][0] == -1) ){
			return 1;
		}else if((board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1) ||
				   (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1) ||
				   (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) ||
				   (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1) ||
				   (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1) ||
				   (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1) ||
				   (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) ||
				   (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) ){
			return 2;
		}else{
			return 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)e.getSource();
		int row = Integer.parseInt(btn.getActionCommand())/3;
		int col = Integer.parseInt(btn.getActionCommand())%3;
		
		//if the board == 0，checkWin，and showDialog to ask "Want to play again?".
		//檢查棋盤沒被下過(等於0)才能下，下完判斷輸贏，跳出視窗詢問是否重新否則關閉程式
		if(board[row][col] == 0){
			btn.setIcon(icons[player%2]);
			board[row][col] = (player % 2 == 0) ? -1 : 1;
			player++;
			if(checkWin(board) == 1){
				int option = JOptionPane.showConfirmDialog(this, "圈圈贏了！要重新嗎？", "遊戲結束", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					restart(panel);
				}else if(option == JOptionPane.NO_OPTION){
					System.exit(0);
				}
			}else if(checkWin(board) == 2){
				int option = JOptionPane.showConfirmDialog(this, "叉叉贏了！要重新嗎？", "遊戲結束", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					restart(panel);
				}else if(option == JOptionPane.NO_OPTION){
					System.exit(0);
				}
			}else if(player == 9 && checkWin(board) == 0){
				int option = JOptionPane.showConfirmDialog(this, "平手！要重新嗎？", "遊戲結束", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					restart(panel);
				}else if(option == JOptionPane.NO_OPTION){
					System.exit(0);
				}
			}
		}		
	}
}

