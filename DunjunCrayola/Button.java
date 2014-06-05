import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Button
{
    private GamePanel gamePanelReference;
    
    private Location location;
    private BufferedImage image;
    
    private boolean mousedOver;
    private boolean mousePressedOver;
    
    public Button(GamePanel gamePanel)
    {
        this.gamePanelReference = gamePanel;
        location = new Location(0,0);
        image = null;
        
        mousedOver = false;
        mousePressedOver = false;
    }
    
    //What happens when the mouse is clicked on a button, meant to be overriden
    public abstract void performClickAction();
    
    //Handling Mouse Events
    public void handleMousePress(MouseEvent me)
    {
        if(getBounds().contains(me.getX(), me.getY()))
        {
            mousePressedOver = true;
        }
        else
        {
            mousePressedOver = false;
            mousedOver = false;
        }
    }
    
    public void handleMouseRelease(MouseEvent me)
    {
        if(getBounds().contains(me.getX(), me.getY()))
        {
            mousedOver = true;
            if(mousePressedOver)
            {
                performClickAction();
            }
        }
        else
        {
            mousedOver = false;
        }
        mousePressedOver = false;
    }
    
    public void handleMouseMove(MouseEvent me)
    {
        if(getBounds().contains(me.getX(), me.getY()))
        {
            mousedOver = true;
        }
        else
        {
            mousedOver = false;
        }
    }
    
    public void handleMouseDragged(MouseEvent me)
    {
        handleMouseMove(me);
        if(getBounds().contains(me.getX(), me.getY()) && mousePressedOver)
        {
            mousePressedOver = true;
        }
        else
        {
            mousePressedOver = false;
        }
    }
    
    //Drawing
    
    public void draw(Graphics g)
    {
        if(image != null)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, getX(), getY(), null);
            if(mousedOver)
            {
                g2d.setColor(Color.YELLOW);
                g2d.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            }
            if(mousePressedOver)
            {
                g2d.setColor(Color.RED);
                g2d.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            }
        }
    }
    
    //Fields
    
    public int getX(){return location.getX();}
    public int getY(){return location.getY();}
    
    public void setX(int x){location.setX(x);}
    public void setY(int y){location.setY(y);}
    
    public int getWidth()
    {
        if(image != null)
            return image.getWidth();
        return 0;
    }
    
    public int getHeight()
    {
        if(image != null)
            return image.getHeight();
        return 0;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public Location getLocation(){return location;}
    public BufferedImage getImage(){return image;}
    
    public void setLocation(Location loc){location = loc;}
    public void setLocation(int x, int y){location.setX(x);location.setY(y);}
    public void setImage(BufferedImage image){this.image = image;}
    
    public GamePanel getGamePanelReference(){return gamePanelReference;}
    public void setGamePanelReference(GamePanel gamePanel){this.gamePanelReference = gamePanel;}
}
