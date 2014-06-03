import java.awt.image.BufferedImage;

public class Button
{
    private Location location;
    private BufferedImage image;
    
    public Button()
    {
        location = new Location(0,0);
        image = null;
    }
    
    public Location getLocation(){return location;}
    public BufferedImage getImage(){return image;}
}
