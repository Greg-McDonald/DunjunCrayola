import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start
{
    public static void main(String[] args)
    {
        JFrame gameFrame = new JFrame("Dunjun Crayola");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel gamePanel = new GamePanel();
        
        gameFrame.getContentPane().add(gamePanel);
        
        gameFrame.pack();
        
        //Sets the location of the frame to be the center of the screen
        Dimension screenBounds =  Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((int)(screenBounds.getWidth() / 2 - gameFrame.getWidth() / 2), (int)(screenBounds.getHeight() / 2 - gameFrame.getHeight() / 2));
        gameFrame.setVisible(true);
    }
}
