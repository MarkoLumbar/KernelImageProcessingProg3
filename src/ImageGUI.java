import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

//vir: https://www.youtube.com/watch?v=gp9H0WLxKgU&ab_channel=ProgrammingandMathTutorials
public class ImageGUI implements ActionListener {
    private BufferedImage image;
    private JFrame frame;
    private JPanel centerTextPanel;


    //vir: https://www.youtube.com/watch?v=PD6pd6AMoOI&list=PLZPZq0r_RZOMhCAyywfnYLlrjiVOkdAI1&index=53&ab_channel=BroCode za border layout
    public ImageGUI(){
        frame = new JFrame("Image GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        centerTextPanel = new JPanel();
        centerTextPanel.setBackground(Color.RED);

        JLabel textlabel = new JLabel("To select an image, click the select button");
        textlabel.setForeground(Color.BLUE);
        textlabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        centerTextPanel.add(textlabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);

        JButton selectButton = new JButton("Select Image");
        bottomPanel.add(selectButton);
        selectButton.addActionListener(this);

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

            centerTextPanel.removeAll();
            JLabel label = new JLabel(new ImageIcon(image));
            centerTextPanel.add(label);
            //vir: https://javarevisited.blogspot.com/2017/04/difference-between-repaint-and-revalidate-in-Swing-Java.html
            centerTextPanel.revalidate(); //layout manager recalculates the layout, ko spremenimo component
            centerTextPanel.repaint(); //redrawing komponente, po spremembi

            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error while loading the image: " + e.getMessage());
        }
    }

    //vir: https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html za JPG in PNG filter
    //vir: https://www.youtube.com/watch?v=A6sA9KItwpY&list=PLZPZq0r_RZOMhCAyywfnYLlrjiVOkdAI1&index=66&ab_channel=BroCode za response handling
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select an image");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);

        int response = chooser.showOpenDialog(frame); //vrne integer 0, če file obstaja, obstaja tudi saveDialog

        if (response == JFileChooser.APPROVE_OPTION){ //uspesno izbran file
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Choosen file: " + selectedFile.getName());
            loadImage(selectedFile.getAbsolutePath());

        }

    }
}

