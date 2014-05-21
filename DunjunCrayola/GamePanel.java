import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Write a description of class GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable
{
    private Actor[][] grid;
    private Actor player;

    public GamePanel()
    {
        this(1280,704);
    }

    public GamePanel(int panelWidth, int panelHeight)
    {
        super.setPreferredSize(new Dimension(panelWidth, panelHeight));

        //super.addMouseListener(this);
        addMouseListener(this);

        //super.addMouseMotionListener(this);
        addMouseMotionListener(this);

        //super.addKeyListener(this);
        //addKeyListener(this);
        
        //setFocusable(true);
        //requestFocusInWindow();

        grid = new Actor[panelHeight / 32][panelWidth / 32];
        player = new Actor();
        player.putSelfIntoGrid(0,0,grid);
    }

    //Updating Method
    public void run()
    {

    }

    //Painting
    public void paintComponent(Graphics g)
    {
        clearScreen(g, Color.WHITE);
        for(int r = 0; r < grid.length; r++)
        {
            for(int c = 0; c < grid[r].length; c++)
            {
                if(grid[r][c] != null)
                    grid[r][c].draw(g);
                g.setColor(Color.RED);
                g.drawRect(c * 32, r * 32, 32, 32);
            }
        }
    }

    public void clearScreen(Graphics g, Color color)
    {
        g.setColor(color);
        g.fillRect(0,0,getWidth(), getHeight());   
    }

    //Key Handling Methods
    public void keyPressed(KeyEvent ke)
    {
        //player.move(3,3);
        if(ke.getKeyCode() == KeyEvent.VK_DOWN)
        {
            player.move(player.getRow() + 1, player.getColumn());
        }
        if(ke.getKeyCode() == KeyEvent.VK_UP)
        {
            player.move(player.getRow() - 1, player.getColumn());
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.move(player.getRow(), player.getColumn() - 1);
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.move(player.getRow(), player.getColumn() + 1);
        }
        repaint();
    }

    public void keyReleased(KeyEvent ke)
    {
        
    }

    public void keyTyped(KeyEvent ke){}

    //Mouse Button Handling Methods
    public void mousePressed(MouseEvent me)
    {
        //player.move(1,1);
        repaint();
    }

    public void mouseReleased(MouseEvent me){}

    public void mouseClicked(MouseEvent me){}

    //Mouse Motion
    public void mouseMoved(MouseEvent me)
    {
        //player.move(5,5);
        repaint();
    }

    public void mouseDragged(MouseEvent me){}

    public void mouseEntered(MouseEvent me){}

    public void mouseExited(MouseEvent me){}

    //Helper Methods
    public void delay(int milliseconds)
    {
        try{
            Thread.sleep(milliseconds);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
