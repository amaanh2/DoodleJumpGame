/* Space Escape culminating project play game page
 * Amaan Ahmed
 * January 18, 2022
 */
package spaceEscape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import oneiros.games.AnimationListener;
import oneiros.games.NoHoldingKeyListener;
import oneiros.games.OSprite;
import oneiros.physic.OVector2D;
import util.Resource;
import java.io.FileReader;
import java.io.FileWriter;

public class GamePlay extends OSprite implements ActionListener {
	
	//creating game over title image
	public static final Image titles = Resource.getImage("gameover.jpg");
	ImageIcon title = new ImageIcon(titles);
	JLabel gameoverlbl = new JLabel(title);
	
	//creating images for medals
	public static final Image bronzeI = Resource.getImage("bronze.jpg");
	public static final Image silverI = Resource.getImage("silver.jpg");
	public static final Image goldI = Resource.getImage("gold.jpg");
	public static final Image platinumI = Resource.getImage("platinum.jpg");
	ImageIcon bronze = new ImageIcon (bronzeI);
	ImageIcon silver = new ImageIcon (silverI);
	ImageIcon gold = new ImageIcon (goldI);
	ImageIcon platinum = new ImageIcon (platinumI);
	JLabel b = new JLabel(bronze);
	JLabel s = new JLabel(silver);
	JLabel g = new JLabel(gold);
	JLabel p = new JLabel(platinum);

    private OVector2D GRAVITY_VECTOR = new OVector2D(0.2, 270); //gravity vector for astronaut
    private Astro astro; //class variable for astronaut
    private ArrayList<Platform> platforms; //platforms array list
    private ArrayList<SuperJump> superjump; //super jump array list
    private ArrayList<Obstacle> obstacle; //obstacle array list
    private KeyListener keyListener = new KeyListener(); //key listener
    private JLabel responselbl = new JLabel(); //response for after game
    private JLabel scorelbl = new JLabel(); //score label
    private JLabel SJUMPlbl = new JLabel(); // super jump count label
    private JLabel finalScorelbl = new JLabel(); //final score label
    private JLabel recordScorelbl = new JLabel(); //record score 
    private JLabel newRecordlbl = new JLabel(); //record is broken
    private int score = 0; //score counter
    private int finalScore = 0; //final score counter
    private int recordScore = 0;
    private int over = 1; //when the game is supposed to finish counter
    private int cause = 1; //cause of player death
    private int SJUMP = 5; //super jump count on w button
    private int keep = 0; //resets everytime SJUMP is added so it doesnt add twice

    public GamePlay() {
        super(Resource.getImage("space.jpg")); //get background image
        addKeyListener(keyListener); //add key listener
        newGame(); //method call to start game
    }
    
    /* start astronaut motion
     * pre: n/a
     * post: n/a
     */
    public void start() {
        astro.startAnimation(); //start astronaut movement
        astro.jump(); //method call for astronaut to jump
    }
    
    /* creating astronaut and random platform spawn
     * pre: n/a
     * post: n/a
     */
    private void newGame() {
        astro = new Astro(); //astronaut image
        astro.addAnimationListener(new Animation()); //add KeyListener to astronaut
        add(astro);
        astro.setLocation((getWidth() - astro.getWidth()) / 2, getHeight() - astro.getHeight()); //starting coordinates of astronaut
        astro.setAcceleration(GRAVITY_VECTOR); //add gravity to astronaut
        platforms = new ArrayList<Platform>(); //create platforms ArrayList
        obstacle = new ArrayList<Obstacle>(); //create obstacle ArrayList
        superjump = new ArrayList<SuperJump>(); //create super jump ArrayList
        
        //creating and spawning the platforms
        for (int i = 0; i < 10; i++) {
            Platform p = new Platform(); //get platforms
            //random spawn coordinates
            p.setLocation((int)(Math.random()*501+0), (int)(Math.random()*979+0));
            add(p);
            platforms.add(p);
        }
      //creating and spawning super jump platforms
        for (int i = 0; i < 1; i++) {
            SuperJump s = new SuperJump(); //get super jump platforms
            //random spawn coordinates
            s.setLocation((int)(Math.random()*501+0), (int)(Math.random()*979+0));
            add(s);
            superjump.add(s);
        }
        //creating and spawning the poison platforms
        for (int i = 0; i < 5; i++) {
            Obstacle o = new Obstacle(); //get poisoned platforms
            //random spawn coordinates
            o.setLocation((int)(Math.random()*501+0), (int)(Math.random()*979+0));
            add(o);
            obstacle.add(o);
        }
        
        //creating score label to display score
        scorelbl.setBounds(520,0,60,20);
        add(scorelbl);
        scorelbl.setBackground (new Color(175,177,179));
        scorelbl.setForeground (new Color(0,0,0));
        scorelbl.setOpaque(true);
        scorelbl.setText(" Score: "+score);
        scorelbl.setVisible(true);
        
        SJUMPlbl.setBounds(510,20,70,20);
        add(SJUMPlbl);
        SJUMPlbl.setBackground (new Color(175,177,179));
        SJUMPlbl.setForeground (new Color(0,0,0));
        SJUMPlbl.setOpaque(true);
        SJUMPlbl.setText(" SJUMP: "+SJUMP);
        SJUMPlbl.setVisible(true);
    }
    
    /* update the score being displayed
     * pre: one integer parameter points
     * post: n/a
     */
    public void updateScore(int points) {
    	score = score+points; //score calculator
    	finalScore = score/50; //slow down score counter
    	scoreText(finalScore); //display score
        if(finalScore % 2500 == 0 && finalScore % 5000 != 0){
            keep = 1;
        }
        if(finalScore % 5000 == 0){
            if (keep == 1){
                SJUMP++;
            }
            keep = 0;
        }
        SJUMPlbl.setText(" SJUMP: "+SJUMP);
    }
    
    /* start new game after previous game is over
     * pre: n/a
     * post: n/a
     */
    public void startGame() {
        astro.stopAnimation(); //stop animation from previous game
        astro.stopAnyMotion(); //stop motion from previous game
        removeAll(); //remove everything from previous game
        newGame(); //start new game
        start(); //start astronaut animation
        repaint();
    }
    
    /* stops the game and takes user to post game page
     * pre: one integer parameter over
     * post: n/a
     */
    public void gameOver(int over) {
    	if (over>=2) { //astronaut falls a second time
    		astro.stopAnimation(); //stop astronaut animation
    		astro.stopAnyMotion(); //stop astronaut motion
    		removeAll(); //remove everything from the frame
    		repaint(); 
    		afterGame(); //post game screen
    	}
    }
    
    /* post game page that shows player the results
     * pre: n/a
     * post: n/a
     */
    public void afterGame() {
    	//setting game over title
    	gameoverlbl.setBounds(0, 50, 600, 338);
    	add(gameoverlbl);
    	gameoverlbl.setVisible(true);
    	
    	//setting all the medals
    	b.setBounds(480, 570, 100, 99);
    	add(b);
    	b.setVisible(false);
    	s.setBounds(480, 570, 100, 99);
    	add(s);
    	s.setVisible(false);
    	g.setBounds(480, 570, 100, 99);
    	add(g);
    	g.setVisible(false);
    	p.setBounds(480, 570, 100, 99);
    	add(p);
    	p.setVisible(false);
    	medals(finalScore);
    	
    	//creating the response label to tell player how they died
    	responselbl.setBounds(0,428,600,60);
        add(responselbl);
        responselbl.setBackground (new Color(15,15,15));
        responselbl.setForeground (new Color(179,31,253));
        responselbl.setFont(new Font("", Font.BOLD, 40));
        responselbl.setOpaque(true);
        responseText();
        responselbl.setVisible(true);
        
        //creating the label to display final score
    	finalScorelbl.setBounds(0,488,600,60);
        add(finalScorelbl);
        finalScorelbl.setBackground (new Color(15,15,15));
        finalScorelbl.setForeground (new Color(179,31,253));
        finalScorelbl.setFont(new Font("", Font.BOLD, 40));
        finalScorelbl.setOpaque(true);
        finalScoreText(finalScore);
        finalScorelbl.setVisible(true);

        //record is broken
        newRecordlbl.setBounds(0,548,600,60);
        add(newRecordlbl);
        newRecordlbl.setBackground (new Color(15,15,15));
        newRecordlbl.setForeground (new Color(179,31,253));
        newRecordlbl.setFont(new Font("", Font.BOLD, 40));
        newRecordlbl.setOpaque(true);
        newRecordlbl.setVisible(false);

        //create label to display record score
        recordScorelbl.setBounds(0,608,600,60);
        add(recordScorelbl);
        recordScorelbl.setBackground (new Color(15,15,15));
        recordScorelbl.setForeground (new Color(179,31,253));
        recordScorelbl.setFont(new Font("", Font.BOLD, 40));
        recordScorelbl.setOpaque(true);
        recordScoreText(recordScore);
        recordScorelbl.setVisible(true);
        
        //creating the play again button
    	JButton play = new JButton ("PLAY AGAIN");
        play.setBounds(50,700,200,60);
        play.setBackground (new Color(175,177,179));
        play.setForeground (new Color(0,0,0));
        play.setFont(new Font("", Font.BOLD, 15));
        play.setActionCommand("PLAY");
        play.addActionListener(this);
        play.setVisible(true);
    	add(play);
    	play.repaint();
    	
    	//creating the return to home screen button
    	JButton home = new JButton ("HOME SCREEN");
    	home.setBounds(325,700,200,60);
    	home.setBackground (new Color(175,177,179));
        home.setForeground (new Color(0,0,0));
        home.setFont(new Font("", Font.BOLD, 15));
    	home.setActionCommand("HOME");
    	home.addActionListener(this);
    	home.setVisible(true);
      	add(home);
      	home.repaint();
    }
    
    /* moving all platforms down when astronaut is about to exit frame
     * pre: n/a
     * post: n/a
     */
    private void moveStageUp() {
        if (astro.getY() < 300) { //astronaut getting close to leaving frame
            int offset = 300 - astro.getY(); //maximum height allowed for astronaut in the frame
            astro.setY(300); //limiting the astronaut height to 300
            for (Platform p : platforms) {
                p.setLocation(p.getX(), p.getY() + offset); //move platforms downwards
                if (p.getY() > getHeight()) { //platforms go below the frame
                    p.setLocation((int) (Math.random() * (getWidth() - p.getWidth())),((int) (Math.random() * 50) - 50)); //new platform spawn
                }
                score += offset; //adding how much higher astronaut got to score
                updateScore(offset); //update displayed score
            }
            for (SuperJump s : superjump) {
                s.setLocation(s.getX(), s.getY() + offset); //move platforms downwards
                if (s.getY() > getHeight()) { //platforms go below the frame
                    s.setLocation((int) (Math.random() * (getWidth() - s.getWidth())),((int) (Math.random() * 50) - 50)); //new platform spawn
                }
                score += offset; //adding how much higher astronaut got to score
                updateScore(offset); //update displayed score
            }
            for (Obstacle o : obstacle) {
                o.setLocation(o.getX(), o.getY() + offset); //move platforms downwards
                if (o.getY() > getHeight()) { //platforms go below the frame
                    o.setLocation((int) (Math.random() * (getWidth() - o.getWidth())),((int) (Math.random() * 50) - 50)); //new platform spawn
                }
                score += offset; //adding how much higher astronaut got to score
                updateScore(offset); //update displayed score
            }
        }
    }
    
    //all the movement for the astronaut is in this private class
    private class Animation extends AnimationListener {
    	
    	/* movement of astronaut
         * pre: n/a
         * post: n/a
         */
        public void postAction() {
            if (astro.getY() > 1000) { //astronaut falls below frame
                startGame(); //start game
                gameOver(over); //game over second time
                over++; //game over counter
            } 
            else { //astronaut within frame

                double x = astro.getVelocity().getX(); //get astronaut horizontal velocity and x coordinate
                if (Math.abs(x) < 0.1) { //astronaut is slowing down or stationary horizontally
                	astro.setVelocityX(0); //set horizontal velocity to zero
                } 
                else { //astronaut is speeding up
                    double speed = Math.abs(x) * 0.02; //horizontal speed limit
                    if (x > 0) { //horizontal velocity less than zero
                    	astro.setVelocityX(x - speed); //speed up astronaut
                    } 
                    else if (x < 0) { //horizontal velocity greater than zero
                    	astro.setVelocityX(x + speed); //slow down astronaut
                    }
                }

                int switchAstro = astro.getWidth() / 2; //variable to switch sides on screen
                if (astro.getX() < -switchAstro) { //leaving frame on right side
                	astro.setX(getWidth() - switchAstro); //bring astronaut to left side
                } 
                else if (astro.getX() > getWidth() - switchAstro) { //leaving frame on left side
                	astro.setX(-switchAstro); //bring astronaut to right side
                }
                for (Platform p : platforms) {
                    if (astro.getVelocity().y < 0 && astro.getFeet().intersects(p.getBase())) { //astronauts feet are colliding with platforms base
                    	astro.jump(); //jump
                    }
                }
                for (SuperJump s : superjump) {
                    if (astro.getVelocity().y < 0 && astro.getFeet().intersects(s.getBase())) { //astronauts feet are colliding with platforms base
                    	astro.superJump(); //super jump
                    }
                }
                for (Obstacle o : obstacle) {
                    if (astro.getVelocity().y < 0 && astro.getFeet().intersects(o.getBase())) { //astronauts feet are colliding with platforms base
                    	astro.deadJump(); //dead jump
                    	cause = 2; //cause of death is poison
                    	SJUMP = 0;
                    }
                }
                moveStageUp(); //move stage up
            }
        }
    }

    private class KeyListener extends NoHoldingKeyListener {

        private boolean right = false; //boolean move right
        private boolean left = false; //boolean move left
        
        /* key listener while key is bring pressed
         * pre: one KeyEvent e
         * post: n/a
         */
        public void keyPressed2(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) { //left arrow is pressed
            	astro.startMovingLeft(); //accelerate left
                left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) { //right arrow pressed
            	astro.startMovingRight(); //accelerate right
                right = true;
            } else if
             (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) { //right arrow pressed
            	if (SJUMP > 0){
                    astro.superJump();
                    SJUMP--;
                }
            }
        }
        
        /* key listener when key is released
         * pre: one KeyEvent e
         * post: n/a
         */
        public void keyReleased2(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && left) { //left arrow is released
            	astro.stopMovingLeft(); //decelerate left
            } else if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && right) { //right key is released
            	astro.stopMovingRight(); //decelerate right
            }
        }
    }
    
    /* tells the player how they died either from fall or poison
     * pre: n/a
     * post: n/a
     */
    public void responseText() {
    	if (cause==1) { //died from fall
    		responselbl.setText("DIED FROM                          Fall");
    	}
    	else if (cause==2) { //died from poison
    		responselbl.setText("DIED FROM                    Poison");
    	}
    }
    
    /* tells the player their final score and score at end of screen according to number of digits
     * pre: one integer parameter score
     * post: n/a
     */
    public void finalScoreText(int score) {
    	if (score<10&&score>=0) { //score of 1 digit
    		finalScorelbl.setText("FINAL SCORE                          "+finalScore);
    	}
    	else if (score<100&&score>9) { //score of 2 digits
    		finalScorelbl.setText("FINAL SCORE                        "+finalScore);
    	}
    	else if (score<1000&&score>99) { //score of 3 digits
    		finalScorelbl.setText("FINAL SCORE                      "+finalScore);
    	}
    	else if (score<10000&&score>999) { //score of 4 digits
    		finalScorelbl.setText("FINAL SCORE                    "+finalScore);
    	}
    	else if (score<100000&&score>9999) { //score of 5 digits
    		finalScorelbl.setText("FINAL SCORE                  "+finalScore);
    	}
    	else { //score of 6 or more digits
    		finalScorelbl.setText("FINAL SCORE                "+finalScore);
    	}
    }

    //Read read = new Read();
    //Writer writer = new Writer();

    public int getRecord(){
        String name = "";
        try{
            FileReader reader = new FileReader("high.txt");
            int data = reader.read();
            while (data != -1){
                name = name + (char)data;
                data = reader.read();
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println("Error reading the file.");
        }

        int score = Integer.parseInt(name);
        return score;
    }

    public void recordScoreText(int score) {
        recordScore = getRecord();
        if (recordScore<finalScore){
            recordScore = finalScore;
            newRecordlbl.setText("You Broke the Record!");
            newRecordlbl.setVisible(true);
            String srecord = String.valueOf(recordScore);
            try{
                FileWriter writer = new FileWriter("high.txt");
                writer.write(srecord);
                writer.close();
            }
            catch(Exception e){
                System.out.println("Error occurred while writing to file.");
            }
        }
    	recordScorelbl.setText("RECORD: "+recordScore);
        
    }
    
    /* displays score and sets appropriate JLabel size according to score
     * pre: one integer parameter score
     * post: n/a
     */
    public void scoreText(int score) {
    	if (score<10&&score>=0) { //score of 1 digit
    		scorelbl.setBounds(520,0,60,20);
    		scorelbl.setText(" Score: "+score);
    	}
    	else if (score<100&&score>9) { //score of 2 digits
    		scorelbl.setBounds(515,0,65,20);
    		scorelbl.setText(" Score: "+score);
    	}
    	else if (score<1000&&score>99) { //score of 3 digits
    		scorelbl.setBounds(510,0,70,20);
    		scorelbl.setText(" Score: "+score);
    	}
    	else if (score<10000&&score>999) { //score of 4 digits
    		scorelbl.setBounds(505,0,75,20);
    		scorelbl.setText(" Score: "+score);
    	}
    	else if (score<100000&&score>9999) { //score of 5 digits
    		scorelbl.setBounds(500,0,80,20);
    		scorelbl.setText(" Score: "+score);
    	}
    	else { //score of 6 digits or more
    		scorelbl.setBounds(495,0,88,20);
    		scorelbl.setText(" Score: "+score);
    	}
    }
    
    /* displays the medal the player got according to score
     * pre: one integer parameter score
     * post: n/a
     */
    public void medals (int score) {
    	if (score>9999&&score<20000) { //10000+ score
    		b.setVisible(true); //bronze medal
    	}
    	else if (score>19000&&score<50000) { //20000+ score
    		s.setVisible(true); //silver medal
    	}
    	else if (score>49000&&score<100000) { //50000+ score
    		g.setVisible(true); //gold medal
    	}
    	else if (score>100000) { //100000+ score
    		p.setVisible(true); //platinum
    	}
    }
    
    /* action performed method
     * pre: one ActionEvent parameter event
     * post: n/a
     */
    public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand(); 
		if(eventName.equals("HOME")) { //if home button is pressed
			setVisible(false); //close game frame
			HomeScreen home = new HomeScreen(); //open home screen frame
		}
		if(eventName.equals("PLAY")) { //if play again button is pressed
			setVisible(false); //close game frame
			Game game = new Game(); //open new game frame
		}
	}
}