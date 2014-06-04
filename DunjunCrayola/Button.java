import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Button
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
    
    //Handling Mouse Events
    public void HandleMousePress(MouseEvent me)
    {
        
    }
    
    public void HandleMouseRelease(MouseEvent me)
    {
        
    }
    
    public void HandleMouseMove(MouseEvent me)
    {
        if(getBounds().contains(me.getX(), me.getY()))
            mousedOver = true;
        else
            mousedOver = false;
    }
    
    //Drawing
    
    public void draw(Graphics g)
    {
        if(image != null)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, getX(), getY(), null);
        }
    }
    
    //Fields
    
    public int getX(){return location.getX();}
    public int getY(){return location.getX();}
    
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
    public void setImage(BufferedImage image){this.image = image;}
    
    public GamePanel getGamePanelReference(){return gamePanelReference;}
    public void setGamePanelReference(GamePanel gamePanel){this.gamePanelReference = gamePanel;}
}
