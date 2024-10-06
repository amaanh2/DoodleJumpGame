/* Space Escape culminating project astronaut class
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.Image;
import java.awt.Rectangle;
import oneiros.games.OSprite;
import oneiros.physic.OVector2D;
import util.Resource;

public class Astro extends OSprite {

    public static final OVector2D JUMP = new OVector2D(12, 90); //jump vector
    public static final OVector2D SUPER_JUMP = new OVector2D(24, 90); //super jump vector
    public static final OVector2D DEAD_JUMP = new OVector2D(6, 90); //dead jump vector
    public static final OVector2D MOVING_VECTOR = new OVector2D(0.5, 0); //horizontal acceleration vector
    public static final Image ASTRO = Resource.getImage("astro.jpg"); //get astronaut image
    public static final Image RED_ASTRO = Resource.getImage("redastro.jpg"); //get poisoned astronaut image
    
    /* set image to astronaut
     * pre: n/a
     * post: n/a
     */
    public Astro() {
        super(ASTRO);
    }
    
    /* make the astronaut jump
     * pre: n/a
     * post: n/a
     */
    public void jump() {
        setVelocityY(0);
        addVelocity(JUMP);
    }
    
    /* make the astronaut super jump
     * pre: n/a
     * post: n/a
     */
    public void superJump() {
        setVelocityY(0);
        addVelocity(SUPER_JUMP);
    }
    
    /* make the astronaut dead jump
     * pre: n/a
     * post: n/a
     */
    public void deadJump() {
        setVelocityY(0);
        addVelocity(DEAD_JUMP);
        setBackground(RED_ASTRO); //change image to poisoned astronaut
    }
    
    /* stop all vertical and horizontal motion
     * pre: n/a
     * post: n/a
     */
    public void stopAnyMotion() {
        setVelocity(null);
        setAcceleration(null);
    }
    
    /* accelerate left
     * pre: n/a
     * post: n/a
     */
    public void startMovingLeft() {
        subAcceleration(MOVING_VECTOR);
    }
    
    /* decelerate left
     * pre: n/a
     * post: n/a
     */
    public void stopMovingLeft() {
        addAcceleration(MOVING_VECTOR);
    }
    
    /* accelerate right
     * pre: n/a
     * post: n/a
     */
    public void startMovingRight() {
        addAcceleration(MOVING_VECTOR);
    }
    
    /* decelerate right
     * pre: n/a
     * post: n/a
     */
    public void stopMovingRight() {
            subAcceleration(MOVING_VECTOR);
    }
    
    /* get the location of the astronauts feet for collision with platform
     * pre: n/a
     * post: return astronauts feet coordinates
     */
    public Rectangle getFeet() {
        if (getBackgroundImage() == ASTRO) { //astronaut is alive
            return new Rectangle(getX(), getY() + getHeight() - 15, 55, 15); //return feet coordinates
        } else { //astronaut is poisoned
            return new Rectangle(-100 , -100); //return false coordinates to remove collision
        }
    }
}
