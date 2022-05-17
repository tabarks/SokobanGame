package MainPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureComponent extends JComponent {
    private BufferedImage image;

    public PictureComponent(File f){
        changeImage(f);
        setPreferredSize(new Dimension(32, 32));//image.getWidth(), image.getHeight()));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image, null, 0, 0);
    }

    public void changeImage(File file){
        try {
            image = ImageIO.read(file);
            repaint();
        } catch (IOException e) {
            System.out.println("Cont open File");
        }
    }
}
