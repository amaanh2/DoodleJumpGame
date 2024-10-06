/* Space Escape culminating project game page
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import javax.swing.JFrame;

public class Game extends JFrame {
    
	//class variable for GamePlay
    private GamePlay gamePlay;

    public Game() {
    	
    	//creating the frame
        super("Space Escape");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setSize(600, 1000);
        gamePlay = new GamePlay();
        add(gamePlay);
        gamePlay.requestFocus();
        setVisible(true); //make frame visible
        
        start(); //method call
    }

    /* calls the start method from GamePlay class
     * pre: n/a
     * post: n/a
     */
    public void start() {
        gamePlay.start();
    }
}