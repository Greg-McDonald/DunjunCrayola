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
    private String name;
    
    public Item()
    {
        image = null;
        name = "Unidentified Item";
    }
    
    //Fields
    public BufferedImage getImage(){return image;}
    public void setImage(BufferedImage image){this.image = image;}
    
    public String getName(){return name;}
    public void setName(String itemName){if(itemName != null)name = itemName;else name = "Unidentified Item";}
    
    //Drawing
    public void draw(int row, int column, Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if(image != null)
            g2d.drawImage(image, column * 32, row * 32, null);
    }
}
