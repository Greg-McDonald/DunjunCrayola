import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile
{
    private Item tileItem;
    private boolean walkable;
    private BufferedImage image;
    
    public Tile()
    {
        tileItem = null;
        walkable = true;
        image = null;
    }
    
    public boolean canEnter()
    {
        return isWalkable();
    }
    
    public boolean canLeave()
    {
        return true;
    }
    
    public void draw(int row, int column, Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if(image != null)
            g2d.drawImage(image, column * 32, row * 32, null);
        else
        {
            if(this.isWalkable())
                g2d.setColor(Color.GREEN);
            else
                g2d.setColor(Color.RED);
            g2d.fillRect(column * 32, row * 32, 32, 32);
        }
        if(tileItem != null)
            tileItem.draw(row, column, g);
    }
    
    //Fields
    public Item getItem(){return tileItem;}
    public boolean isWalkable(){return walkable;}
    public BufferedImage getImage(){return image;}
    
    public void setItem(Item item){this.tileItem = item;}
    public void setWalkable(boolean isWalkable){this.walkable = isWalkable;}
    public void setImage(BufferedImage image){this.image = image;}
}
