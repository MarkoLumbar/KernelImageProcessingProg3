import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//vir: https://www.youtube.com/watch?v=gp9H0WLxKgU&ab_channel=ProgrammingandMathTutorials
public class ImageGUI {
    private BufferedImage image;
    private JFrame frame;

    public ImageGUI(){
        frame = new JFrame("Image GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    public void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));

            if(image == null){
                System.out.println("No image");
                return;
            }

            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.pack(); //adjust to the size of image
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error while loading the image: " + e.getMessage());
        }
    }

}

