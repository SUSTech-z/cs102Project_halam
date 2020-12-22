package view;


import javax.swing.*;
import java.awt.*;

public class AboutHalma extends JFrame {
    public AboutHalma() {
        JFrame window = new JFrame();  //create a Frame
        window.setTitle("About HALMA");
        ImageIcon picture = new ImageIcon("pic\\About halma.png");
        Image image = picture.getImage();  //create an Image to change the size of the picture
        ImageIcon newpicture = new ImageIcon(image.getScaledInstance(picture.getIconWidth() , picture.getIconHeight() , Image.SCALE_SMOOTH));
        JLabel label = new JLabel(newpicture);  //add the picture to a label

        window.add(label);  //add the label to the frame
        window.setVisible(true);  //Set the window to visible
         window.setLocation(350,200);
        window.setSize(newpicture.getIconWidth(), newpicture.getIconHeight() + 30);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}