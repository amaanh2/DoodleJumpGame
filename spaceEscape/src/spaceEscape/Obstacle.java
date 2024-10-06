/* Space Escape culminating project obstacle class
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.Rectangle;
import oneiros.games.OSprite;
import util.Resource;

public class Obstacle extends OSprite{

    public Obstacle() {
        super(Resource.getImage("obstacle.jpg")); //get obstacle image
    }
    
    /* gets the base of the platform
     * pre: n/a
     * post: return x y coordinates, base width, and base height
     */
    public Rectangle getBase(){
        return new Rectangle(getX(), getY(), getWidth(), 2);
    }
}