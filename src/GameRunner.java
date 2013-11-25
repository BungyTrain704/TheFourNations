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
		Civilization civ = Civilization.getInstance();
		civ.setMap(board);
		
		
		civ.addTaskToQueue(new BuildStructureTask(10, 941, 940, board, new BasicStructure(941, "S")));
		//TODO: initialize commands (either hardcode, or user input)
		
		int delay = 300;
		ActionListener timeListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: perform updates on board
				Civilization.getInstance().update();
				System.out.println(board);
			}
		};
		
		new Timer(delay, timeListener).start();
		while(true){}
	}
	
}

