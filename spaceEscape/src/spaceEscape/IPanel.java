/* Space Escape culminating project draw background
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.*;
import javax.swing.*;
import util.Resource;

public class IPanel extends JPanel  {
	
	//get space background image
	public static final Image space = Resource.getImage("space.jpg");
	
	public IPanel() {
		
	}
	//draw space background image
	public void paintComponent(Graphics comp) {
    	 Graphics2D comp2D = (Graphics2D) comp;
    	 comp2D.drawImage(space, 0, 0, this); 
    }
}