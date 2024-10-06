/* Space Escape culminating project instructions page
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.*;
import javax.swing.*;
import util.Resource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class Instructions extends JFrame implements ActionListener {
		
		//creating all the images and labels for this class
		public static final Image backI = Resource.getImage("back.jpg");
		public static final Image arrowsI = Resource.getImage("arrows.jpg");
		public static final Image platformI = Resource.getImage("platformInfo.jpg");
		public static final Image superJumpI = Resource.getImage("superJumpInfo.jpg");
		public static final Image obstacleI = Resource.getImage("obstacleInfo.jpg");
		public static final Image bronzeI = Resource.getImage("bronze.jpg");
		public static final Image silverI = Resource.getImage("silver.jpg");
		public static final Image goldI = Resource.getImage("gold.jpg");
		public static final Image platinumI = Resource.getImage("platinum.jpg");
		ImageIcon back = new ImageIcon (backI);
		ImageIcon arrows = new ImageIcon (arrowsI);
		ImageIcon platform = new ImageIcon (platformI);
		ImageIcon superJump = new ImageIcon (superJumpI);
		ImageIcon obstacle = new ImageIcon (obstacleI);
		ImageIcon bronze = new ImageIcon (bronzeI);
		ImageIcon silver = new ImageIcon (silverI);
		ImageIcon gold = new ImageIcon (goldI);
		ImageIcon platinum = new ImageIcon (platinumI);
		JLabel one = new JLabel();
		JLabel two = new JLabel();
		JLabel three = new JLabel();
		JLabel four = new JLabel();
		JLabel five = new JLabel();
		JLabel six = new JLabel();
		JLabel arrow = new JLabel(arrows);
		JLabel plat = new JLabel(platform);
		JLabel sprjp = new JLabel(superJump);
		JLabel obs = new JLabel(obstacle);
		JLabel b = new JLabel(bronze);
		JLabel s = new JLabel(silver);
		JLabel g = new JLabel(gold);
		JLabel p = new JLabel(platinum);
	 
	public Instructions() {
		 
		//creating the frame and background
    	super("Space Escape");
    	setSize(600,1000);
    	setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	IPanel background = new IPanel();
    	background.setLayout(null);
        background.setBounds(0,0,600,1000);
    	add(background);
    	
    	//creating back button
    	JButton back = new JButton (this.back);
    	back.setBounds(423,890,150,60);
    	back.setBackground(new Color(15,15,15));
    	back.setActionCommand("BACK");
    	back.addActionListener(this);
    	back.setVisible(true);
    	background.add(back);
    	
    	//first instruction label
    	one.setText("                  Use arrow keys to move left and right");
    	one.setBackground(new Color(15,15,15));
    	one.setForeground(new Color(179,31,253));
    	one.setFont(new Font("", Font.BOLD, 20));
    	one.setOpaque(true);
    	one.setBounds(0, 0, 600, 25);
    	background.add(one);
    	one.setVisible(true);
    	
    	//arrow keys image
    	arrow.setBounds(190, 30, 195, 87);
    	background.add(arrow);
    	arrow.setVisible(true);
    	
    	//second instruction label
    	two.setText("                              Use platforms to jump");
    	two.setBackground(new Color(15,15,15));
    	two.setForeground(new Color(179,31,253));
    	two.setFont(new Font("", Font.BOLD, 20));
    	two.setOpaque(true);
    	two.setBounds(0, 150, 600, 25);
    	background.add(two);
    	two.setVisible(true);
    	
    	//platform image
    	plat.setBounds(190, 180, 195, 87);
    	background.add(plat);
    	plat.setVisible(true);
    	
    	//third instruction label
    	three.setText("                 Use super jump platforms to jump higher");
    	three.setBackground(new Color(15,15,15));
    	three.setForeground(new Color(179,31,253));
    	three.setFont(new Font("", Font.BOLD, 20));
    	three.setOpaque(true);
    	three.setBounds(0, 265, 600, 25);
    	background.add(three);
    	three.setVisible(true);
    	
    	//super jump image
    	sprjp.setBounds(190, 295, 195, 87);
    	background.add(sprjp);
    	sprjp.setVisible(true);
    	
    	//fourth instruction label
    	four.setText("                          Avoid the poison platforms ");
    	four.setBackground(new Color(15,15,15));
    	four.setForeground(new Color(179,31,253));
    	four.setFont(new Font("", Font.BOLD, 20));
    	four.setOpaque(true);
    	four.setBounds(0, 380, 600, 25);
    	background.add(four);
    	four.setVisible(true);
    	
    	//obstacle image
    	obs.setBounds(190, 410, 195, 87);
    	background.add(obs);
    	obs.setVisible(true);
    	
    	//fifth instruction label
    	five.setText("               Bronze: 10000+                    Silver: 20000+");
    	five.setBackground(new Color(15,15,15));
    	five.setForeground(new Color(179,31,253));
    	five.setFont(new Font("", Font.BOLD, 20));
    	five.setOpaque(true);
    	five.setBounds(0, 495, 600, 25);
    	background.add(five);
    	five.setVisible(true);
    	
    	//bronze and silver images
    	b.setBounds(115, 540, 100, 99);
    	background.add(b);
    	b.setVisible(true);
    	s.setBounds(375, 540, 100, 99);
    	background.add(s);
    	s.setVisible(true);
    	
    	//sixth instruction label
    	six.setText("                 Gold: 50000+                   Platinum: 100000+");
    	six.setBackground(new Color(15,15,15));
    	six.setForeground(new Color(179,31,253));
    	six.setFont(new Font("", Font.BOLD, 20));
    	six.setOpaque(true);
    	six.setBounds(0, 660, 600, 25);
    	background.add(six);
    	six.setVisible(true);
    	
    	//gold and platinum images
    	g.setBounds(115, 705, 100, 99);
    	background.add(g);
    	g.setVisible(true);
    	p.setBounds(375, 705, 100, 99);
    	background.add(p);
    	p.setVisible(true);
    	
    	setVisible(true); //make the frame visible
	}
	
	/* action performed method
	 * pre: one ActionEvent parameter event
	 * post: n/a
	 */
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand(); 
		if( eventName.equals("BACK")) { //back button is pressed
			setVisible(false); //close instructions page
			HomeScreen home = new HomeScreen(); //open home page
		}
	}
}