package MainPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This Class will help us to create a JComponent that can hold an image, whit ability of changing
 */
public class PictureComponent extends JComponent {
    private BufferedImage image;

    /**
     * The Constructor Method of PictureComponent, it accept an initial image file,
     * then set its preferred size at the size of the Image.
     * @param f file of the picture
     */
    public PictureComponent(File f){
        changeImage(f);
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));//image.getWidth(), image.getHeight()));
    }

    /**
     * this method is used to paint the image we have in this class
     * @param g the graphical object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image, null, 0, 0);
    }

    /**
     * This Method help us in changing the image to new one, and then reset the dimensions.
     * @param file file of the picture
     */
    public void changeImage(File file){
        try {
            image = ImageIO.read(file);
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            repaint();
        } catch (IOException e) {
            System.out.println("Cont open File");
        }
    }
}
