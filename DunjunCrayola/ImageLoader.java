import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

/**
 * ImageFileManager is a small utility class with static methods to load
 * and save images.
 * 
 * @author Michael Kolling and David J Barnes 
 * @version 1.0
 */
public class ImageLoader
{
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    
    /**
     * Open a file chooser and let the user select an image file in the file
     * system. Then read an image file from disk and return it as an image. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @return       The image object or null is it was not a valid image file.
     */
    public static BufferedImage getImage()
    {
        int returnVal = fileChooser.showOpenDialog(null);

        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return null;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        return loadImage(selectedFile);
    }

    /**
     * Read an image file from disk and return it as a BufferedImage. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @param imageFile  The image file to be loaded.
     * @return           The image object or null is it was not a valid image file.
     */
    public static BufferedImage loadImage(File imageFile)
    {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image == null || (image.getWidth(null) < 0)) {
                // we could not load the image - probably invalid file format
                return null;
            }
            return image;
        }
        catch(IOException exc) {
            return null;
        }
    }
    
    /**
     * Read an image file from disk and return it as a BufferedImage. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @param imageFile  The image file path to be loaded.
     * @return           The image object or null is it was not a valid image file.
     */
    public static BufferedImage loadImage(String filePath)
    {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            if(image == null || (image.getWidth(null) < 0)) {
                // we could not load the image - probably invalid file format
                return null;
            }
            return image;
        }
        catch(IOException exc) {
            return null;
        }
    }
}
