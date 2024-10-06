/* Space Escape culminating project home page
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.*;
import javax.swing.*;
import util.Resource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class HomeScreen extends JFrame implements ActionListener {
		
		//creating the title image
		public static final Image titles = Resource.getImage("title.jpg");
		ImageIcon title = new ImageIcon(titles);
		JLabel titlelbl = new JLabel(title);
		
	public HomeScreen() {
		 
		//creating the frame and background
    	super("Space Escape");
    	setSize(600,1000);
    	setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    	IPanel background = new IPanel();
    	background.setLayout(null);
        background.setBounds(0,0,600,1000);
    	add(background);
    	
    	//creating the title
    	titlelbl.setBounds(35, 250, 511, 214);
    	background.add(titlelbl);
    	titlelbl.setVisible(true);
    	
    	//creating the play button
    	JButton play = new JButton ("PLAY");
        play.setBounds(50,700,200,60);
        play.setBackground (new Color(175,177,179));
        play.setForeground (new Color(0,0,0));
        play.setFont(new Font("", Font.BOLD, 15));
        play.setActionCommand("PLAY");
        play.addActionListener(this);
        play.setVisible(true);
    	background.add(play);
    	play.repaint();
    	
    	//creating the instructions button
    	JButton inst = new JButton ("INSTRUCTIONS");
        inst.setBounds(325,700,200,60);
        inst.setBackground (new Color(175,177,179));
        inst.setForeground (new Color(0,0,0));
        inst.setFont(new Font("", Font.BOLD, 15));
        inst.setActionCommand("INSTRUCTIONS");
        inst.addActionListener(this);
        inst.setVisible(true);
      	background.add(inst);
      	inst.repaint();
      	
    	setVisible(true); //make frame visible
	}
	 
	//driver method
	public static void main(String[] arguments) {
		HomeScreen frame = new HomeScreen();
	}
	
	/* action performed method
	 * pre: one ActionEvent parameter event
	 * post: n/a
	 */
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand(); 
		if(eventName.equals("INSTRUCTIONS")) { //instructions button pressed
			setVisible(false); //close home page
			Instructions game = new Instructions(); //open instructions page
		}
		if(eventName.equals("PLAY")) { //play button pressed
			setVisible(false); //close home page
			Game game = new Game(); //open game page
		}
	}
}