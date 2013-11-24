import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;




public class GameRunner 
{
	private static Map board;
	
	public static void main(String[] args)
	{
		board = new Map();
		System.out.println(board.toString());
		
		//TODO: initialize commands (either hardcode, or user input)
		
		int delay = 300;
		ActionListener timeListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: perform updates on board
				System.out.println(board);
			}
		};
		
		new Timer(delay, timeListener).start();
		while(true){}
	}
	
}

