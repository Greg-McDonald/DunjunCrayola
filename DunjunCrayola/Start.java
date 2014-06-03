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
        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
        
        GamePanel gamePanel = new GamePanel();
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gameFrame.addKeyListener(gamePanel);
        
        gameFrame.getContentPane().add(gamePanel);
        
        gameFrame.pack();
        //gameFrame.setSize(new Dimension(1400,704));
        
        //Sets the location of the frame to be the center of the screen
        Dimension screenBounds =  Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((int)(screenBounds.getWidth() / 2 - gameFrame.getWidth() / 2), (int)(screenBounds.getHeight() / 2 - gameFrame.getHeight() / 2));
        gameFrame.setVisible(true);
    }
}
