import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//vir: https://www.youtube.com/watch?v=gp9H0WLxKgU&ab_channel=ProgrammingandMathTutorials
public class ImageGUI {
    private BufferedImage image;
    private JFrame frame;


    //vir: https://www.youtube.com/watch?v=PD6pd6AMoOI&list=PLZPZq0r_RZOMhCAyywfnYLlrjiVOkdAI1&index=53&ab_channel=BroCode za border layout
    public ImageGUI(){
        frame = new JFrame("Image GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        JPanel centerTextPanel = new JPanel();
        centerTextPanel.setBackground(Color.RED);

        JLabel textlabel = new JLabel("To select an image, click the select button");
        textlabel.setForeground(Color.BLUE);
        textlabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        centerTextPanel.add(textlabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);

        JButton selectButton = new JButton("Select Image");
        bottomPanel.add(selectButton);

        centerTextPanel.setPreferredSize(new Dimension(700, 100));
        bottomPanel.setPreferredSize(new Dimension(700, 50));

        frame.add(centerTextPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
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

