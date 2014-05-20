import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private BufferedImage image;
    
    public Item()
    {
        image = null;
    }
    
    //Fields
    public BufferedImage getImage(){return image;}
    
    public void setImage(BufferedImage image){this.image = image;}
    
    public void draw(int row, int column, Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if(image != null)
            g2d.drawImage(image, column * 32, row * 32, null);
    }
}
